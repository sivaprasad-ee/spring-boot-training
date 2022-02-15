package com.ee.cruddemo.repositories;

import com.ee.cruddemo.entities.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

//@Repository
@RequiredArgsConstructor
@Transactional
public class JpaTodoRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    public List<Todo> getAllTodos() {

        return em.createQuery("select t from Todo t", Todo.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Todo getTodoById(Long id) {
      return em.createQuery("select t from Todo t where t.id=:id", Todo.class)
              .setParameter("id", id)
              .getSingleResult();
    }

    public Todo createTodo(Todo todo) {
        em.persist(todo);
        return todo;
    }

    public Todo updateTodo(Todo todo) {
        return em.merge(todo);
    }

    public void deleteTodo(Long id) {
        final Todo todo = getTodoById(id);
        em.remove(todo);
    }

}

