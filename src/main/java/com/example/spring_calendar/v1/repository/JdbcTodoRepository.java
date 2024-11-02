package com.example.spring_calendar.v1.repository;

import com.example.spring_calendar.v1.dto.TodoResponseDto;
import com.example.spring_calendar.v1.entity.Todo;
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
public class JdbcTodoRepository implements TodoRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTodoRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public TodoResponseDto saveTodo(Todo todo) {
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
    public TodoResponseDto findTodoByIdOrElseThrow(Long id) {
        List<TodoResponseDto> result = jdbcTemplate.query("select * from todos where id = ?", todoRowMapper(), id);

        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 Todo가 없습니다."));
    }

    @Override
    public List<TodoResponseDto> findAllTodos() {
        return jdbcTemplate.query("select * from todos ORDER BY updated_at", todoRowMapper());
    }

    @Override
    public List<TodoResponseDto> findAllTodosByUserNameAndUpdatedAt(String userName, String updatedAt) {
        return jdbcTemplate.query("select * from todos WHERE user_name=? AND updated_at > ? ORDER BY updated_at", todoRowMapper(), userName, updatedAt);
    }

    @Override
    public List<TodoResponseDto> findAllTodosByUserName(String userName) {
        return jdbcTemplate.query("SELECT * FROM todos WHERE user_name=? ORDER BY updated_at", todoRowMapper(), userName);
    }

    @Override
    public List<TodoResponseDto> findAllTodosByUpdatedAt(String updatedAt) {
        return jdbcTemplate.query("SELECT * FROM todos WHERE updated_at > ? ORDER BY updated_at", todoRowMapper(), updatedAt);
    }

    private RowMapper<TodoResponseDto> todoRowMapper() {
        return new RowMapper<TodoResponseDto>() {
            @Override
            public TodoResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new TodoResponseDto(
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
}
