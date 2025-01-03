package org.example.account.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record SignupRequestDto(
        @NotEmpty @Size(min = 4, max = 30) String loginId,
        @NotEmpty @Size(min = 6, max = 20) String password
) {
}
