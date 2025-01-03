package org.example.todo.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdateContentTodoRequestDto(@NotNull Long taskId, @NotEmpty String content) {
}
