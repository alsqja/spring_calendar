package com.example.spring_calendar.v2.service.todo;

import com.example.spring_calendar.v2.dto.todo.CreateTodoReqDto;
import com.example.spring_calendar.v2.dto.todo.TodoRequestDto;
import com.example.spring_calendar.v2.dto.todo.TodoResponseDto;
import com.example.spring_calendar.v2.dto.todo.TodoResponseDtoWithUser;
import org.springframework.data.domain.Page;

public interface TodoService {
    TodoResponseDto saveTodo(CreateTodoReqDto dto);

    TodoResponseDto findTodoById(Long id);

    TodoResponseDto updateTodo(Long id, TodoRequestDto dto);

    void deleteTodo(Long id, String password);

    public Page<TodoResponseDto> findAllTodos(int page, int offset);

    public TodoResponseDtoWithUser getTodoWithUser(Long id);
}
