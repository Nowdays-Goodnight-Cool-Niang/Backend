package org.example.todo.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdateCheckTodoRequestDto(@NotNull Long taskId, @NotEmpty Boolean check) {
}
