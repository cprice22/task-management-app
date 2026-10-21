package com.example.task.service;

import com.example.task.domain.UpdateTaskRequest;
import com.example.task.domain.entity.CreateTaskRequest;
import com.example.task.domain.entity.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    // Creates a new task
    Task createTask(CreateTaskRequest request);

    //lists all tasks
    List<Task> listTasks();

    //updates the specified task
    Task updateTask(UUID taskId, UpdateTaskRequest request);

    //deletes the specified task
    //does not throw an exception when the specified task does not exist
    void deleteTask(UUID taskId);
}
