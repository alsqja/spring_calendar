package com.example.spring_calendar.v2.entity.user;

import com.example.spring_calendar.v2.dto.user.CreateUserReqDto;
import com.example.spring_calendar.v2.dto.user.UserRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
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

    public User(CreateUserReqDto dto) {
        this.name = dto.getName();
        this.password = dto.getPassword();
        this.email = dto.getEmail();
    }

    public void patchUserByDto(Long id, UserRequestDto dto) {
        this.id = id;
        if (dto.getName() != null) {
            this.name = dto.getName();
        }
        if (dto.getEmail() != null) {
            this.email = dto.getEmail();
        }
    }
}
