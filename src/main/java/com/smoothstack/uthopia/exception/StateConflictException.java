package com.smoothstack.uthopia.exception;

public class StateConflictException extends Exception {
  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private String message;

  public StateConflictException() {
  }

  public StateConflictException(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return this.message;
  }

}
