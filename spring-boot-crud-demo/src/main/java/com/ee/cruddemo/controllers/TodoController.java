package com.ee.cruddemo.controllers;

import com.ee.cruddemo.entities.Todo;
import com.ee.cruddemo.repositories.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoRepository todoRepository;

    @GetMapping(value = "")
    public List<Todo> getAllTodos() {
        //return todoRepository.getAllTodos();
        Sort sort = Sort.by(Sort.Direction.DESC, "createdOn");
        Pageable pageable = PageRequest.of(2, 20, sort);
        //Page<Todo> todoPage = todoRepository.findAll(pageable);
        //return todoPage.getContent();
        return todoRepository.findCompletedTodos(pageable);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable(name = "id") Long id) {
        //final Todo todo = todoRepository.getTodoById(id);
        final Todo todo = todoRepository.findById(id).orElse(null);
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
    public ResponseEntity<Todo> createTodo(@RequestBody @Valid Todo todo) {
        //final Todo savedTodo = todoRepository.createTodo(todo);
        final Todo savedTodo = todoRepository.save(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTodo);
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable(name = "id") Long id, @RequestBody Todo todo) {
        todo.setId(id);
        //return todoRepository.updateTodo(todo);
        return todoRepository.save(todo);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable(name = "id") Long id) {
        //todoRepository.deleteTodo(id);
        todoRepository.deleteById(id);
    }
}
