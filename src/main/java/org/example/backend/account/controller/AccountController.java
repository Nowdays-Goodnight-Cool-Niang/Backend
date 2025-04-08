package org.example.backend.account.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.backend.account.dto.request.RequestUpdateInfoDto;
import org.example.backend.account.dto.response.ResponseAccountIdDto;
import org.example.backend.account.dto.response.ResponseAccountInfo;
import org.example.backend.account.entity.Account;
import org.example.backend.account.repository.AccountRepository;
import org.example.backend.account.service.AccountService;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;
    private final OAuth2AuthorizedClientService authorizedClientService;
    private final AccountRepository accountRepository;

    @PatchMapping
    public ResponseEntity<Void> updateAccountInfo(@AuthenticationPrincipal Account account,
                                                  @Valid @RequestBody RequestUpdateInfoDto requestUpdateInfoDto) {

        accountService.updateAccountInfo(account.getId(), requestUpdateInfoDto.name(), requestUpdateInfoDto.email(),
                requestUpdateInfoDto.linkedinUrl(), requestUpdateInfoDto.githubUrl(),
                requestUpdateInfoDto.instagramUrl());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<ResponseAccountInfo> getAccountInfo(@AuthenticationPrincipal Account account) {
        return ResponseEntity.ok(new ResponseAccountInfo(account));
    }

    @GetMapping("my-id")
    public ResponseEntity<ResponseAccountIdDto> getMyId(@AuthenticationPrincipal Account account) {
        return ResponseEntity.ok(new ResponseAccountIdDto(account.getId()));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@AuthenticationPrincipal Account account, HttpSession session) {
        accountService.delete(account);

        session.invalidate();

        ResponseCookie cookie = ResponseCookie.from("JSESSIONID", "")
                .path("/")
                .httpOnly(true)
                .maxAge(0)
                .build();

        disconnectOauth();

        return ResponseEntity.noContent()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .build();
    }

    @GetMapping("/{count}")
    public ResponseEntity<Set<Account>> inputDummyAccounts(@PathVariable("count") Long count) {
        long totalCount = accountRepository.count();

        Set<Account> collect = LongStream.range(totalCount + 1, totalCount + count + 1)
                .mapToObj(value -> new Account(value, value + "번 유저"))
                .collect(Collectors.toSet());

        accountRepository.saveAll(collect);

        return ResponseEntity.ok(collect);
    }

    private void disconnectOauth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof OAuth2AuthenticationToken oauth2Authentication) {
            String clientRegistrationId = oauth2Authentication.getAuthorizedClientRegistrationId();

            OAuth2AuthorizedClient authorizedClient = authorizedClientService
                    .loadAuthorizedClient(clientRegistrationId, authentication.getName());

            if (authorizedClient != null) {
                OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
                String tokenValue = accessToken.getTokenValue();

                requestDisconnectOauth(tokenValue);
            }
        }
    }

    private static void requestDisconnectOauth(String tokenValue) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + tokenValue);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(headers);

        restTemplate.exchange("https://kapi.kakao.com/v1/user/unlink", HttpMethod.POST, entity, String.class);
    }
}
