package com.example.spring_calendar.v2.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateUserReqDto {
    @NotNull(message = "이름을 입력하세요")
    private String name;

    @NotNull(message = "비밀번호를 입력하세요")
    private String password;

    @NotNull(message = "이메일을 입력하세요")
    @Email(message = "이메일 형식으로 입력하세요")
    private String email;
}
