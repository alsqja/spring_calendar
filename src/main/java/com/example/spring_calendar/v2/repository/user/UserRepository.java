package com.example.spring_calendar.v2.repository.user;

import com.example.spring_calendar.v2.dto.user.UserResponseDto;
import com.example.spring_calendar.v2.entity.user.User;

public interface UserRepository {
    public UserResponseDto saveUser(User dto);
}
