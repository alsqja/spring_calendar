package com.example.spring_calendar.v2.entity.todo;

import com.example.spring_calendar.v2.dto.todo.TodoRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Todos {
    private Long id;
    private String user_name;
    private String password;
    private String title;
    private String contents;
    private String created_at;
    private String updated_at;
    private Long user_id;

    public Todos() {
    }

    public Todos(TodoRequestDto dto) {
        this.user_name = dto.getUserName();
        this.password = dto.getPassword();
        this.title = dto.getTitle();
        this.contents = dto.getContents();
        this.user_id = dto.getUserId();
    }

    public void patchByDto(Long id, TodoRequestDto dto) {
        this.id = id;
        if (dto.getUserName() != null) {
            this.user_name = dto.getUserName();
        }
        if (dto.getTitle() != null) {
            this.title = dto.getTitle();
        }
        if (dto.getContents() != null) {
            this.contents = dto.getContents();
        }
    }
}
