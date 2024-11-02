package com.example.spring_calendar.v1.dto;

import lombok.Getter;

@Getter
public class TodoRequestDto {
    private String user_name;
    private String password;
    private String title;
    private String contents;
}
