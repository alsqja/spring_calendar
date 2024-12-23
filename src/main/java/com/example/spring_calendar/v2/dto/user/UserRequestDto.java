package com.example.spring_calendar.v2.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserRequestDto {
    private String name;

    @NotNull(message = "비밀번호를 입력하세요")
    private String password;

    @Email(message = "이메일 형식으로 입력하세요")
    private String email;
}
