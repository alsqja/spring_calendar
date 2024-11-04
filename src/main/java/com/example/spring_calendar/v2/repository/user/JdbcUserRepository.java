package com.example.spring_calendar.v2.repository.user;

import com.example.spring_calendar.v2.dto.todo.TodoResponseDto;
import com.example.spring_calendar.v2.dto.user.UserResponseDto;
import com.example.spring_calendar.v2.entity.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

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

    @Override
    public UserResponseDto findUserByIdOrElseThrow(Long id) {
        return jdbcTemplate.query("SELECT * FROM users WHERE id = ?", userRowMapper(), id)
                .stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당되는 유저가 없습니다."));
    }

    @Override
    public List<UserResponseDto> findAllUser() {
        return jdbcTemplate.query("SELECT * FROM users", userRowMapper());
    }

    @Override
    public UserResponseDto login(String email, String password) {
        return jdbcTemplate.query("SELECT * FROM users WHERE email = ? AND password = ?", userRowMapper(), email, password)
                .stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당되는 유저가 없습니다."));
    }

    @Override
    public User findUserByIdOrElseThrowIncludePassword(Long id) {
        return jdbcTemplate.query("SELECT * FROM users WHERE id = ?", userRowMapperIncludePassword(), id)
                .stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당되는 유저가 없습니다."));
    }

    @Override
    public int updateUser(User user) {
        return jdbcTemplate.update("UPDATE users SET name = ?, email = ? WHERE id = ?", user.getName(), user.getEmail(), user.getId());
    }

    @Override
    public int deleteUser(Long id) {
        return jdbcTemplate.update("DELETE FROM users WHERE id = ?", id);
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

    private RowMapper<UserResponseDto> userRowMapper() {
        return ((rs, rowNum) -> new UserResponseDto(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("created_at"),
                rs.getString("updated_at")
        ));
    }

    private RowMapper<User> userRowMapperIncludePassword() {
        return ((rs, rowNum) -> new User(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("created_at"),
                rs.getString("updated_at")
        ));
    }
}
