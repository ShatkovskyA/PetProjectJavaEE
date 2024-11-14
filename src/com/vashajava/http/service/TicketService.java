package com.vashajava.http.service;

import static java.util.stream.Collectors.toList;

import com.vashajava.http.dao.TicketDao;
import com.vashajava.http.dto.TicketDto;
import java.util.List;

/**
 * Класс TicketService - реализует логику получения DTO-шек на слой сервлетов.
 *
 * @author Anton Shatkovskiy
 * @created 19.03.2024 г.
 */
public class TicketService {

  // добавляем паттерн синглтон
  private static final TicketService INCTANCE = new TicketService();
  // внедряем зависимость от DAO
  private final TicketDao ticketDao = TicketDao.getInstance();
  // метод гет + прайвед конструктор
  private TicketService() {
  }

  public static TicketService getInstance() {
    return INCTANCE;
  }

  // реализуем метод, который возвращает все TicketDTO из слоя DTO - метод после интеграции Lombok
  public List<TicketDto> findAllByFindId(Long flightId) {
    return ticketDao.findAllByFlightId(flightId).stream()
        // преобразуем в TicketDTO
        .map(ticket -> TicketDto.builder()
                .id(ticket.getId())
                .flightId(ticket.getFlightId())
                .seatNo(ticket.getSeatNo())
            .build()
        )
        // коллектим в список
        .collect(toList());
  }

  // реализуем метод, который возвращает все TicketDTO из слоя DTO - метод до интеграции Lombok
//  public List<TicketDto> findAllByFindId(Long flightId) {
//    return ticketDao.findAllByFlightId(flightId).stream()
//        // преобразуем в TicketDTO
//        .map(ticket -> new TicketDto(
//            ticket.getId(),
//            ticket.getFlightId(),
//            ticket.getSeatNo()
//        ))
//         // коллектим в список
//        .collect(toList());
//  }

}
