package com.ee.cruddemo.repositories;

import com.ee.cruddemo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u join fetch u.todos where u.id=?1")
    User findByIdWithTodos(Long id);

    User findByEmail(String email);

    User findByUsernameAndPassword(String username, String password);
}
