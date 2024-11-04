package com.example.spring_calendar.v1.repository;

import com.example.spring_calendar.v1.dto.TodoResponseDtoV1;
import com.example.spring_calendar.v1.entity.TodoV1;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcTodoRepositoryV1 implements TodoRepositoryV1 {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTodoRepositoryV1(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public TodoResponseDtoV1 saveTodo(TodoV1 todo) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("todos").usingGeneratedKeyColumns("id")
                .usingColumns("title", "password", "contents", "user_name", "user_id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", todo.getTitle());
        parameters.put("contents", todo.getContents());
        parameters.put("user_name", todo.getUser_name());
        parameters.put("password", todo.getPassword());
        parameters.put("user_id", todo.getUser_id());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return findTodoByIdOrElseThrow(key.longValue());
    }

    @Override
    public TodoResponseDtoV1 findTodoByIdOrElseThrow(Long id) {
        List<TodoResponseDtoV1> result = jdbcTemplate.query("select * from todos where id = ?", todoRowMapper(), id);

        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 Todo가 없습니다."));
    }

    @Override
    public TodoV1 findTodoByIdOrElseThrowIncludePassword(Long id) {
        List<TodoV1> result = jdbcTemplate.query("select * from todos where id = ?", todoRowMapperV2(), id);

        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 Todo가 없습니다."));
    }

    @Override
    public List<TodoResponseDtoV1> findAllTodos() {
        return jdbcTemplate.query("select * from todos ORDER BY updated_at DESC", todoRowMapper());
    }

    @Override
    public List<TodoResponseDtoV1> findAllTodosByUserNameAndUpdatedAt(String userName, String updatedAt) {
        return jdbcTemplate.query("select * from todos WHERE user_name=? AND updated_at > ? ORDER BY updated_at DESC", todoRowMapper(), userName, updatedAt);
    }

    @Override
    public List<TodoResponseDtoV1> findAllTodosByUserName(String userName) {
        return jdbcTemplate.query("SELECT * FROM todos WHERE user_name=? ORDER BY updated_at DESC", todoRowMapper(), userName);
    }

    @Override
    public List<TodoResponseDtoV1> findAllTodosByUpdatedAt(String updatedAt) {
        return jdbcTemplate.query("SELECT * FROM todos WHERE updated_at > ? ORDER BY updated_at DESC", todoRowMapper(), updatedAt);
    }

    @Override
    public int updateTodo(Long id, String title, String contents, String userName) {
        return jdbcTemplate.update("UPDATE todos SET title = ?, contents = ?, user_name = ? WHERE id = ?", title, contents, userName, id);
    }

    @Override
    public int deleteTodo(Long id) {
        return jdbcTemplate.update("DELETE FROM todos WHERE id = ?", id);
    }

    private RowMapper<TodoResponseDtoV1> todoRowMapper() {
        return new RowMapper<TodoResponseDtoV1>() {
            @Override
            public TodoResponseDtoV1 mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new TodoResponseDtoV1(
                        rs.getLong("id"),
                        rs.getString("user_name"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getString("created_at"),
                        rs.getString("updated_at"),
                        rs.getLong("user_id")
                );
            }
        };
    }

    private RowMapper<TodoV1> todoRowMapperV2() {
        return new RowMapper<TodoV1>() {
            @Override
            public TodoV1 mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new TodoV1(
                        rs.getLong("id"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getString("created_at"),
                        rs.getString("updated_at"),
                        rs.getLong("user_id")
                );
            }
        };
    }
}
