package br.lucasmsf.aded.application;

import br.lucasmsf.aded.application.dto.FieldErrorResponse;
import br.lucasmsf.aded.application.dto.MessageResponse;
import br.lucasmsf.aded.application.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class BaseControllerAdvice {
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<MessageResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        var messageResponse = new MessageResponse();
        messageResponse.setMessage(ex.getMessage());
        return new ResponseEntity<>(messageResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BindException.class)
    public ResponseEntity<List<FieldErrorResponse>> handleBindException(BindException ex) {
        var fieldErrorResponseList = ex.getFieldErrors()
                .stream()
                .map(fieldError -> {
                    var fieldErrorResponse = new FieldErrorResponse();
                    fieldErrorResponse.setMessage(fieldError.getDefaultMessage());
                    fieldErrorResponse.setField(fieldError.getField());
                    return fieldErrorResponse;
                })
                .toList();

        return new ResponseEntity<>(fieldErrorResponseList, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
