package com.example.spring_calendar.v1.repository;

import com.example.spring_calendar.v1.dto.TodoResponseDtoV1;
import com.example.spring_calendar.v1.entity.TodoV1;

import java.util.List;

public interface TodoRepositoryV1 {
    TodoResponseDtoV1 saveTodo(TodoV1 todo);

    TodoResponseDtoV1 findTodoByIdOrElseThrow(Long id);

    TodoV1 findTodoByIdOrElseThrowIncludePassword(Long id);

    List<TodoResponseDtoV1> findAllTodos();

    List<TodoResponseDtoV1> findAllTodosByUserNameAndUpdatedAt(String userName, String updatedAt);

    List<TodoResponseDtoV1> findAllTodosByUserName(String userName);

    List<TodoResponseDtoV1> findAllTodosByUpdatedAt(String updatedAt);

    int updateTodo(Long id, String title, String contents, String userName);

    int deleteTodo(Long id);
}
