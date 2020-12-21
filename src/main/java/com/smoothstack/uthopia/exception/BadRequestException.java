package com.smoothstack.uthopia.exception;

public class BadRequestException extends Exception {
  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private String message;

  public BadRequestException() {
  }

  public BadRequestException(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return this.message;
  }
}
