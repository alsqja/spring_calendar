package com.example.spring_calendar.v1.controller;

import com.example.spring_calendar.v1.dto.TodoRequestDto;
import com.example.spring_calendar.v1.dto.TodoResponseDto;
import com.example.spring_calendar.v1.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<TodoResponseDto> createTodo(@RequestBody TodoRequestDto dto) {

        return new ResponseEntity<>(todoService.saveTodo(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDto> findTodoById(@PathVariable Long id) {

        return new ResponseEntity<>(todoService.findTodoById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TodoResponseDto>> findAllTodos(
            @RequestParam(defaultValue = "") String userName,
            @RequestParam(defaultValue = "") String updatedAt
    ) {

        return new ResponseEntity<>(todoService.findAllTodos(userName, updatedAt), HttpStatus.OK);
    }
}
