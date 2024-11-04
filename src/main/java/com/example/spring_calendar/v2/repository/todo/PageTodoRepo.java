package com.example.spring_calendar.v2.repository.todo;

import com.example.spring_calendar.v2.entity.todo.Todos;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

@NonNullApi
public interface PageTodoRepo extends PagingAndSortingRepository<Todos, Long> {
    Page<Todos> findAll(Pageable pageable);
}
