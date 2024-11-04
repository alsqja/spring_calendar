package com.example.spring_calendar.v2.service.todo;

import com.example.spring_calendar.v2.dto.todo.TodoRequestDto;
import com.example.spring_calendar.v2.dto.todo.TodoResponseDtoWithUser;

import java.util.List;

public interface TodoService {
    TodoResponseDtoWithUser saveTodo(TodoRequestDto dto);

    TodoResponseDtoWithUser findTodoById(Long id);

    List<TodoResponseDtoWithUser> findAllTodos(String userName, String updatedAt);

    TodoResponseDtoWithUser updateTodo(Long id, TodoRequestDto dto);

    void deleteTodo(Long id, String password);
}
