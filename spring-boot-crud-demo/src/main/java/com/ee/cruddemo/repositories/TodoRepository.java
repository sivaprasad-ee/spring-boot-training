package com.ee.cruddemo.repositories;

import com.ee.cruddemo.TodoNotFoundException;
import com.ee.cruddemo.entities.Todo;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TodoRepository {
    private AtomicLong NEXT_ID = new AtomicLong(0);
    private Map<Long, Todo> TODOS = new HashMap<>();

    public List<Todo> getAllTodos() {
        return new ArrayList<>(TODOS.values());
    }

    public Todo getTodoById(Long id) {
        return TODOS.get(id);
    }

    public Todo createTodo(Todo todo) {
        todo.setId(NEXT_ID.incrementAndGet());
        todo.setCreatedOn(LocalDateTime.now());
        TODOS.put(todo.getId(), todo);
        return todo;
    }

    public Todo updateTodo(Todo todo) {
        if(!TODOS.containsKey(todo.getId())) {
            throw new TodoNotFoundException("Id doesn't exists");
        }
        TODOS.put(todo.getId(), todo);
        return todo;
    }

    public void deleteTodo(Long id) {
        if(!TODOS.containsKey(id)) {
            throw new TodoNotFoundException("Id doesn't exists");
        }
        TODOS.remove(id);
    }

}
