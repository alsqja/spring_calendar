package com.example.spring_calendar.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoResponseDtoV1 {
    private Long id;
    private String user_name;
    private String title;
    private String contents;
    private String created_at;
    private String updated_at;
    private Long user_id;
}
