package com.example.spring_calendar.v1.entity;

import com.example.spring_calendar.v1.dto.TodoRequestDtoV1;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoV1 {
    private Long id;
    private String user_name;
    private String password;
    private String title;
    private String contents;
    private String created_at;
    private String updated_at;
    private Long user_id;

    public TodoV1(TodoRequestDtoV1 dto) {
        this.user_name = dto.getUserName();
        this.password = dto.getPassword();
        this.title = dto.getTitle();
        this.contents = dto.getContents();

        //  v1 -> user 가 없어 user_id 하드코딩
        this.user_id = 1L;
    }
}
