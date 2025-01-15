package com.vashajava.http.entity;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс User - отображение таблицы "users" на сущность
 *
 * @author Anton Shatkovskiy
 * @created 29.03.2024 г.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

  // внутренние приватные переменные, как в таблице
  private Integer id;
  private String name;
  private String image;
  private LocalDate birthday;
  private String email;
  private String password;
  private Role role;
  private Gender gender;

}
