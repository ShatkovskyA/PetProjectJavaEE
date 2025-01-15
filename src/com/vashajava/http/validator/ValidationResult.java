package com.vashajava.http.validator;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

/**
 * Класс ValidationResult - реализующий объекты, кот. позволяют понять прошла валидация или нет,
 * т. е. список ошибок
 *
 * @author Anton Shatkovskiy
 * @created 28.03.2024 г.
 */

public class ValidationResult {

  @Getter
  private final List<Error> errors = new ArrayList<>();

  // метод по добавлению ошибок в список, если нашли ошибку
  public void add(Error error) {
    this.errors.add(error);
  }

  // метод валидирующий список ошибок,т. е. список ошибок пусток - validresult - валидный
  // и можно продолжать выполнение
  public boolean isValid() {
    return errors.isEmpty();
  }

}
