package com.ee.cruddemo.repositories;

import com.ee.cruddemo.entities.Todo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query("select t from Todo t where t.completed = true")
    //@Query(value = "select * from todos t where t.completed = true", nativeQuery = true)
    List<Todo> findCompletedTodos(Pageable pageable);

    @Query("delete from Todo t where t.completed = true")
    @Modifying
    void deleteAllCompletedTodos();

    List<Todo> findByUserId(Long userId);

    List<Todo> findByUserEmail(String userEmail);
}

