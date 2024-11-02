package com.example.spring_calendar.v1.service;

import com.example.spring_calendar.v1.dto.TodoRequestDto;
import com.example.spring_calendar.v1.dto.TodoResponseDto;

public interface TodoService {
    TodoResponseDto saveTodo(TodoRequestDto dto);
}
