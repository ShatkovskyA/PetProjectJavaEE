package com.vashajava.http.dto;

import lombok.Builder;
import lombok.Value;

/**
 * Класс TicketDto - реализует шаблон проектирования DTO.
 *
 * @author Anton Shatkovskiy
 * @created 19.03.2024 г.
 */

// аналогично, после интеграции Lombok комментим геттеры, сеттеры, икуалс, хэшкод и т. п., ставим аннотации
@Value
@Builder
public class TicketDto {

  // создаем переменный в соответствии с СУБД
  Long id;
  // к какому перелету относится тот или иной тикет
  Long flightId;
  String seatNo;

  // после интеграции Lombok комментим геттеры, сеттеры, икуалс, хэшкод и т. п., ставим аннотации
  // создаем переменный в соответствии с СУБД
  // private final Long id;
  // к какому перелету относится тот или иной тикет
  // private final Long flightId;
  // private final String seatNo;


  // генерация кучи boilerplate code
//  public TicketDto(Long id, Long flightId, String seatNo) {
//    this.id = id;
//    this.flightId = flightId;
//    this.seatNo = seatNo;
//  }

  // т. к. DTO, то только getter
//  public Long getId() {
//    return id;
//  }
//
//  public Long getFlightId() {
//    return flightId;
//  }
//
//  public String getSeatNo() {
//    return seatNo;
//  }

//  @Override
//  public boolean equals(Object o) {
//    if (this == o) {
//      return true;
//    }
//    if (o == null || getClass() != o.getClass()) {
//      return false;
//    }
//    TicketDto ticketDTO = (TicketDto) o;
//    return Objects.equals(id, ticketDTO.id) && Objects.equals(flightId, ticketDTO.flightId) && Objects.equals(
//        seatNo, ticketDTO.seatNo);
//  }

//  @Override
//  public int hashCode() {
//    return Objects.hash(id, flightId, seatNo);
//  }
//
//  @Override
//  public String toString() {
//    return "TicketDto{" +
//        "id=" + id +
//        ", flightId=" + flightId +
//        ", seatNo='" + seatNo + '\'' +
//        '}';
//  }
}
