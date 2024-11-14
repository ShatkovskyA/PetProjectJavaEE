package com.vashajava.http.dto;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Value;

/**
 * Класс UserDto - создан для работы с сессиями.
 *
 * @author Anton Shatkovskiy
 * @created 18.03.2024 г.
 */

@Value
@Builder
public class UserDto {

  Integer id;
  String name;
  String email;
  LocalDate birthday;
  String image;
//  Role role;
//  Gender gender;

}
