package com.gokapture.taskmanagement.controller;

import com.gokapture.taskmanagement.enums.Priority;
import com.gokapture.taskmanagement.enums.Status;
import com.gokapture.taskmanagement.model.Task;
import com.gokapture.taskmanagement.service.TaskService;
import com.gokapture.taskmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController
{
    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task)
    {
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.ok(createdTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task)
    {
        task.setId(id);
        Task updatedTask = taskService.updateTask(task);
        return ResponseEntity.ok(updatedTask);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id)
    {
        Optional<Task> task = taskService.getTaskById(id);
        return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<Task>> getAllTasks(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size,
                                                  @RequestParam(defaultValue = "id") String sortBy,
                                                  @RequestParam(defaultValue = "true") boolean ascending)
    {
        Page<Task> tasks = taskService.getTasksWithPaginationAndSorting(page, size, sortBy, ascending);
        return ResponseEntity.ok(tasks);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id)
    {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{taskId}/assign/{userId}")
    public ResponseEntity<Task> assignTaskToUser(@PathVariable Long taskId, @PathVariable Long userId)
    {
        Task assignedTask = taskService.assignTaskToUser(taskId, userId);
        return ResponseEntity.ok(assignedTask);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Task>> filterTasks(@RequestParam String keyword)
    {
        List<Task> tasks = taskService.searchTasks(keyword);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Task>> filterTasks(@RequestParam Status status, @RequestParam Priority priority)
    {
        List<Task> tasks = taskService.filterTasks(status, priority);
        return ResponseEntity.ok(tasks);
    }
}

