package org.example.account.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.account.domain.Account;
import org.example.account.dto.SignupRequestDto;
import org.example.account.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignupController {

    private final AccountService accountService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        Account account = accountService.signup(signupRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(account.getLoginId());
    }
}
