package com.ee.cruddemo.repositories;

import com.ee.cruddemo.entities.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/*@Repository
@Transactional*/
@RequiredArgsConstructor
public class JdbcTodoRepository {
    private final JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public List<Todo> getAllTodos() {
        final List<Todo> todos = jdbcTemplate.query("select * from todos", new TodoRowMapper());
        return todos;
    }

    @Transactional(readOnly = true)
    public Todo getTodoById(Long id) {
        return jdbcTemplate.queryForObject("select * from todos where id=?", new Object[]{id}, new TodoRowMapper());
    }

    public Todo createTodo(Todo todo) {
        String sql = "insert into todos(content) values(?)";
        jdbcTemplate.update(sql, new Object[]{todo.getContent()});

        return todo;
    }

    public Todo updateTodo(Todo todo) {

        return todo;
    }

    public void deleteTodo(Long id) {

    }

}

class TodoRowMapper implements RowMapper<Todo> {
    @Override
    public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Todo todo = new Todo();
        todo.setId(rs.getLong("id"));
        todo.setContent(rs.getString("content"));
        todo.setCompleted(rs.getBoolean("completed"));
        todo.setCreatedOn(rs.getTimestamp("created_on").toLocalDateTime());
        return todo;
    }
}
