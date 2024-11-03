package com.example.spring_calendar.v2.entity.todo;

import com.example.spring_calendar.v2.dto.todo.TodoRequestDto;
import lombok.Getter;

@Getter
public class Todo {
    private Long id;
    private String user_name;
    private String password;
    private String title;
    private String contents;
    private String created_at;
    private String updated_at;
    private Long user_id;

    public Todo(TodoRequestDto dto) {
        this.user_name = dto.getUserName();
        this.password = dto.getPassword();
        this.title = dto.getTitle();
        this.contents = dto.getContents();
        this.user_id = dto.getUserId();
    }

    public Todo(String password) {
        this.password = password;
    }
}
