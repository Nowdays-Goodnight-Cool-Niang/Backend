package org.example.backend.account.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.account.dto.request.RequestUpdateInfoDto;
import org.example.backend.account.dto.response.ResponseAccountIdDto;
import org.example.backend.account.dto.response.ResponseAccountInfo;
import org.example.backend.account.entity.Account;
import org.example.backend.account.repository.AccountRepository;
import org.example.backend.account.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;
    private final AccountRepository accountRepository;

    @PatchMapping
    public ResponseEntity<Void> updateAccountInfo(@AuthenticationPrincipal Account account,
                                                  @RequestBody RequestUpdateInfoDto requestUpdateInfoDto) {

        accountService.updateAccountInfo(account.getId(), requestUpdateInfoDto);
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

    @PostMapping("/{count}")
    public ResponseEntity<Set<Account>> inputDummyAccounts(@PathVariable("count") Long count) {
        long totalCount = accountRepository.count();

        Set<Account> collect = LongStream.range(totalCount + 1, totalCount + count + 1)
                .mapToObj(value -> new Account(value, value + "번 유저"))
                .collect(Collectors.toSet());

        accountRepository.saveAll(collect);

        return ResponseEntity.ok(collect);
    }
}
