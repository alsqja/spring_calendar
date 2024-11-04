package com.example.spring_calendar.v2.controller.todo;

import com.example.spring_calendar.v2.dto.todo.TodoRequestDto;
import com.example.spring_calendar.v2.dto.todo.TodoResponseDtoWithUser;
import com.example.spring_calendar.v2.service.todo.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<TodoResponseDtoWithUser> createTodo(@RequestBody TodoRequestDto dto) {

        return new ResponseEntity<>(todoService.saveTodo(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDtoWithUser> findTodoById(@PathVariable Long id) {

        return new ResponseEntity<>(todoService.findTodoById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TodoResponseDtoWithUser>> findAllTodos(
            @RequestParam(defaultValue = "") String userName,
            @RequestParam(defaultValue = "") String updatedAt
    ) {

        return new ResponseEntity<>(todoService.findAllTodos(userName, updatedAt), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TodoResponseDtoWithUser> updateTodo(
            @PathVariable Long id,
            @RequestBody TodoRequestDto dto
    ) {

        return new ResponseEntity<>(todoService.updateTodo(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(
            @PathVariable Long id,
            @RequestBody TodoRequestDto dto
    ) {
        todoService.deleteTodo(id, dto.getPassword());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
