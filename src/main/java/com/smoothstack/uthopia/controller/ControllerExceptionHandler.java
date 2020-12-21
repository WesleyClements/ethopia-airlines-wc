package com.smoothstack.uthopia.controller;

import com.smoothstack.uthopia.exception.BadRequestException;
import com.smoothstack.uthopia.exception.NotFoundException;
import com.smoothstack.uthopia.exception.StateConflictException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {

  Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NotFoundException.class)
  public void handleEntityNotFound(final Exception e) {
    logger.error(e.getClass().toString());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({ BadRequestException.class, HttpMessageNotReadableException.class })
  public void handleBadRequest(final Exception e) {
    logger.error(e.getClass().toString());
  }

  @ResponseStatus(HttpStatus.CONFLICT)
  @ExceptionHandler(StateConflictException.class)
  public ResponseEntity<String> handleConflict(final Exception e) {
    logger.error(e.getClass().toString());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
  }

  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public void handleMethodNotAllowed(final Exception e) {
    logger.error(e.getClass().toString());
  }

  @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
  @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
  public void handleNotAcceptableMediaType(final Exception e) {
    logger.error(e.getClass().toString());
  }

  @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  public void handleUnsupportedMediaType(final Exception e) {
    logger.error(e.getClass().toString());
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  public void handle(final Exception e) {
    logger.error(e.getClass().toString());
  }
}
