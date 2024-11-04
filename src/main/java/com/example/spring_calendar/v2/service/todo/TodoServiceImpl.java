package com.example.spring_calendar.v2.service.todo;

import com.example.spring_calendar.v2.dto.todo.TodoRequestDto;
import com.example.spring_calendar.v2.dto.todo.TodoResponseDto;
import com.example.spring_calendar.v2.entity.todo.Todo;
import com.example.spring_calendar.v2.repository.todo.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public TodoResponseDto saveTodo(TodoRequestDto dto) {
        if (dto.getUserId() == null || dto.getUserName() == null || dto.getTitle() == null || dto.getPassword() == null || dto.getContents() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "값을 정확하게 입력해주세요.");
        }

        // TODO: 입력받은 userId에 해당하는 user 없을 시 throw (findUserById 구현 후 수정)
        Todo todo = new Todo(dto);

        return todoRepository.saveTodo(todo);
    }

    @Override
    public TodoResponseDto findTodoById(Long id) {

        return todoRepository.findTodoByIdOrElseThrow(id);
    }

    @Override
    public List<TodoResponseDto> findAllTodos(String userName, String updatedAt) {
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
    public TodoResponseDto updateTodo(Long id, TodoRequestDto dto) {

        Todo todo = todoRepository.findTodoByIdOrElseThrowIncludePassword(id);

        if (!todo.getPassword().equals(dto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "잘못된 비밀번호입니다.");
        }

        todo.patchByDto(id, dto);

        todoRepository.updateTodo(todo);

        return todoRepository.findTodoByIdOrElseThrow(id);
    }

    @Override
    public void deleteTodo(Long id, String password) {
        Todo todo = todoRepository.findTodoByIdOrElseThrowIncludePassword(id);

        if (!todo.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "잘못된 비밀번호입니다.");
        }

        todoRepository.deleteTodo(id);
    }
}
