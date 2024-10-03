package com.vashajava.http.util;

/**
 * Класс ConnectionManager - утилитный класс, реализующий соединение
 * т. к. утилитный, то делаем класс final
 * класс  в пакете uti - является подготовкой к тому, чтобы реализовать слой DAO
 *
 * @author Anton Shatkovskiy created 29.02.2024 г.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//import src.ru.tern.http.util.PropertiesUtil;

/**
 * ConnectionManager.
 *
 * @author Anton Shatkovskiy
 * @created 01.03.2024 г.
 */
public final class ConnectionManager {

  // добавляем три проперти как в файле application.properties
  private static final String URL_KEY = "db.url";
  private static final String USER_KEY = "db.user";
  private static final String PASSWORD_KEY = "db.password";

//  private static final String DRIVER_DB = "db.driver";

  // статический блок инициализации для загрузки драйвера в JVM
  static {
    loadDriver();
  }
  // метод для загрузки драйвера через try-catch
  private static void loadDriver() {
    try {
      Class.forName("org.postgresql.Driver");
  } catch (ClassNotFoundException ex) {
    throw new RuntimeException(ex);
    }
  }

  // генерируем private конструктор
  private ConnectionManager() {
  }

  // статический метод для возврата конекшена
  public static Connection get() {
    // прописываем настройки, но можно это вынести и в проперти, это харкордные значения
    // return DriverManager.getConnection("url", "user", "password");

    // получаем настройки, это второй способ из файла + генерируем try-catch
    try {
      return DriverManager.getConnection(
          PropertiesUtil.get(URL_KEY),
          PropertiesUtil.get(USER_KEY),
          PropertiesUtil.get(PASSWORD_KEY));
    } catch (SQLException throwables) {
      throw new RuntimeException(throwables);
    }
  }
}
