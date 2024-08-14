package com.gokapture.taskmanagement.repository;

import com.gokapture.taskmanagement.enums.Priority;
import com.gokapture.taskmanagement.enums.Status;
import com.gokapture.taskmanagement.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task, Long>, CrudRepository<Task, Long>
{
    List<Task> findByTitleOrDescription(String title, String description);
    List<Task> findByStatusAndPriority(Status status, Priority priority);
}

