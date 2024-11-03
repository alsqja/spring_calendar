package com.example.spring_calendar.v2.service.user;

import com.example.spring_calendar.v2.dto.user.UserRequestDto;
import com.example.spring_calendar.v2.dto.user.UserResponseDto;

public interface UserService {
    public UserResponseDto saveUser(UserRequestDto dto);
}
