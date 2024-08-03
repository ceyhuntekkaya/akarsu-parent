package com.genixo.akarsu.common.advice;


import com.genixo.akarsu.common.exception.AlreadyExistsException;
import com.genixo.akarsu.common.exception.IdNotAvailableException;
import com.genixo.akarsu.common.exception.NotFoundException;
import com.genixo.akarsu.common.exception.ValidationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BaseRestControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = { NotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundException(
            NotFoundException ex, WebRequest request) {
        String bodyOfResponse = ex.getBaseErrorInfo().getErrorKey();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = { IdNotAvailableException.class})
    protected ResponseEntity<Object> handleIdNotAvailableException(
            IdNotAvailableException ex, WebRequest request) {
        String bodyOfResponse = ex.getBaseErrorInfo().getErrorKey();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = { ValidationException.class})
    protected ResponseEntity<Object> handleValidationException(
            ValidationException ex, WebRequest request) {
        String bodyOfResponse = ex.getBaseErrorInfo().getErrorKey();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = { AlreadyExistsException.class})
    protected ResponseEntity<Object> handleAlreadyExistsException(
            AlreadyExistsException ex, WebRequest request) {
        String bodyOfResponse = ex.getBaseErrorInfo().getErrorKey();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
