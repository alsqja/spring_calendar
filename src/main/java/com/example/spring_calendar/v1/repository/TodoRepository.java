package com.example.spring_calendar.v1.repository;

import com.example.spring_calendar.v1.dto.TodoResponseDto;
import com.example.spring_calendar.v1.entity.Todo;

public interface TodoRepository {
    TodoResponseDto saveTodo(Todo todo);
}
