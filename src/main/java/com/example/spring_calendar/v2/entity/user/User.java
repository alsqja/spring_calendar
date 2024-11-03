package com.example.spring_calendar.v2.entity.user;

import com.example.spring_calendar.v2.dto.user.UserRequestDto;
import lombok.Getter;

@Getter
public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String created_at;
    private String updated_at;

    public User(UserRequestDto dto) {
        this.name = dto.getName();
        this.password = dto.getPassword();
        this.email = dto.getEmail();
    }
}
