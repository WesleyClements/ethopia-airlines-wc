package com.smoothstack.uthopia.controller;

import com.smoothstack.uthopia.exception.BadRequestException;
import com.smoothstack.uthopia.exception.NotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NotFoundException.class)
  public void handleEntityNotFound() {
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(BadRequestException.class)
  public void handleBadRequest() {
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  public void handle(final Exception e) {
    System.out.println(e.getClass());
    System.out.println(e.getMessage());
  }
}
