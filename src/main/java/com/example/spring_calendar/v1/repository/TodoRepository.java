package com.example.spring_calendar.v1.repository;

import com.example.spring_calendar.v1.dto.TodoResponseDto;
import com.example.spring_calendar.v1.entity.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoRepository {
    TodoResponseDto saveTodo(Todo todo);

    TodoResponseDto findTodoByIdOrElseThrow(Long id);

    List<TodoResponseDto> findAllTodos();

    List<TodoResponseDto> findAllTodosByUserNameAndUpdatedAt(String userName, String updatedAt);

    List<TodoResponseDto> findAllTodosByUserName(String userName);

    List<TodoResponseDto> findAllTodosByUpdatedAt(String updatedAt);

    int updateTodo(Long id, String title, String contents, String userName);

    boolean checkPassword(Long id, String password);
}
