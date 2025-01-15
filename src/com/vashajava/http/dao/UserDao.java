package com.vashajava.http.dao;

/**
 * Класс UserDao - осуществляет маппинг DAO (т. е. подготовку данных из СУБД для дальнейшего использования) для Юзера
 *
 * @author Anton Shatkovskiy created 27.03.2024 г.
 */

import static java.sql.Statement.RETURN_GENERATED_KEYS;

import com.vashajava.http.entity.Gender;
import com.vashajava.http.entity.Role;
import com.vashajava.http.entity.User;
import com.vashajava.http.util.ConnectionManager;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import lombok.SneakyThrows;

public class UserDao implements Dao<Integer, User> {

  // реализуем паттерн синглтон + добавляем геттер к TicketDao
  private static final UserDao INSTANCE = new UserDao();

  // запрос SQL для метода save + нжектим SQL
  // все поля, кроме id т. к. id - автогенерируемый
  private static final String SAVE_SQL = "insert into users(name, birthday, email, password, role, gender, image) VALUES (?, ?, ?, ?, ?, ?, ?)";

  // запрос SQL для возвращения пользователя по емайлу и паролю
  private static final String GET_BY_EMAIL_AND_PASSWORD_SQL =
      "select * from users where email = ? and password = ?";

  // метод для получения юзера по логину и паролю
  @SneakyThrows
  public Optional<User> findByEmailAndPassword(String email, String password) {
    // соединение с СУБД
    try (var connection = ConnectionManager.get();
    var preparedStatement = connection.prepareStatement(GET_BY_EMAIL_AND_PASSWORD_SQL)) {
      // устанавливаем поля
      preparedStatement.setString(1, email);
      preparedStatement.setString(2, password);
      // выполняем запрос
      var resultSet = preparedStatement.executeQuery();
      // теперь выполняем проверку
      User user = null;
      // если есть хотя бы одна запись
      if (resultSet.next()) {
        // устанавливаем поля с приминением ломбока
        user = buildEntity(resultSet);
      }
      // в противном случае юзер - нулл - оборачиваем в опшинал, чтобы не было нулпойнт эксепшн
      return Optional.ofNullable(user);
    }
  }
  @Override
  public List<User> findAll() {
    return null;
  }

  @Override
  public Optional<User> findById(Integer id) {
    return Optional.empty();
  }

  @Override
  public boolean delete(Integer id) {
    return false;
  }

  @Override
  public void update(User entity) {

  }

  // реализация метода для регистрации
  // @SneakyThrows - эта аннотация необходима для проброски исключения
  @Override
  @SneakyThrows
  public User save(User entity) {
    // делаем конекшн
    try(var connection = ConnectionManager.get();
        // включаем айди в параметры + статический импорт
      var preparedStatement = connection.prepareStatement(SAVE_SQL, RETURN_GENERATED_KEYS)) {
      preparedStatement.setObject(1, entity.getName());
      preparedStatement.setObject(2, entity.getBirthday());
      preparedStatement.setObject(3, entity.getEmail());
      preparedStatement.setObject(4, entity.getPassword());
      preparedStatement.setObject(5, entity.getRole().name());
      preparedStatement.setObject(6, entity.getGender().name());
      preparedStatement.setObject(7, entity.getImage());

      // выполняем запрос
      preparedStatement.executeUpdate();

      // устанавливаем-генерим id
      var generatedKeys = preparedStatement.getGeneratedKeys();
      generatedKeys.next();
      entity.setId(generatedKeys.getObject("id", Integer.class));

      // возвращаем объект энтити с устновленным id
      return entity;

    }
  }
  // реализуем геттер,  т. к. используем паттерн синглтон
  public static UserDao getInstance() {
    return INSTANCE;
  }

  // в итоге создали отдельный метод для сборки сущности по вводимым полям,
  // отправляем вниз
  private User buildEntity(ResultSet resultSet) throws SQLException {
    return User.builder()
        .id(resultSet.getObject("id", Integer.class))
        .name(resultSet.getObject("name", String.class))
        .birthday(resultSet.getObject("birthday", Date.class).toLocalDate())
        //.image(resultSet.getObject("image", String.class))
        .email(resultSet.getObject("email", String.class))
        .password(resultSet.getObject("password", String.class))
        // т. к. роль - необязательно, то метод find - если ее нет - то orElse(null)
        .role(Role.find(resultSet.getObject("role", String.class)).orElse(null))
        // т. к. gender - обязательно, то valueOf
        .gender(Gender.valueOf(resultSet.getObject("gender", String.class)))
        .build();
  }
}
