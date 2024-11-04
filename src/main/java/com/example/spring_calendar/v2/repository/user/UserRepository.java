package com.example.spring_calendar.v2.repository.user;

import com.example.spring_calendar.v2.dto.todo.TodoResponseDto;
import com.example.spring_calendar.v2.dto.user.UserResponseDto;
import com.example.spring_calendar.v2.entity.user.User;

import java.util.List;

public interface UserRepository {
    public UserResponseDto saveUser(User dto);

    public List<TodoResponseDto> getAllUserTodo(Long id);

    public UserResponseDto findUserByIdOrElseThrow(Long id);

    public List<UserResponseDto> findAllUser();

    public UserResponseDto login(String email, String password);
}
