package com.example.spring_calendar.v2.dto.todo;

import lombok.Getter;

import java.util.List;

@Getter
public class TodoPageResDto {
    private Long totalCount;
    private Long totalPage;
    private List<TodoResponseDtoWithUser> datas;
}
