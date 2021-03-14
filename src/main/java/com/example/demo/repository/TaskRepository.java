package com.example.demo.repository;

import com.example.demo.entities.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE t.title = ?1")
    Optional<Task> findTaskByTitle(String title);

}