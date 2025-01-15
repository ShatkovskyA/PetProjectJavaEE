package com.vashajava.http.exception;

import com.vashajava.http.validator.Error;
import java.util.List;
import lombok.Getter;

/**
 * Класс ValidationException - реализует логику ошибок.
 *
 * @author Anton Shatkovskiy
 * @created 29.03.2024 г.
 */

public class ValidationException extends RuntimeException {

  // лист ошибок, который создали раньше
  @Getter
  private final List<Error> errors;

  // инициализируем ошибки с помощью конструктора
  public ValidationException(List<Error> errors) {
    this.errors = errors;
  }

  // инициализируем ошибки с помощью конструктора
//  public ValidationException(List<Error> errors) {
//    this.errors = errors;
//  }
}
