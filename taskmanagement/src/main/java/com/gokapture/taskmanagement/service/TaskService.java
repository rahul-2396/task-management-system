package com.gokapture.taskmanagement.service;

import com.gokapture.taskmanagement.enums.Priority;
import com.gokapture.taskmanagement.enums.Status;
import com.gokapture.taskmanagement.model.Task;
import com.gokapture.taskmanagement.model.User;
import com.gokapture.taskmanagement.repository.TaskRepository;
import com.gokapture.taskmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService
{
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public Task createTask(Task task)
    {
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }

    public Task updateTask(Task task)
    {
        task.setUpdatedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }

    public Optional<Task> getTaskById(Long id)
    {
        return taskRepository.findById(id);
    }

    public List<Task> getAllTasks()
    {
        return (List<Task>) taskRepository.findAll();
    }

    public void deleteTask(Long id)
    {
        taskRepository.deleteById(id);
    }

    public Task assignTaskToUser(Long taskId, Long userId)
    {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        task.setUser(user);
        return taskRepository.save(task);
    }

    public List<Task> filterTasks(Status status, Priority priority)
    {
        return taskRepository.findByStatusAndPriority(status, priority);
    }

    public List<Task> searchTasks(String keyword)
    {
        return taskRepository.findByTitleOrDescription(keyword, keyword);
    }

    public Page<Task> getTasksWithPaginationAndSorting(int page, int size, String sortBy, boolean ascending)
    {
        Pageable pageable = PageRequest.of(page, size, ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
        return taskRepository.findAll(pageable);
    }
}

