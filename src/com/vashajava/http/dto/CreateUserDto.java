package com.vashajava.http.dto;

/**
 * Класс CreateUserDto - создает DTO (для дальнейших преобразований) для User
 *
 * @author Anton Shatkovskiy created 28.03.2024 г.
 */

import jakarta.servlet.http.Part;
import lombok.Builder;
import lombok.Value;

// добавляем эти аннотации lombok, т. к. имьютебл
@Value
@Builder
public class CreateUserDto {
  // т. к. все поля будут в виде стринг, потому что все параметры приходят из запроса
  // в виде пар кл-значение, где оба - строки
  String name;
  String birthday;
  Part image;
  String email;
  String password;
  String role;
  String gender;

}
