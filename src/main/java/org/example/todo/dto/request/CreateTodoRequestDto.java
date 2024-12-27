package org.example.todo.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record CreateTodoRequestDto(@NotEmpty String content) {
}
