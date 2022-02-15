package com.ee.cruddemo.services;

import com.ee.cruddemo.entities.Todo;
import com.ee.cruddemo.repositories.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Todo create(Todo todo) {
        return todoRepository.save(todo);
    }

}
