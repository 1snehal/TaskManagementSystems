package com.example.demo.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.pojo.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserId(Long userId);
    List<Task> findByStatusAndPriorityAndDueDateBetween(String status, String priority, Date startDate, Date endDate);
    List<Task> findByTitleContainingOrDescriptionContaining(String title, String description);

}
