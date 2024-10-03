package com.vashajava.http.util;

import java.io.IOException;
import java.util.Properties;
/**
 * Класс PropertiesUtil - утилитный класс
 * т. к. утилитный, то делаем класс final
 * класс  в пакете uti - является подготовкой к тому, чтобы реализовать слой DAO
 *
 * @author Anton Shatkovskiy created 29.02.2024 г.
 */

/**
 * PropertiesUtil.
 *
 * @author Anton Shatkovskiy
 * @created 01.03.2024 г.
 */
public final class PropertiesUtil {

  // создаем объект класса Properties
  private static final Properties PROPERTIES = new Properties();

  // загружаем через класслоайдер PROPERTIES из application.properties
  // через статитческий блок инициализации
  static {
    loadProperties();
  }

  // создаем метод для загрузки application.properties
  private static void loadProperties() {
    // через дефолтный класслоудер получаем доступ к папочкам  цикл try-catch
    try (var inputStream = PropertiesUtil.class.getClassLoader()
        .getResourceAsStream("application.properties")) {
         PROPERTIES.load(inputStream);
    } catch (IOException ex) {
      // эксепшн пробрасываем дальше, т. к. это статический блок инициализации
      throw new RuntimeException(ex);
    }
  }

  // генерируем private конструктор, для того, чтобы нельзя было создать
  // объект этого класса
  private PropertiesUtil() {
  }

  // метод, возвращающий по ключу значения из Properties файла (application.properties)
  public static String get(String key) {
    return PROPERTIES.getProperty(key);
  }

}
