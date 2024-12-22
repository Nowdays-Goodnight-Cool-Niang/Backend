package org.example.todo.service;

import lombok.RequiredArgsConstructor;
import org.example.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public String greeting() {
        return "hello!";
    }
}
