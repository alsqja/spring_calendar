package com.example.spring_calendar.v2.repository.todo;

import com.example.spring_calendar.v2.dto.todo.TodoResponseDtoWithUser;
import com.example.spring_calendar.v2.entity.todo.Todo;

import java.util.List;

public interface TodoRepository {
    TodoResponseDtoWithUser saveTodo(Todo todo);

    TodoResponseDtoWithUser findTodoByIdOrElseThrow(Long id);

    Todo findTodoByIdOrElseThrowIncludePassword(Long id);

    List<TodoResponseDtoWithUser> findAllTodos();

    List<TodoResponseDtoWithUser> findAllTodosByUserNameAndUpdatedAt(String userName, String updatedAt);

    List<TodoResponseDtoWithUser> findAllTodosByUserName(String userName);

    List<TodoResponseDtoWithUser> findAllTodosByUpdatedAt(String updatedAt);

    int updateTodo(Todo todo);

    int deleteTodo(Long id);
}
