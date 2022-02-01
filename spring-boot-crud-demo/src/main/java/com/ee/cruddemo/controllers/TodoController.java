package com.ee.cruddemo.controllers;

import com.ee.cruddemo.entities.Todo;
import com.ee.cruddemo.repositories.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoRepository todoRepository;

    @GetMapping(value = "")
    public List<Todo> getAllTodos() {
        return todoRepository.getAllTodos();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable(name = "id") Long id) {
        final Todo todo = todoRepository.getTodoById(id);
        if(todo != null) {
            return ResponseEntity.ok(todo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /*@PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Todo createTodo(@RequestBody Todo todo) {
        return todoRepository.createTodo(todo);
    }
    */

    @PostMapping("")
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        final Todo savedTodo = todoRepository.createTodo(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTodo);
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable(name = "id") Long id, @RequestBody Todo todo) {
        todo.setId(id);
        return todoRepository.updateTodo(todo);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable(name = "id") Long id) {
        todoRepository.deleteTodo(id);
    }
}
