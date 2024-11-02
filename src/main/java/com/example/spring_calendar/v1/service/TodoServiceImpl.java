package com.example.spring_calendar.v1.service;

import com.example.spring_calendar.v1.dto.TodoRequestDto;
import com.example.spring_calendar.v1.dto.TodoResponseDto;
import com.example.spring_calendar.v1.entity.Todo;
import com.example.spring_calendar.v1.repository.TodoRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final JdbcTemplate jdbcTemplate;

    public TodoServiceImpl(TodoRepository todoRepository, JdbcTemplate jdbcTemplate) {
        this.todoRepository = todoRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public TodoResponseDto saveTodo(TodoRequestDto dto) {
        Todo todo = new Todo(dto);

        return todoRepository.saveTodo(todo);
    }

    @Override
    public TodoResponseDto findTodoById(Long id) {

        return todoRepository.findTodoByIdOrElseThrow(id);
    }

    @Override
    public List<TodoResponseDto> findAllTodos(String userName, String updatedAt) {
        if (userName.isEmpty() && updatedAt.isEmpty()) {
            return todoRepository.findAllTodos();
        }
        if (!userName.isEmpty() && updatedAt.isEmpty()) {
            return todoRepository.findAllTodosByUserName(userName);
        }
        if (userName.isEmpty() && !updatedAt.isEmpty()) {
            return todoRepository.findAllTodosByUpdatedAt(updatedAt);
        }
        return todoRepository.findAllTodosByUserNameAndUpdatedAt(userName, updatedAt);
    }
}
