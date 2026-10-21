package com.example.task.exception;

import java.util.UUID;

//thrown when a task is not found
public class TaskNotFoundException extends RuntimeException{

    //the error message template
    public static final String ERROR_MESSAGE = "Task with ID `%` does not exist";

    //the ID of the task that was not found
    private final UUID id;

    //Constructs a new TaskNotFoundException using the ID of the task not found
    public TaskNotFoundException(UUID id) {
        super(String.format(ERROR_MESSAGE, id));
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
