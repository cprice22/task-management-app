package com.example.task.controller;
//Handles exceptions thrown by the service layer, returning errors in a standardized format

import com.example.task.domain.dto.ErrorResponseDto;
import com.example.task.exception.TaskNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //Handles MethodArgumentNotValid Exception,
    //returning a standardized error response & an HTTP 400 BAD REQUEST
    //This exception is thrown when a @Valid validation fails
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationException(
            MethodArgumentNotValidException ex
    )
    {
        //Get the first validation error message
        String errorMessage = ex.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .findFirst()
                                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                .orElse("Validation Failed.");

        //Create an ErrorResponseDto using the error message
        ErrorResponseDto errorDto = new ErrorResponseDto(errorMessage);

        //Return the ErrorResponseDto in the response body with an HTTP 400 BAD REQUEST
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    //Handles the TaskNotFoundException, returning a standardized error response & an HTTP 400
    //this exception is thrown when the specified task is not found
    @ExceptionHandler({TaskNotFoundException.class})
    public ResponseEntity<ErrorResponseDto> handleExceptions(
            TaskNotFoundException ex) {

        // Create an ErrorResponseDto using a templated error message.
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                String.format("Task with ID '%s' not found", ex.getId())
        );

        // Return the ErrorResponseDto with status HTTP 400 BAD REQUEST.
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }


}
