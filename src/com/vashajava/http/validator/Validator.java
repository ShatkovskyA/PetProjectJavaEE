package com.vashajava.http.validator;

/**
 * Класс Validator - интерефейс с методами для валидации
 * вводимых пользователем данных.
 *
 * @author Anton Shatkovskiy
 * @created 28.03.2024 г.
 */

public interface Validator<T> {

  // возвращает true либо false в зависимости от того валидный объект или нет
  // boolean isValid(T object);

  ValidationResult isValid(T object);

}
