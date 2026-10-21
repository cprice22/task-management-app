package com.example.task.controller;

//REST API controller for tasks

import com.example.task.domain.UpdateTaskRequest;
import com.example.task.domain.dto.CreateTaskRequestDto;
import com.example.task.domain.dto.TaskDto;
import com.example.task.domain.dto.UpdateTaskRequestDto;
import com.example.task.domain.entity.CreateTaskRequest;
import com.example.task.domain.entity.Task;
import com.example.task.mapper.TaskMapper;
import com.example.task.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/tasks")
public class TaskController {

    //the task service from the service layer
    private final TaskService taskService;

    //the task mapper
    private final TaskMapper taskMapper;

    //Constructs a new TaskController
    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    //this is our endpoint to create a task
    // Create task endpoint method
    @PostMapping
    public ResponseEntity<TaskDto> createTask(
            @Valid @RequestBody CreateTaskRequestDto createTaskRequestDto)
    {
        //map the CreateTaskRequestDto to a CreateTaskRequest
        CreateTaskRequest taskToCreate = taskMapper.fromDto(createTaskRequestDto);

        //Call createTask on the TaskService, passing the CreateTaskRequest as an argument
        Task createdTask = taskService.createTask(taskToCreate);

        //Map the newly created Task object into a TaskDto
        TaskDto createdTaskDto = taskMapper.toDto(createdTask);

        //Return the TaskDto object to the caller with an HTTP 201 CREATED
        return new ResponseEntity<>(createdTaskDto, HttpStatus.CREATED);
    }

    //endpoint to list tasks
    //lists all tasks with an HTTP 200 OK
    @GetMapping
    public ResponseEntity<List<TaskDto>> listTasks() {
        //call the listTasks() on the TaskService to get a list of tasks
        List<Task> tasks = taskService.listTasks();

        //map the list of Task objects to a list of TaskDto objects.
        List<TaskDto> taskDtoList = tasks.stream().map(taskMapper::toDto).toList();

        //return the list of TaskDto objects with an HTTP 200 OK
        return ResponseEntity.ok(taskDtoList);
    }

    //Implement the Update Task Endpoint
    //updates the specified task with the provided information
    @PutMapping(path = "/{taskId}")
    public ResponseEntity<TaskDto> updateTask(
            @PathVariable UUID taskId,
            @Valid @RequestBody UpdateTaskRequestDto updateTaskRequestDto
    )
    {
        //map the UpdateTaskRequestDto to an UpdateTaskRequest
        UpdateTaskRequest updateTaskRequest = taskMapper.fromDto(updateTaskRequestDto);

        //pass the UpdateTaskRequest to the TaskService's updateTask()
        Task updatedTask = taskService.updateTask(taskId, updateTaskRequest);

        //map the Task to a TaskDto
        TaskDto taskMapperDto = taskMapper.toDto(updatedTask);

        //return the TaskDto with an HTTP 200
        return ResponseEntity.ok(taskMapperDto);
    }

    //delete task endpoint
    //deletes the specified task
    //always returns an HTTP 204 NO CONTENT
    @DeleteMapping(path = "/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID taskId) {
        //call the deleteTask method on the TaskService
        taskService.deleteTask(taskId);

        //return an HTTP 204 NO CONTENT
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
