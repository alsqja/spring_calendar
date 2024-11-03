package com.example.spring_calendar.v1.service;

import com.example.spring_calendar.v1.dto.TodoRequestDtoV1;
import com.example.spring_calendar.v1.dto.TodoResponseDtoV1;
import com.example.spring_calendar.v1.entity.TodoV1;
import com.example.spring_calendar.v1.repository.TodoRepositoryV1;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TodoServiceImplV1 implements TodoServiceV1 {

    private final TodoRepositoryV1 todoRepository;

    public TodoServiceImplV1(TodoRepositoryV1 todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public TodoResponseDtoV1 saveTodo(TodoRequestDtoV1 dto) {
        if (dto.getUserName() == null || dto.getTitle() == null || dto.getPassword() == null || dto.getContents() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "값을 정확하게 입력해주세요.");
        }
        TodoV1 todo = new TodoV1(dto);

        return todoRepository.saveTodo(todo);
    }

    @Override
    public TodoResponseDtoV1 findTodoById(Long id) {

        return todoRepository.findTodoByIdOrElseThrow(id);
    }

    @Override
    public List<TodoResponseDtoV1> findAllTodos(String userName, String updatedAt) {
        if (userName.isEmpty() && updatedAt.isEmpty()) {
            return todoRepository.findAllTodos();
        }
        if (!userName.isEmpty() && updatedAt.isEmpty()) {
            return todoRepository.findAllTodosByUserName(userName);
        }
        if (userName.isEmpty() && !updatedAt.isEmpty()) {
            return todoRepository.findAllTodosByUpdatedAt(updatedAt);
        }
        return todoRepository.findAllTodosByUserNameAndUpdatedAt(userName, updatedAt);
    }

    @Transactional
    @Override
    public TodoResponseDtoV1 updateTodo(Long id, TodoRequestDtoV1 dto) {

        TodoResponseDtoV1 todo = todoRepository.findTodoByIdOrElseThrow(id);

        if (!todoRepository.checkPassword(id, dto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "잘못된 비밀번호입니다.");
        }

        todoRepository.updateTodo(
                id,
                dto.getTitle() == null ? todo.getTitle() : dto.getTitle(),
                dto.getContents() == null ? todo.getContents() : dto.getContents(),
                dto.getUserName() == null ? todo.getUser_name() : dto.getUserName()
        );

        return todoRepository.findTodoByIdOrElseThrow(id);
    }

    @Override
    public void deleteTodo(Long id) {
        int deletedRow = todoRepository.deleteTodo(id);

        if (deletedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 일정입니다.");
        }
    }
}
