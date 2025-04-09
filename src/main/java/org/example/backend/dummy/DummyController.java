package org.example.backend.dummy;

import lombok.RequiredArgsConstructor;
import org.example.backend.account.entity.Account;
import org.example.backend.account.repository.AccountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@RestController
@RequestMapping("/dummy")
@RequiredArgsConstructor
public class DummyController {
    private final AccountRepository accountRepository;

    @GetMapping("/{count}")
    public ResponseEntity<Set<Account>> inputDummyAccounts(@PathVariable("count") Long count) {
        long totalCount = accountRepository.count();

        Set<Account> collect = LongStream.range(totalCount + 1, totalCount + count + 1)
                .mapToObj(value -> new Account(value, value + "번 유저"))
                .collect(Collectors.toSet());

        accountRepository.saveAll(collect);

        return ResponseEntity.ok(collect);
    }
}
