package com.epta.assigment.infraestructure.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final boolean printStackTrace = true;

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                               HttpHeaders headers,
                                                               HttpStatus status,
                                                               WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value()
                , "Validation error. Check 'errors' field for details.");

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errorResponse.addValidationError(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException itemBadrequest, WebRequest request) {
        return buildErrorResponse(itemBadrequest, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(NoSuchElementFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleNoSuchElementFoundException(NoSuchElementFoundException itemNotFoundException, WebRequest request) {
        return buildErrorResponse(itemNotFoundException, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<Object> handleAllUncaughtException(Exception exception, WebRequest request) {

        if (exception.getClass().equals(DataIntegrityViolationException.class)){
            return buildErrorResponse(exception
                    , "you are trying to duplicate a record"
                    , HttpStatus.BAD_REQUEST
                    , request);
        }else{
            return buildErrorResponse(exception
                    , "unknown error"
                    , HttpStatus.INTERNAL_SERVER_ERROR
                    , request);
        }

    }

    private ResponseEntity<Object> buildErrorResponse(Exception exception,
                                                      HttpStatus httpStatus,
                                                      WebRequest request) {
        return buildErrorResponse(exception, exception.getMessage(), httpStatus, request);
    }

    private ResponseEntity<Object> buildErrorResponse(Exception exception,
                                                      String message,
                                                      HttpStatus httpStatus,
                                                      WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), message);

        if (printStackTrace) {
            errorResponse.setStackTrace(ExceptionUtils.getStackTrace(exception));
            errorResponse.setErrorMessage(ExceptionUtils.getMessage(exception));
        }
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }

    @Override
    public ResponseEntity<Object> handleExceptionInternal(
            Exception ex,
            Object body,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        return buildErrorResponse(ex, status, request);
    }
}
