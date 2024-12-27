package org.example.todo.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.account.domain.AccountDetail;
import org.example.todo.dto.request.CreateTodoRequestDto;
import org.example.todo.dto.request.DeleteTodoRequestDto;
import org.example.todo.dto.request.UpdateTodoRequestDto;
import org.example.todo.dto.response.TodoResponseDto;
import org.example.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoResponseDto> create(@AuthenticationPrincipal AccountDetail accountDetail,
                                         @RequestBody @Valid CreateTodoRequestDto createTodoRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(todoService.create(accountDetail, createTodoRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<TodoResponseDto>> read(@AuthenticationPrincipal AccountDetail accountDetail) {
        return ResponseEntity.ok(todoService.read(accountDetail));
    }

    @PatchMapping
    public ResponseEntity<TodoResponseDto> update(@AuthenticationPrincipal AccountDetail accountDetail,
                                         @RequestBody @Valid UpdateTodoRequestDto updateTodoRequestDto) {
        return ResponseEntity.ok(todoService.update(accountDetail, updateTodoRequestDto));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@AuthenticationPrincipal AccountDetail accountDetail,
                                         @RequestBody @Valid DeleteTodoRequestDto deleteTodoRequestDto) {
        todoService.delete(accountDetail, deleteTodoRequestDto);
        return ResponseEntity.noContent().build();
    }
}
