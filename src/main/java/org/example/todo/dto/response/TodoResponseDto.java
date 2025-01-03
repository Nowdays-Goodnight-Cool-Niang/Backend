package org.example.todo.dto.response;

import lombok.Getter;
import org.example.todo.domain.Todo;

@Getter
public class TodoResponseDto {
    private final Long id;
    private final String content;
    private final Boolean check;

    public TodoResponseDto(Todo todo) {
        this.id = todo.getId();
        this.content = todo.getContent();
        this.check = todo.getIsSuccess();
    }
}
