package com.example.spring_calendar.v1.service;

import com.example.spring_calendar.v1.dto.TodoRequestDto;
import com.example.spring_calendar.v1.dto.TodoResponseDto;

import java.util.List;

public interface TodoService {
    TodoResponseDto saveTodo(TodoRequestDto dto);

    TodoResponseDto findTodoById(Long id);

    List<TodoResponseDto> findAllTodos(String userName, String updatedAt);

    TodoResponseDto updateTodo(Long id, TodoRequestDto dto);
}
