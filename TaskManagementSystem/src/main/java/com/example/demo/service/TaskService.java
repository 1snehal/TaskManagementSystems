package com.example.demo.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.pojo.Task;
import com.example.demo.pojo.User;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;
    
   Date date=new Date(0);

    public Task createTask(Task task, Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow();
        task.setUser(user);
        task.setCreatedAt(date);
        return taskRepository.save(task);
    }

    public List<Task> getTasks(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    public Task updateTask(Long taskId, Task taskDetails) {
        Task task = taskRepository.findById(taskId)
            .orElseThrow();
        
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());
        task.setPriority(taskDetails.getPriority());
        task.setUpdatedAt(date);

        return taskRepository.save(task);
    }

    public void deleteTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
            .orElseThrow();
        taskRepository.delete(task);
    }

    public List<Task> filterTasks(String status, String priority, Date startDate, Date endDate) {
        return taskRepository.findByStatusAndPriorityAndDueDateBetween(status, priority, startDate, endDate);
    }

    public List<Task> searchTasks(String keyword) {
        return taskRepository.findByTitleContainingOrDescriptionContaining(keyword, keyword);
    }
}
