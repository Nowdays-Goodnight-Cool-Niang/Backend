package org.example.todo.dto.request;

import jakarta.validation.constraints.NotNull;

public record DeleteTodoRequestDto(@NotNull Long id) {
}
