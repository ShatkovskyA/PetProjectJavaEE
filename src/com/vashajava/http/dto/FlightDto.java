package com.vashajava.http.dto;

import lombok.Builder;
import lombok.Value;

/**
 * Класс FlightDto - реализует шаблон проектирования DTO
 * служащий промежуточным слоем передачи данных из одного слоя в другой
 *
 * @author Anton Shatkovskiy created 05.03.2024 г.
 */

// после интеграции Lombok комментим геттеры, сеттеры, икуалс, хэшкод и т. п., ставим аннотации
//@AllArgsConstructor
//@Getter
//@EqualsAndHashCode
//@ToString
@Value
@Builder
public class FlightDto {

  // Id
  Long id;
  // дескрипшен перелета, указывает откуда и куда летит + статус перелета
  String description;

  // т. к. неизм., т. е. имьютубл, то помечаем поля как final
  // Id
  // комментим, т. к. есть аннотация Lombok @Value
//  private final Long id;
  // дескрипшен перелета, указывает откуда и куда летит + статус перелета
//  private final String description;

  // далее генерируем шаблонный код - конструктор, геттеры (сеттеры не нужны, т. к. имьютубл)
  // комментим, т. к. есть аннотация
//  public FlightDto(Long id, String description) {
//    this.id = id;
//    this.description = description;
//  }

  // комментим, т. к. есть аннотация
//  public Long getId() {return id;}
//  public String getDescription() {return description;}

  // комментим, т. к. есть аннотация
//  @Override
//  public boolean equals(Object o) {
//    if (this == o) {
//      return true;
//    }
//    if (o == null || getClass() != o.getClass()) {
//      return false;
//    }
//    FlightDto flightDto = (FlightDto) o;
//    return Objects.equals(id, flightDto.id) && Objects.equals(description, flightDto.description);
//  }

  // комментим, т. к. есть аннотация
//  @Override
//  public int hashCode() {
//    return Objects.hash(id, description);
//  }

  // комментим, т. к. есть аннотация
//  @Override
//  public String toString() {
//    return "FlightDto{" +
//        "id=" + id +
//        ", description='" + description + '\'' +
//        '}';
//  }
}
