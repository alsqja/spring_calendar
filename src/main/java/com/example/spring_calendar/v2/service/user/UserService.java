package com.example.spring_calendar.v2.service.user;

import com.example.spring_calendar.v2.dto.todo.TodoResponseDto;
import com.example.spring_calendar.v2.dto.user.UserRequestDto;
import com.example.spring_calendar.v2.dto.user.UserResponseDto;

import java.util.List;

public interface UserService {

    public UserResponseDto saveUser(UserRequestDto dto);

    public List<TodoResponseDto> getAllUserTodo(Long id);

    public UserResponseDto findUserById(Long id);
}
