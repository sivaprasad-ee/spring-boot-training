package com.ee.cruddemo.services;

import com.ee.cruddemo.entities.Todo;
import com.ee.cruddemo.entities.User;
import com.ee.cruddemo.repositories.TodoRepository;
import com.ee.cruddemo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TodoRepository todoRepository;
    private final TodoService todoService;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    //1. Obtain a DB connection
    // 2. Starts a txn
    //3. Run all the operations in this method
    // 4. if success then commit
    // 5. if RuntimeException then rollback

    @Transactional(readOnly = true)
    public User createUserAndTodos(User user) {
        // 1. Create User
        // 2. Insert 1 sample todo
        return null;
    }


    public User createUserAndSampleTodos(User user) {
        // 1. Create User
        // 2. Insert 1 sample todo
        todoService.create(null);
        userRepository.save(user);
        return null;
    }

}
