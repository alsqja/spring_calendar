package com.example.spring_calendar.v2.dto.user;

import com.example.spring_calendar.v2.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private String created_at;
    private String updated_at;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.created_at = user.getCreatedAt();
        this.updated_at = user.getUpdatedAt();
    }
}
