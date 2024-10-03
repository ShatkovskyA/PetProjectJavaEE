package com.vashajava.http.dto;

/**
 * Класс FlightDto - реализует шаблон проектирования DTO
 * служащий промежуточным слоем передачи данных из одного слоя в другой
 *
 * @author Anton Shatkovskiy created 05.03.2024 г.
 */

import java.util.Objects;

public class FlightDto {

  // т. к. неизм., т. е. имьютубл, то помечаем поля как final
  // Id
  private final Long id;
  // дескрипшен перелета, указывает откуда и куда летит + статус перелета
  private final String description;

  // далее генерируем шаблонный код - конструктор, геттеры (сеттеры не нужны, т. к. имьютубл)
  public FlightDto(Long id, String description) {
    this.id = id;
    this.description = description;
  }

  public Long getId() {return id;}
  public String getDescription() {return description;}

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FlightDto flightDto = (FlightDto) o;
    return Objects.equals(id, flightDto.id) && Objects.equals(description, flightDto.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, description);
  }

  @Override
  public String toString() {
    return "FlightDto{" +
        "id=" + id +
        ", description='" + description + '\'' +
        '}';
  }
}
