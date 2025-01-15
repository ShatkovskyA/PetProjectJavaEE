package com.vashajava.http.mapper;

import com.vashajava.http.dto.CreateUserDto;
import com.vashajava.http.entity.Gender;
import com.vashajava.http.entity.Role;
import com.vashajava.http.entity.User;
import com.vashajava.http.util.LocalDateFormatter;

/**
 * Класс CreateUserMapper - реализует интерфейс и преобразует CreateUserDto
 * в сущность User.
 *
 * @author Anton Shatkovskiy
 * @created 28.03.2024 г.
 */

public class CreateUserMapper implements Mapper<CreateUserDto, User> {

  // добавляем паттерн синглтон
  private static final CreateUserMapper INCTANCE = new CreateUserMapper();
  // константа для сохранения места хранения картинок
  private static final String IMAGE_FOLDER = "users/";

  // реализация метода для преобразования из CreateUserDto в User
  @Override
  public User mapFrom(CreateUserDto object) {
    return User.builder()
        .name(object.getName())
        // оздаем спец. утилитный класс для обработки и перевода даты т. е. добавляем LocalDateFormatter
        .birthday(LocalDateFormatter.format(object.getBirthday()))
        .image(IMAGE_FOLDER + object.getImage().getSubmittedFileName())
        .email(object.getEmail())
        .password(object.getPassword())
        .gender(Gender.valueOf(object.getGender()))
        .role(Role.valueOf(object.getRole()))
        .build();
  }

  // делаем геттер на синглтон (инстанс)
  public static CreateUserMapper getInstance() {
    return INCTANCE;
  }

}
