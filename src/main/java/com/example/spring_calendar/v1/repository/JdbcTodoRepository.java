package com.example.spring_calendar.v1.repository;

import com.example.spring_calendar.v1.dto.TodoResponseDto;
import com.example.spring_calendar.v1.entity.Todo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
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
        System.out.println(key);

        //  TODO: response 되는 created_at, updated_at 이 DB 자동 생성이라 null 값 들어감 -> 조회 쿼리 만든 후 해결 가능
        return new TodoResponseDto(key.longValue(), todo.getUser_name(), todo.getTitle(), todo.getContents(), todo.getCreated_at(), todo.getUpdated_at(), todo.getUser_id());
    }
}
