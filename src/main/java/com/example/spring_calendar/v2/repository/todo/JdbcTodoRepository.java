package com.example.spring_calendar.v2.repository.todo;

import com.example.spring_calendar.v2.dto.todo.TodoResponseDto;
import com.example.spring_calendar.v2.dto.todo.TodoResponseDtoWithUser;
import com.example.spring_calendar.v2.dto.user.UserResponseDto;
import com.example.spring_calendar.v2.entity.todo.Todos;
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
    public TodoResponseDto saveTodo(Todos todo) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("todos").usingGeneratedKeyColumns("id")
                .usingColumns("title", "password", "contents", "user_name", "user_id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", todo.getTitle());
        parameters.put("contents", todo.getContents());
        parameters.put("user_name", todo.getUser_name());
        parameters.put("password", todo.getPassword());
        parameters.put("user_id", todo.getUserId());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return findTodoByIdOrElseThrow(key.longValue());
    }

    @Override
    public TodoResponseDto findTodoByIdOrElseThrow(Long id) {
        List<TodoResponseDto> result = jdbcTemplate.query("select * from todos where id = ?", todoRowMapper(), id);

        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 일정입니다."));
    }

    @Override
    public Todos findTodoByIdOrElseThrowIncludePassword(Long id) {
        List<Todos> result = jdbcTemplate.query("select * from todos where id = ?", todoRowMapperV2(), id);

        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 일정입니다."));
    }

    @Override
    public int updateTodo(Todos todo) {
        return jdbcTemplate.update("UPDATE todos SET title = ?, contents = ?, user_name = ? WHERE id = ?", todo.getTitle(), todo.getContents(), todo.getUser_name(), todo.getId());
    }

    @Override
    public int deleteTodo(Long id) {
        return jdbcTemplate.update("DELETE FROM todos WHERE id = ?", id);
    }

    @Override
    public TodoResponseDtoWithUser getTodoWithUser(Long id) {
        List<TodoResponseDtoWithUser> result = jdbcTemplate.query("select * from todos JOIN users ON users.id = todos.user_id where todos.id = ?", todoWithUserRowMapper(), id);

        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 일정입니다."));
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

    private RowMapper<Todos> todoRowMapperV2() {
        return new RowMapper<Todos>() {
            @Override
            public Todos mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Todos(
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

    private RowMapper<TodoResponseDtoWithUser> todoWithUserRowMapper() {
        return (rs, rowNum) -> {
            // UserResponseDto 생성
            UserResponseDto user = new UserResponseDto(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("created_at"),
                    rs.getString("updated_at")
            );

            // TodoResponseDto 생성, UserResponseDto를 포함시킴
            return new TodoResponseDtoWithUser(
                    rs.getLong("id"),
                    rs.getString("user_name"),
                    rs.getString("title"),
                    rs.getString("contents"),
                    rs.getString("created_at"),
                    rs.getString("updated_at"),
                    rs.getLong("user_id"),
                    user
            );
        };
    }
}
