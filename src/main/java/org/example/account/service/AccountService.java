package org.example.account.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.example.account.domain.Account;
import org.example.account.dto.SignupRequestDto;
import org.example.account.repository.AccountRepository;
import org.example.exception.CustomException;
import org.example.exception.ExceptionStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;

    public Account signup(SignupRequestDto signupRequestDto) {

        Optional<Account> optionalAccount = accountRepository.findByLoginId(signupRequestDto.getLoginId());

        if (optionalAccount.isPresent()) {
            throw new CustomException(ExceptionStatus.EXIST_LOGIN_ID);
        }

        String encodedPassword = passwordEncoder.encode(signupRequestDto.getPassword());

        return accountRepository.save(new Account(signupRequestDto.getLoginId(), encodedPassword));
    }
}
