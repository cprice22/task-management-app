package com.example.task.domain;

import com.example.task.domain.entity.TaskPriority;
import com.example.task.domain.entity.TaskStatus;

import java.time.LocalDate;

//models a request to update an existing task
public record UpdateTaskRequest(
        String title,
        String description,
        LocalDate dueDate,
        TaskStatus status,
        TaskPriority priority
) {

}
