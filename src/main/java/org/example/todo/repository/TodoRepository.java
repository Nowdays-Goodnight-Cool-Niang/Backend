package org.example.todo.repository;

import java.util.List;
import org.example.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findByAccountId(Long id);
}
