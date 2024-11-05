package com.example.spring_calendar.v2.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class LoginReqDto {
    @NotNull(message = "이메일을 입력하세요")
    @Email
    private String email;

    @NotNull(message = "비밀번호를 입력하세요")
    private String password;
}
