package com.example.spring_calendar.v2.repository.todo;

import com.example.spring_calendar.v2.dto.todo.TodoResponseDtoWithUser;
import com.example.spring_calendar.v2.entity.todo.Todos;

import java.util.List;

public interface TodoRepository {
    TodoResponseDtoWithUser saveTodo(Todos todo);

    TodoResponseDtoWithUser findTodoByIdOrElseThrow(Long id);

    Todos findTodoByIdOrElseThrowIncludePassword(Long id);

    List<TodoResponseDtoWithUser> findAllTodos();

    List<TodoResponseDtoWithUser> findAllTodosByUserNameAndUpdatedAt(String userName, String updatedAt);

    List<TodoResponseDtoWithUser> findAllTodosByUserName(String userName);

    List<TodoResponseDtoWithUser> findAllTodosByUpdatedAt(String updatedAt);

    int updateTodo(Todos todo);

    int deleteTodo(Long id);
}
