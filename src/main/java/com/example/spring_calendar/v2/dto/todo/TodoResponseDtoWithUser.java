package com.example.spring_calendar.v2.dto.todo;

import com.example.spring_calendar.v2.dto.user.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoResponseDtoWithUser {
    private Long id;
    private String userName;
    private String title;
    private String contents;
    private String createdAt;
    private String updatedAt;
    private Long userId;
    private UserResponseDto user;
}
