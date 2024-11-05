package com.example.spring_calendar.v2.dto.todo;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class TodoRequestDto {
    private String userName;

    @NotNull(message = "비밀번호는 필수 입니다.")
    private String password;

    private String title;
    private String contents;

    private Long userId;
}
