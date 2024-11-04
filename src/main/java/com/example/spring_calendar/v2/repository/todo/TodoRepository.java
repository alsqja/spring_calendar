package com.example.spring_calendar.v2.repository.todo;

import com.example.spring_calendar.v2.dto.todo.TodoResponseDto;
import com.example.spring_calendar.v2.entity.todo.Todo;

import java.util.List;

public interface TodoRepository {
    TodoResponseDto saveTodo(Todo todo);

    TodoResponseDto findTodoByIdOrElseThrow(Long id);

    Todo findTodoByIdOrElseThrowIncludePassword(Long id);

    List<TodoResponseDto> findAllTodos();

    List<TodoResponseDto> findAllTodosByUserNameAndUpdatedAt(String userName, String updatedAt);

    List<TodoResponseDto> findAllTodosByUserName(String userName);

    List<TodoResponseDto> findAllTodosByUpdatedAt(String updatedAt);

    int updateTodo(Todo todo);

    int deleteTodo(Long id);
}
