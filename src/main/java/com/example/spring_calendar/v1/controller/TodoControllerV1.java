package com.example.spring_calendar.v1.controller;

import com.example.spring_calendar.v1.dto.TodoRequestDtoV1;
import com.example.spring_calendar.v1.dto.TodoResponseDtoV1;
import com.example.spring_calendar.v1.service.TodoServiceV1;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/todos")
public class TodoControllerV1 {

    private final TodoServiceV1 todoService;

    public TodoControllerV1(TodoServiceV1 todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<TodoResponseDtoV1> createTodo(@RequestBody TodoRequestDtoV1 dto) {

        return new ResponseEntity<>(todoService.saveTodo(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDtoV1> findTodoById(@PathVariable Long id) {

        return new ResponseEntity<>(todoService.findTodoById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TodoResponseDtoV1>> findAllTodos(
            @RequestParam(defaultValue = "") String userName,
            @RequestParam(defaultValue = "") String updatedAt
    ) {

        return new ResponseEntity<>(todoService.findAllTodos(userName, updatedAt), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TodoResponseDtoV1> updateTodo(
            @PathVariable Long id,
            @RequestBody TodoRequestDtoV1 dto
    ) {

        return new ResponseEntity<>(todoService.updateTodo(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(
            @PathVariable Long id,
            @RequestBody TodoRequestDtoV1 dto
    ) {
        todoService.deleteTodo(id, dto.getPassword());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
