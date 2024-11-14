package com.vashajava.http.dao;

import com.vashajava.http.entity.Ticket;
import com.vashajava.http.util.ConnectionManager;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Класс TicketDao - реализует интерфейс DAO.
 *
 * @author Anton Shatkovskiy
 * @created 13.03.2024 г.
 */

public class TicketDao implements Dao<Long, Ticket> {

  // реализуем паттерн синглтон + добавляем геттер к TicketDao
  private static final TicketDao INSTANCE = new TicketDao();
  // создаем переменную запроса через запрос SQL
  private static final String FIND_ALL_BY_FLIGHT_ID = """
select * from ticket where flight_id = ?
""";

  // cоздаем привед конструктор, кот. защищает TicketDao от случайной инициализации
  // для паттерна синглтон
  private TicketDao() {
  }

  // для петтерна синглтон метод
  public static TicketDao getInstance() {
    return INSTANCE;
  }

  // вернуть все тикеты по фладID
  public List<Ticket> findAllByFlightId(Long flightId) {
    // ооединение с СУБД + запрос SQL
    try (var connection = ConnectionManager.get();
    var preparedStatement = connection.prepareStatement(FIND_ALL_BY_FLIGHT_ID)) {
      preparedStatement.setObject(1, flightId);

      // для возврата резалтсета
      var resultSet = preparedStatement.executeQuery();

      // создаем список
      List<Ticket> tickets = new ArrayList<>();

      // пробегаемся по резалтсеты, до тех пор пока есть тикеты - они создаются
      while (resultSet.next()) {
        // тикеты добавляем в список
        tickets.add(buildTicket(resultSet));

      }

      // возвращаем тикеты
      return tickets;

    } catch (SQLException throwables) {
      throw new RuntimeException(throwables);
    }
  }

  private Ticket buildTicket(ResultSet resultSet) throws SQLException {
    return new Ticket(
        resultSet.getObject("id", Long.class),
        resultSet.getObject("passenger_no", String.class),
        resultSet.getObject("passenger_name", String.class),
        resultSet.getObject("flight_id", Long.class),
        resultSet.getObject("seat_no", String.class),
        resultSet.getObject("cost", BigDecimal.class)
    );
  }

  // переопределяем все методы из интерфейса, но не один не подходит
  @Override
  public List<Ticket> findAll() {
    return null;
  }

  @Override
  public Optional<Ticket> findById(Long id) {
    return Optional.empty();
  }

  @Override
  public boolean delete(Long id) {
    return false;
  }

  @Override
  public void update(Ticket entity) {

  }

  @Override
  public Ticket save(Ticket entity) {
    return null;
  }
}
