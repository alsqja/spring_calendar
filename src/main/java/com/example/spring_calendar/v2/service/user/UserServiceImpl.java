package com.example.spring_calendar.v2.service.user;

import com.example.spring_calendar.v2.dto.todo.TodoResponseDto;
import com.example.spring_calendar.v2.dto.user.CreateUserReqDto;
import com.example.spring_calendar.v2.dto.user.UserRequestDto;
import com.example.spring_calendar.v2.dto.user.UserResponseDto;
import com.example.spring_calendar.v2.entity.user.User;
import com.example.spring_calendar.v2.repository.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserResponseDto saveUser(CreateUserReqDto dto) {
        if (userRepository.findExistingEmail(dto.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이메일은 중복될 수 없습니다.");
        }

        User user = new User(dto);

        return userRepository.saveUser(user);
    }

    //  SELECT 요청만 2개 -> transactional 필요 x
    @Override
    public List<TodoResponseDto> getAllUserTodo(Long id) {

        //  user_id 검증
        findUserById(id);

        return userRepository.getAllUserTodo(id);
    }

    @Override
    public UserResponseDto findUserById(Long id) {
        return userRepository.findUserByIdOrElseThrow(id);
    }

    @Override
    public List<UserResponseDto> findAllUser() {
        return userRepository.findAllUser();
    }

    @Override
    public UserResponseDto login(String email, String password) {
        if (email == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이메일을 입력해주세요.");
        }
        if (password == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호를 입력해주세요.");
        }

        return userRepository.login(email, password);
    }

    @Transactional
    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto dto) {
        User user = userRepository.findUserByIdOrElseThrowIncludePassword(id);

        if (!user.getPassword().equals(dto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "잘못된 비밀번호입니다.");
        }

        user.patchUserByDto(id, dto);

        userRepository.updateUser(user);

        //  data를 db 에서 받아와 리턴하는 방식 -> 입력 데이터와 LocalDateTime.now() 를 통해 조회 없이 timestamp 전달
        return new UserResponseDto(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long id, String password) {
        User user = userRepository.findUserByIdOrElseThrowIncludePassword(id);

        if (!user.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "잘못된 비밀번호입니다.");
        }

        userRepository.deleteUser(id);
    }
}
