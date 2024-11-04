package com.example.spring_calendar.v2.entity.todo;

import com.example.spring_calendar.v2.dto.todo.TodoRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
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

        //  v1 -> user 가 없어 user_id 하드코딩
        this.user_id = 1L;
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
