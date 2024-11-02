package com.example.spring_calendar.v1.dto;

import lombok.Getter;

@Getter
public class TodoRequestDto {
    private String userName;
    private String password;
    private String title;
    private String contents;
}
