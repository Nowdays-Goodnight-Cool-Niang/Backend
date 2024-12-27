package org.example.account.domain;

import java.util.Collection;
import java.util.List;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AccountDetail implements UserDetails {
    @Getter
    private final Long id;
    private final String loginId;
    private final String password;

    public AccountDetail(Account account) {
        this.id = account.getId();
        this.loginId = account.getLoginId();
        this.password = account.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.loginId;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
}
