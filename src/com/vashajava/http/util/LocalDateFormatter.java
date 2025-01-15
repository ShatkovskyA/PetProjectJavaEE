package com.vashajava.http.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import lombok.experimental.UtilityClass;

/**
 * Класс LocalDateFormatter - для обработки и форматирования дат.
 *
 * @author Anton Shatkovskiy
 * @created 28.03.2024 г.
 */

@UtilityClass
public class LocalDateFormatter {

  //переменные
  private static final String PATTERN = "yyyy-MM-dd";
  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

  // метод, преобразующий строку в дату
  public LocalDate format(String date) {
    return LocalDate.parse(date, FORMATTER);
  }

  // делаем валидатор для строки, т. к. метод parse ызывает эксепшн
  // первый вариант
  public boolean isVealid(String date) {
    try {
      // метод вызывает дату и если не было ошибки - true
      format(date);
      return true;
    } catch (DateTimeParseException exception) {
      // в противном случае - false, т. е дата невалидная
      return false;
    }
  }
  // второй вариант, с проверкой на null
//  public boolean isVealid(String date) {
//    try {
//      return Optional.ofNullable(date)
//          .map(LocalDateFormatter::format)
//          .isPresent();
//    } catch (DateTimeParseException exception) {
//      return false;
//    }
//  }

}
