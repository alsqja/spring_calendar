package com.example.spring_calendar.v2.service.todo;

import com.example.spring_calendar.v2.dto.todo.TodoRequestDto;
import com.example.spring_calendar.v2.dto.todo.TodoResponseDto;

import java.util.List;

public interface TodoService {
    TodoResponseDto saveTodo(TodoRequestDto dto);

    TodoResponseDto findTodoById(Long id);

    List<TodoResponseDto> findAllTodos(String userName, String updatedAt);

    TodoResponseDto updateTodo(Long id, TodoRequestDto dto);

    void deleteTodo(Long id, String password);
}
