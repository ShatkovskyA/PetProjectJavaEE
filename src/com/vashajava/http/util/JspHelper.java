package com.vashajava.http.util;

import lombok.experimental.UtilityClass;

/**
 * Класс JspHelper - утилитный, добавляет префикс и суффикс к страничке.
 *
 * @author Anton Shatkovskiy
 * @created 26.03.2024 г.
 */
@UtilityClass
public class JspHelper {

  // создаем константу - берем пусть из контекстсервлета
  private static final String JSP_FORMAT = "/WEB-INF/jsp/%s.jsp";

  // метод, который получает путь странички
  public static String getPath(String jspName) {

    return String.format(JSP_FORMAT, jspName);
  }
}
