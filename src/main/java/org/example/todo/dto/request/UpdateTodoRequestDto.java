package org.example.todo.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdateTodoRequestDto(@NotNull Long id, @NotEmpty String content) {
}
