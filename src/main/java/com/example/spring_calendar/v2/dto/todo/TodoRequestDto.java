package com.example.spring_calendar.v2.dto.todo;

import lombok.Getter;

@Getter
public class TodoRequestDto {
    private String userName;
    private String password;
    private String title;
    private String contents;
    private Long userId;
}
