package org.example.todo.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.account.domain.Account;
import org.example.account.domain.AccountDetail;
import org.example.account.repository.AccountRepository;
import org.example.exception.CustomException;
import org.example.exception.ExceptionStatus;
import org.example.todo.domain.Todo;
import org.example.todo.dto.request.CreateTodoRequestDto;
import org.example.todo.dto.request.DeleteTodoRequestDto;
import org.example.todo.dto.request.UpdateCheckTodoRequestDto;
import org.example.todo.dto.request.UpdateContentTodoRequestDto;
import org.example.todo.dto.response.TodoResponseDto;
import org.example.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final AccountRepository accountRepository;

    public TodoResponseDto create(AccountDetail accountDetail, CreateTodoRequestDto createTodoRequestDto) {
        Account account = accountRepository.findById(accountDetail.getId())
                .orElseThrow(() -> new CustomException(ExceptionStatus.NOT_FOUND));

        Todo todo = todoRepository.save(new Todo(createTodoRequestDto.content(), account));
        return new TodoResponseDto(todo);
    }

    public List<TodoResponseDto> read(AccountDetail accountDetail) {
        return todoRepository.findByAccountId(accountDetail.getId()).stream().map(TodoResponseDto::new).toList();
    }

    public TodoResponseDto updateContent(AccountDetail accountDetail, UpdateContentTodoRequestDto updateContentTodoRequestDto) {
        Todo todo = todoRepository.findById(updateContentTodoRequestDto.taskId())
                .orElseThrow(() -> new CustomException(ExceptionStatus.NOT_FOUND));

        if (!todo.getAccount().getId().equals(accountDetail.getId())) {
            throw new CustomException(ExceptionStatus.NO_PERMISSION);
        }

        todo.updateContent(updateContentTodoRequestDto.content());
        return new TodoResponseDto(todo);
    }

    public TodoResponseDto updateCheck(AccountDetail accountDetail, UpdateCheckTodoRequestDto updateCheckTodoRequestDto) {
        Todo todo = todoRepository.findById(updateCheckTodoRequestDto.taskId())
            .orElseThrow(() -> new CustomException(ExceptionStatus.NOT_FOUND));

        if (!todo.getAccount().getId().equals(accountDetail.getId())) {
            throw new CustomException(ExceptionStatus.NO_PERMISSION);
        }

        todo.updateCheck(updateCheckTodoRequestDto.check());
        return new TodoResponseDto(todo);
    }

    public void delete(AccountDetail accountDetail, DeleteTodoRequestDto deleteTodoRequestDto) {
        Todo todo = todoRepository.findById(deleteTodoRequestDto.taskId())
                .orElseThrow(() -> new CustomException(ExceptionStatus.NOT_FOUND));

        if (!todo.getAccount().getId().equals(accountDetail.getId())) {
            throw new CustomException(ExceptionStatus.NO_PERMISSION);
        }

        todoRepository.delete(todo);
    }
}
