package com.smoothstack.exception;

public class BadRequestException extends Exception {

  private String message;
  /**
   *
   */
  private static final long serialVersionUID = 1L;

  public BadRequestException(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return this.message;
  }
}
