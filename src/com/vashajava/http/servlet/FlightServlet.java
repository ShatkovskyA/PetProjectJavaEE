package com.vashajava.http.servlet;

import com.vashajava.http.service.FlightService;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Класс FlightServlet - который будет работать с сущностями Flight
 *
 * @author Anton Shatkovskiy
 * @created 05.03.2024 г.
 */

 @WebServlet("/flights")
 // новые пути с исп. утилитного класса UrlPath
//@WebServlet(UrlPath.FLIGHTS)
public class FlightServlet extends HttpServlet {

  // добавляем зависимость на синглтон
  private final FlightService flightService = FlightService.getInstance();

  // для представления
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // заголовок
    resp.setContentType("text/html");
    // устанавливаем кодировку
    resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
    try (var printWriter = resp.getWriter()) {
      // генерируем страничку
      printWriter.write("<h1>Списко перелетов тут:</h1>");
      printWriter.write("<ul>"); // ul - означает список
      // возвращаем все перелеты, генерируя элементы списка
      flightService.findAll().forEach(flightDto -> {
        // li - означает каждое значение списка
        printWriter.write("""
            <li> 
            <a href="/tickets?flightId=%d">%s</a>
            </li>
             """.formatted(flightDto.getId(), flightDto.getDescription()));
        // передаем Id и Description, href="/tickets?flightId=%d - ссылка на доп. сервлет
      });
      printWriter.write("</ul>");
    }
  }
}
