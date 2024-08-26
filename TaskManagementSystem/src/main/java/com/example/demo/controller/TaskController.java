package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Task;
import com.example.demo.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/adddata")
    public ResponseEntity<Task> createTask(@RequestBody Task task, @RequestParam Long userId) {
        return ResponseEntity.ok(taskService.createTask(task, userId));
    }

    @GetMapping("/fetchdata")
    public ResponseEntity<List<Task>> getTasks(@RequestParam Long userId) {
        return ResponseEntity.ok(taskService.getTasks(userId));
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task taskDetails) {
        return ResponseEntity.ok(taskService.updateTask(taskId, taskDetails));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.ok("Task deleted successfully");
    }
}
