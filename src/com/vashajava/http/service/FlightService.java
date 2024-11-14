package com.vashajava.http.service;

import com.vashajava.http.dao.FlightDao;
import com.vashajava.http.dto.FlightDto;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс FlightService -
 *
 * @author Anton Shatkovskiy created 05.03.2024 г.
 */

public class FlightService {

  // добавляем паттерн синглтон
  private static final FlightService INSTANCE = new FlightService();

  // фильтр, возвращает из DAO также все перелеты,
  // но с реализацией паттерна синглтон (т. к. по правилам хорошего тона, опять же
  // Service, DAO и пр. должны быть синглтонами)
  private final FlightDao flightDao = FlightDao.getInstance();

  // также добавляем приватный конструктор для для синглтона
  private FlightService() {
  }

  // по правилам хорошего тона - возвращаем Dto, т. е. возвращаем все перелеты
  public List<FlightDto> findAll() {

    // преобразовываем все сущности через стримы
    return flightDao.findAll().stream()
        // flight cущность преобразовываем в FlightDto
        // - до Lombok .map(flight -> new FlightDto(flight.getId() и т. д.
        .map(flight -> FlightDto.builder()
                .id(flight.getId())
                .description(
            // откуда и куда летит + статус
            """
                %s - %s - %s
                """.formatted(flight.getDepartureAirportCode(),
                flight.getArrivalAirportCode(),
                flight.getStatus()))
            .build()
        )
        // коллектим все в список, преобразовываем в статический импорт
        .collect(Collectors.toList());

  }

  // добавляем для синглтона метод гет
 public static FlightService getInstance() {
    return INSTANCE;
 }
}
