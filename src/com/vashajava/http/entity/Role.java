package com.vashajava.http.entity;

/**
 * Класс Role - перечисление ролей
 *
 * @author Anton Shatkovskiy created 27.03.2024 г.
 */

import java.util.Arrays;
import java.util.Optional;
public enum Role {
  // обычный пользователь
  USER,
  // администратор
  ADMIN;

  // метод проверяет, что роли не существует - вернулся бы Опшинал
  // ИЩЕТ РОЛЬ ПО ЕЕ НАЗВАНИЮ
  public static Optional<Role> find(String role) {
    return Arrays.stream(values())
        .filter(it -> it.name().equals(role))
        .findFirst();
  }
}
