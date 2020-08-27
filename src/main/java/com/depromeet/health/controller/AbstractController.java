package com.depromeet.health.controller;

import com.depromeet.health.exception.AlreadyEvaluatedUserException;
import com.depromeet.health.exception.RequestNullPointerException;
import com.depromeet.health.exception.ResponseNullPointerException;
import com.depromeet.health.payload.ErrorResponse;
import com.depromeet.health.payload.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AbstractController {
    protected Response<String> ok() {
        return new Response<>("Success", true);
    }

    protected <T> Response<T> ok(T data) {
        return new Response<>("Message", true, data);
    }

    @ExceptionHandler(RequestNullPointerException.class)
    public ResponseEntity<ErrorResponse> handlerException(RequestNullPointerException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Request Null Pointer Exception", "Request Null Pointer Exception", "You can add not null field");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResponseNullPointerException.class)
    public ResponseEntity<ErrorResponse> handlerException(ResponseNullPointerException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Response Null Pointer Exception", "Rest Template can't get response", "API Request is rejected");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyEvaluatedUserException.class)
    public ResponseEntity<ErrorResponse> handlerException(AlreadyEvaluatedUserException ex) {
        ErrorResponse errorResponse = new ErrorResponse("This User already evaluated this post", "evaluated exception", "check your user id and post id");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
