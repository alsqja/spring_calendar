package com.example.spring_calendar.v2.dto.todo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateTodoReqDto {
    @NotNull(message = "이름을 입력해주세요.")
    private String userName;

    @NotNull(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotNull(message = "제목을 입력해주세요.")
    @Size(min = 1, max = 20, message = "제목은 한글자 이상, 20글자 이내 입니다.")
    private String title;

    @NotNull(message = "내용을 입력해주세요.")
    @Size(min = 1, max = 200, message = "내용은 한글자 이상 200글자 이내 입니다.")
    private String contents;

    @NotNull(message = "유저아이디를 입력해주세요.")
    private Long userId;
}
