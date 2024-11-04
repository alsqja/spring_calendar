package com.example.spring_calendar.v2.service.user;

import com.example.spring_calendar.v2.dto.todo.TodoResponseDto;
import com.example.spring_calendar.v2.dto.user.UserRequestDto;
import com.example.spring_calendar.v2.dto.user.UserResponseDto;
import com.example.spring_calendar.v2.entity.user.User;
import com.example.spring_calendar.v2.repository.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto saveUser(UserRequestDto dto) {

        User user = new User(dto);

        //  TODO: 중복 email throw 처리
        return userRepository.saveUser(user);
    }

    @Override
    public List<TodoResponseDto> getAllUserTodo(Long id) {
        // TODO: user_id 검증
        return userRepository.getAllUserTodo(id);
    }

    @Override
    public UserResponseDto findUserById(Long id) {
        return userRepository.findUserByIdOrElseThrow(id);
    }
}
