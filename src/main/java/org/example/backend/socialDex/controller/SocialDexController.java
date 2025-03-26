package org.example.backend.socialDex.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.account.entity.Account;
import org.example.backend.account.repository.AccountRepository;
import org.example.backend.socialDex.dto.request.RequestUpdateSocialDexDto;
import org.example.backend.socialDex.dto.response.ResponseSocialDexDto;
import org.example.backend.socialDex.dto.response.ResponseSocialDexInfoDto;
import org.example.backend.socialDex.service.SocialDexService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/participants")
@RequiredArgsConstructor
public class SocialDexController {

    private final SocialDexService socialDexService;
    private final AccountRepository accountRepository;

    @PostMapping
    public ResponseEntity<ResponseSocialDexDto> updateSocialDex(@AuthenticationPrincipal Account account,
                                                                @RequestBody RequestUpdateSocialDexDto requestUpdateSocialDexDto) {
        ResponseSocialDexDto responseSocialDexDto =
                socialDexService.updateSocialDex(account.getId(), requestUpdateSocialDexDto.targetAccountId());

        return ResponseEntity.ok(responseSocialDexDto);
    }

    @GetMapping
    public ResponseEntity<ResponseSocialDexInfoDto> getSocialDex(@AuthenticationPrincipal Account account, Pageable pageable) {
        return ResponseEntity.ok(socialDexService.getSocialDex(account.getId(), pageable));
    }

    @PostMapping("/{count}")
    public ResponseEntity<ResponseSocialDexDto> forceUpdateSocialDex(@AuthenticationPrincipal Account account, @PathVariable("count") Long count) {
        Account targetAccount = accountRepository.findByKakaoOauthId(count)
                .orElseThrow();

        ResponseSocialDexDto responseSocialDexDto =
                socialDexService.updateSocialDex(account.getId(), targetAccount.getId());

        return ResponseEntity.ok(responseSocialDexDto);
    }
}
