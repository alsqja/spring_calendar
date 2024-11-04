package com.example.spring_calendar.v2.repository.todo;

import com.example.spring_calendar.v2.dto.todo.TodoResponseDto;
import com.example.spring_calendar.v2.dto.todo.TodoResponseDtoWithUser;
import com.example.spring_calendar.v2.entity.todo.Todos;

public interface TodoRepository {
    TodoResponseDto saveTodo(Todos todo);

    TodoResponseDto findTodoByIdOrElseThrow(Long id);

    Todos findTodoByIdOrElseThrowIncludePassword(Long id);

    int updateTodo(Todos todo);

    int deleteTodo(Long id);

    TodoResponseDtoWithUser getTodoWithUser(Long id);
}
