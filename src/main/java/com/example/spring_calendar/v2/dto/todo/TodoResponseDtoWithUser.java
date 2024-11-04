package com.example.spring_calendar.v2.dto.todo;

import com.example.spring_calendar.v2.dto.user.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoResponseDtoWithUser {
    private Long id;
    private String user_name;
    private String title;
    private String contents;
    private String created_at;
    private String updated_at;
    private Long user_id;
    private UserResponseDto user;
}
