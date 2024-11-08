package com.example.spring_calendar.v2.dto.todo;

import com.example.spring_calendar.v2.entity.todo.Todos;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoResponseDto {
    private Long id;
    private String userName;
    private String title;
    private String contents;
    private String createdAt;
    private String updatedAt;
    private Long userId;

    public TodoResponseDto(Todos todos) {
        this.id = todos.getId();
        this.userName = todos.getUser_name();
        this.title = todos.getTitle();
        this.contents = todos.getContents();
        this.createdAt = todos.getCreated_at();
        this.updatedAt = todos.getUpdated_at();
        this.userId = todos.getUserId();
    }
}
