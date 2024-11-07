package com.example.spring_calendar.v2.entity.todo;

import com.example.spring_calendar.v2.dto.todo.CreateTodoReqDto;
import com.example.spring_calendar.v2.dto.todo.TodoRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Todos {
    private Long id;
    private String userName;
    private String password;
    private String title;
    private String contents;
    private String createdAt;
    private String updatedAt;
    private Long userId;

    public Todos() {
    }

    public Todos(TodoRequestDto dto) {
        this.userName = dto.getUserName();
        this.password = dto.getPassword();
        this.title = dto.getTitle();
        this.contents = dto.getContents();
        this.userId = dto.getUserId();
    }

    public Todos(CreateTodoReqDto dto) {
        this.userName = dto.getUserName();
        this.password = dto.getPassword();
        this.title = dto.getTitle();
        this.contents = dto.getContents();
        this.userId = dto.getUserId();
    }

    public void patchByDto(Long id, TodoRequestDto dto) {
        this.id = id;
        if (dto.getUserName() != null) {
            this.userName = dto.getUserName();
        }
        if (dto.getTitle() != null) {
            this.title = dto.getTitle();
        }
        if (dto.getContents() != null) {
            this.contents = dto.getContents();
        }

        this.updatedAt = LocalDateTime.now().toString();
    }
}
