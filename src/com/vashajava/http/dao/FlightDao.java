package com.vashajava.http.dao;

/**
 * Класс FlightDao - для работы с таблицей.
 *
 * @author Anton Shatkovskiy created 29.02.2024 г.
 */

import com.vashajava.http.entity.Flight;
import com.vashajava.http.entity.FlightStatus;
import com.vashajava.http.util.ConnectionManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightDao implements Dao<Long, Flight> {

  // реализуем паттерн синглтон + добавляем геттер к FlightDao
  private static final FlightDao INSTANCE = new FlightDao();

  // пишем SQL-скрипт для метода findAll, инжектим SQL язык
  private static final String FIND_ALL = """
select  * from flight
""";

  // создаем прайвед конструктор, кот. защищает FlightDao от случайной инициализации
  // для паттерна синглтон
  private FlightDao() {
  }

  @Override
  public List<Flight> findAll() {
    // связываем метод с циклом
   try ( var connection = ConnectionManager.get();
       // ооед. через prepareStatement
   final var prepareStatement = connection.prepareStatement(FIND_ALL)) {
     // вызываем executeQuery() и получаем результат
     var resultSet = prepareStatement.executeQuery();
     // возрващаем весь список, т. к. изначально тоже был список
     List<Flight> flights = new ArrayList<>();
     // до тех пор, пока true
     while (resultSet.next()) {
       // кладем в сущность
       flights.add(buildFlight(resultSet));
     }
     return flights;
   } catch (SQLException ex) {
     throw new RuntimeException(ex);
   }
  }

  @Override
  public Optional<Flight> findById(Long id) {
    return Optional.empty();
  }

  @Override
  public boolean delete(Long id) {
    return false;
  }

  @Override
  public void update(Flight entity) {  }

  @Override
  public Flight save(Flight entity) {return null;}

  // для петтерна синглтон метод
  public static FlightDao getInstance() {return INSTANCE;}

  // создаем метод buildFlight, кот. по resultSet вернет сущность
  private Flight buildFlight(ResultSet resultSet) throws SQLException {

    // вызываем конструктор и передаем все необходимые параметры
    return new Flight(
        resultSet.getObject("id", Long.class),
        resultSet.getObject("flight_no", String.class),
        resultSet.getObject("departure_date", Timestamp.class).toLocalDateTime(),
        resultSet.getObject("departure_airport_code", String.class),
        resultSet.getObject("arrival_date", Timestamp.class).toLocalDateTime(),
        resultSet.getObject("arrival_airport_code", String.class),
        resultSet.getObject("aircraft_id", Integer.class),
        // для преобразования string в FlightStatus
        FlightStatus.valueOf(resultSet.getObject("status", String.class))

    );
  }
}
