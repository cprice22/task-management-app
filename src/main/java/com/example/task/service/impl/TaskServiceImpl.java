package com.example.task.service.impl;

import com.example.task.domain.UpdateTaskRequest;
import com.example.task.domain.entity.CreateTaskRequest;
import com.example.task.domain.entity.Task;
import com.example.task.domain.entity.TaskStatus;
import com.example.task.exception.TaskNotFoundException;
import com.example.task.repository.TaskRepository;
import com.example.task.service.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(CreateTaskRequest request) {
        //get the time, date, & timezone at the moment
        Instant now = Instant.now();

        //create a new Task entity
        Task newTask = new Task(
                null, //Hibernate to generate an ID
                request.title(),
                request.description(),
                request.dueDate(),
                TaskStatus.OPEN, //Default to an open status
                request.priority(),
                now,
        now);

        //save the task, returning the saved Task to caller
        return taskRepository.save(newTask);
    }

    //implement the List Tasks()
    @Override
    public List<Task> listTasks() {
        return taskRepository.findAll(Sort.by(Sort.Direction.ASC, "Created"));
    }

    //implement update task
    @Override
    public Task updateTask(UUID taskId, UpdateTaskRequest request) {
        //look up the existing task
        //if it doesn't exist throw a TaskNotFoundException
        Task existingTask = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));

        //update the existing task with the provided information
        existingTask.setTitle(request.title());
        existingTask.setDescription(request.description());
        existingTask.setDueDate(request.dueDate());
        existingTask.setPriority(request.priority());
        existingTask.setStatus(request.status());

        //update the existing task's updated value
        existingTask.setUpdated(Instant.now());

        //save the existing task
        return taskRepository.save(existingTask);
    }

    //Implement the delete task method
    @Transactional
    @Override
    public void deleteTask(UUID taskId) {
        taskRepository.deleteById(taskId);
    }
}
