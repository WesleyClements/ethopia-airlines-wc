package com.smoothstack.uthopia.exception;

public class NotFoundException extends Exception {
  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private String message;

  public NotFoundException() {
  }

  public NotFoundException(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return this.message;
  }

}
