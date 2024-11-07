package com.example.spring_calendar.v2.entity.user;

import com.example.spring_calendar.v2.dto.user.CreateUserReqDto;
import com.example.spring_calendar.v2.dto.user.UserRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String createdAt;
    private String updatedAt;

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

        // updated_at 처리(db 읽어오는 횟수 down)
        this.updatedAt = LocalDateTime.now().toString();
    }
}
