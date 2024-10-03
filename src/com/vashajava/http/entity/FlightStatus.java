package com.vashajava.http.entity;

/**
 * Класс FlightStatus - реализует перечисления из таблицы для сущности
 * уровень DAO
 *
 * @author Anton Shatkovskiy created 29.02.2024 г.
 */
/**
 * FlightStatus.
 *
 * @author Anton Shatkovskiy
 * @created 01.03.2024 г.
 */
public enum FlightStatus {
  // также берутся из таблицы
  ARRIVED,
  DEPARTED,
  CANCELLED,
  SCHEDULED

}
