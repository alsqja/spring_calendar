package com.example.spring_calendar.v1.service;

import com.example.spring_calendar.v1.dto.TodoRequestDtoV1;
import com.example.spring_calendar.v1.dto.TodoResponseDtoV1;

import java.util.List;

public interface TodoServiceV1 {
    TodoResponseDtoV1 saveTodo(TodoRequestDtoV1 dto);

    TodoResponseDtoV1 findTodoById(Long id);

    List<TodoResponseDtoV1> findAllTodos(String userName, String updatedAt);

    TodoResponseDtoV1 updateTodo(Long id, TodoRequestDtoV1 dto);

    void deleteTodo(Long id, String password);
}
