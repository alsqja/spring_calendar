package com.example.spring_calendar.v2.controller.todo;

import com.example.spring_calendar.v2.dto.todo.CreateTodoReqDto;
import com.example.spring_calendar.v2.dto.todo.TodoRequestDto;
import com.example.spring_calendar.v2.dto.todo.TodoResponseDto;
import com.example.spring_calendar.v2.dto.todo.TodoResponseDtoWithUser;
import com.example.spring_calendar.v2.service.todo.TodoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v2/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<TodoResponseDto> createTodo(@Valid @RequestBody CreateTodoReqDto dto) {

        return new ResponseEntity<>(todoService.saveTodo(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDto> findTodoById(
            @PathVariable Long id
    ) {

        return new ResponseEntity<>(todoService.findTodoById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> findAllTodos(
            @RequestParam(defaultValue = "5") int offset,
            @RequestParam(defaultValue = "1") int page
    ) {
        Page<TodoResponseDto> todosPage = todoService.findAllTodos(page - 1, offset);

        Map<String, Object> response = new HashMap<>();
        response.put("datas", todosPage.getContent());
        response.put("totalCount", todosPage.getTotalElements());
        response.put("totalPage", todosPage.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TodoResponseDto> updateTodo(
            @PathVariable Long id,
            @Valid @RequestBody TodoRequestDto dto
    ) {

        return new ResponseEntity<>(todoService.updateTodo(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(
            @PathVariable Long id,
            @Valid @RequestBody TodoRequestDto dto
    ) {

        todoService.deleteTodo(id, dto.getPassword());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/join")
    public ResponseEntity<TodoResponseDtoWithUser> getTodoWithUser(@PathVariable Long id) {
        return new ResponseEntity<>(todoService.getTodoWithUser(id), HttpStatus.OK);
    }
}
