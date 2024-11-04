package com.example.spring_calendar.v2.repository.user;

import com.example.spring_calendar.v2.dto.todo.TodoResponseDto;
import com.example.spring_calendar.v2.dto.user.UserResponseDto;
import com.example.spring_calendar.v2.entity.user.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public UserResponseDto saveUser(User user) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("users").usingGeneratedKeyColumns("id")
                .usingColumns("name", "email", "password");

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("name", user.getName());
        parameters.put("email", user.getEmail());
        parameters.put("password", user.getPassword());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        //  TODO: findUserById 구현 후 대체
        return new UserResponseDto(key.longValue(), user.getName(), user.getEmail(), null, null);
    }

    @Override
    public List<TodoResponseDto> getAllUserTodo(Long id) {
        return jdbcTemplate.query("SELECT * FROM todos WHERE user_id = ?", todoRowMapper(), id);
    }

    private RowMapper<TodoResponseDto> todoRowMapper() {
        return (rs, rowNum) -> new TodoResponseDto(
                rs.getLong("id"),
                rs.getString("user_name"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getString("created_at"),
                rs.getString("updated_at"),
                rs.getLong("user_id")
        );
    }
}
