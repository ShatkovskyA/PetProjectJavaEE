package com.vashajava.http.servlet;

import com.vashajava.http.dto.UserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Класс SessionServlet - реализует проверку работы сессий.
 *
 * @author Anton Shatkovskiy
 * @created 18.03.2024 г.
 */

@WebServlet("/sessions")
public class SessionServlet extends HttpServlet {

  // создаем атрибут сессии
  private static final String USER = "user";

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // получаем сессиию, которая хранится на стороне сервера, в отличии ку
    var session = req.getSession();
    // метод для того, чтобы установить (хранить) какие то значения/атрибуты в сессии
    // session.setAttribute();

    // проверяем создана ли вновь сессия или нет
    // System.out.println(session.isNew());

    // метод для того, чтобы получить какие то значения в сессии
    // с явным преобразованием
    var user = (UserDto)session.getAttribute(USER);
    // проверка на добавление Userd, чтобы увидеть его и не добавить в сессию
    // если null, то создаем
    if (user == null) {
      // без Builder
      // user = new UserDto(25L, "test@gmail.com");
      // т. к. Builder создаем через Builder пользвоателя
      user = UserDto.builder()
          .id(25)
          .email("test@gmail.com")
          .build();
      // после того как создали, кладем в сессию вновь созданного пользователя - массив атрибутов
      session.setAttribute(USER, user);
    }
  }
}
