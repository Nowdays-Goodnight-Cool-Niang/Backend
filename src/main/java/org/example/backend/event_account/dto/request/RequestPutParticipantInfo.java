package org.example.backend.event_account.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RequestPutParticipantInfo(
        @NotNull
        Long eventId,

        @NotEmpty
        String jobGroup,

        @NotEmpty
        String teamName,

        @NotEmpty
        String projectInfo
) {

}
