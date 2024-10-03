package com.vashajava.http.entity;

/**
 * Класс Flight - реализует сущность с полями из СУБД
 * уровень DAO
 *
 * @author Anton Shatkovskiy created 29.02.2024 г.
 */

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Flight.
 *
 * @author Anton Shatkovskiy
 * @created 29.02.2024 г.
 */
public class Flight {
  // маппим таблицу в поля сущности
  private Long id;
  private String flghtNo;
  private LocalDateTime departureDate;
  private String departureAirportCode;
  private LocalDateTime arrivalDate;
  private String arrivalAirportCode;
  private Integer aircraftId;

  // энам - отражает все возможные статусы
  private FlightStatus status;

  // создаем конструктор со всеми параметрами для исп. в других методах
  public Flight(Long id, String flghtNo, LocalDateTime departureDate,
      String departureAirportCode, LocalDateTime arrivalDate,
      String arrivalAirportCode, Integer aircraftId, FlightStatus status) {
    this.id = id;
    this.flghtNo = flghtNo;
    this.departureDate = departureDate;
    this.departureAirportCode = departureAirportCode;
    this.arrivalDate = arrivalDate;
    this.arrivalAirportCode = arrivalAirportCode;
    this.aircraftId = aircraftId;
    this.status = status;
  }

  // генерируем get&set для исп. в других методах
  public Long getId() {return id;}
  public void setId(Long id) {this.id = id;}

  public String getFlghtNo() {return flghtNo;}
  public void setFlghtNo(String flghtNo) {this.flghtNo = flghtNo;}

  public LocalDateTime getDepartureDate() {return departureDate;}
  public void setDepartureDate(LocalDateTime departureDate) {this.departureDate = departureDate;}

  public String getDepartureAirportCode() {return departureAirportCode;}
  public void setDepartureAirportCode(String departureAirportCode) {this.departureAirportCode = departureAirportCode;}

  public LocalDateTime getArrivalDate() {return arrivalDate;}
  public void setArrivalDate(LocalDateTime arrivalDate) {this.arrivalDate = arrivalDate;}

  public String getArrivalAirportCode() {return arrivalAirportCode;}
  public void setArrivalAirportCode(String arrivalAirportCode) {this.arrivalAirportCode = arrivalAirportCode;}

  public Integer getAircraftId() {return aircraftId;}
  public void setAircraftId(Integer aircraftId) {this.aircraftId = aircraftId;}

  public FlightStatus getStatus() {return status;}
  public void setStatus(FlightStatus status) {this.status = status;}

  // генерируем икуалс+хэшкод, но только по id, т. к. идентификатора будет достаточно,
  // т. к. сущности это проекции из СУБД
  @Override
  public boolean equals(Object o) {
    if (this == o) {return true;}
    if (o == null || getClass() != o.getClass()) {return false;}
    Flight flight = (Flight) o;
    return Objects.equals(id, flight.id);
  }

  @Override
  public int hashCode() {return Objects.hash(id);}

  // генерируем toString для отображения на консоль
  @Override
  public String toString() {
    return "Flight{" +
        "id=" + id +
        ", flghtNo='" + flghtNo + '\'' +
        ", departureDate=" + departureDate +
        ", departureAirportCode='" + departureAirportCode + '\'' +
        ", arrivalDate=" + arrivalDate +
        ", arrivalAirportCode='" + arrivalAirportCode + '\'' +
        ", aircraftId=" + aircraftId +
        ", status=" + status +
        '}';
  }
}
