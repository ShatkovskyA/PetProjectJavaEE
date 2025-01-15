package com.vashajava.http.validator;

import com.vashajava.http.dto.CreateUserDto;
import com.vashajava.http.entity.Gender;
import com.vashajava.http.util.LocalDateFormatter;

/**
 * Класс CreateUserValidator - реализующий валидатор для CreateUserDTO.
 *
 * @author Anton Shatkovskiy
 * @created 28.03.2024 г.
 */

public class CreateUserValidator implements Validator<CreateUserDto> {

  // добавляем паттерн синглтон
  private static final CreateUserValidator INCTANCE = new CreateUserValidator();


  // реализация метода из интерфейса
  @Override
  public ValidationResult isValid(CreateUserDto object) {
    var validationResult = new ValidationResult();
    // добавляем сюда новую валидацию из LocalDateFormatter
    // если не валидный, то... добавляем, пишем ошибку
    if(!LocalDateFormatter.isVealid((object.getBirthday()))) {
      validationResult.add(Error.of("invalid.birthday", "Birthday is invalid"));
    }

    // заполняем
    if (object.getGender() == null || Gender.valueOf(object.getGender()) == null) {
      // выдает код ошибки
      validationResult.add(Error.of("invalid.gender", "Gender id invalid"));
    }
    return validationResult;
  }

  // реализуем геттер,  т. к. используем паттерн синглтон
  public static CreateUserValidator getInstance() {

    return INCTANCE;
  }
}
