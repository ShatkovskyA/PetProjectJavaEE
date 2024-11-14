package com.vashajava.http.servlet;

/**
 * Класс CookieServlet - реализует кукки, считаем уникальное посещение сайта с помощью кук
 *
 * @author Anton Shatkovskiy created 28.02.2024 г.
 */

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// тут путь
@WebServlet("/cookies")
public class CookieServlet extends HttpServlet {

  // вводим константу для проверки - счетчик уникальных пользователей
  private static final String UNIQUE_ID = "userId";
  // создаем счетчик, исп. потокобезопасный класс
  private static final AtomicInteger counter = new AtomicInteger();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    // достаем значение куки, но это будет стринг, т. е. надо его потом парсить поэтому это будет
    // неудобно
    // var cookie = req.getHeader("cookie");

    // поэтому другой вариант, с прямым методом, возвр. массив кук
    var cookies = req.getCookies();
    // проверка, если кукис равен нулл или найдем куку
    if(cookies == null || Arrays.stream(cookies)
        .filter(cookie -> UNIQUE_ID.equals(cookie.getName()))
        // проверяем - нет куки, следовательно пользователь впервые
        .findFirst()
        .isEmpty()) {
      // и следовательно создаем новую куки
      var cookie = new Cookie(UNIQUE_ID, "1");
      // вызываем разные необходимые методы у куки
      // определенный путь
      cookie.setPath("cookies");
      // 15 минут действие куки
      cookie.setMaxAge(15 * 60);
      // секьюрность - только по протоколу HTTP с S - https, минуя JS
      // cookie.setHttpOnly();

      // добавляем в респонз куки, объект типа респонз преобразует куки в хедер
      resp.addCookie(cookie);

      // возвращаем счетчик
      counter.incrementAndGet();
    }
    // добавляем заголовки
    resp.setContentType("text/html");
    // возвращаем счетчик в качестве респонза
    try(var writer = resp.getWriter()) {
      // возвращаем число - это счетчик
      writer.write(counter.get());
    }
  }
}
