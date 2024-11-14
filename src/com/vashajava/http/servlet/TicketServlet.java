package com.vashajava.http.servlet;

import com.vashajava.http.service.TicketService;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Класс TicketServlet.
 *
 * @author Anton Shatkovskiy
 * @created 19.03.2024 г.
 */

@WebServlet("/tickets")
public class TicketServlet extends HttpServlet {

  // добавляем зависимость на синглтон
  private final TicketService ticketService = TicketService.getInstance();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // передаем параметр TicketId
    var flightId= Long.valueOf(req.getParameter("flightId"));

    // устанавливаем контент тайп, чтобы в хедере не было абракадабры
    resp.setContentType("text/html");
    // + устанавливаем кодировку явно - для ислкючения "крокозябр"
    resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
    // генерируем response
    try(var printWriter = resp.getWriter()) {
      printWriter.write("<h1>Купленные билеты</h1>");
      printWriter.write("<ul>");
      // перечисляем все значения списка, с преобразованием ticketa в строку
      ticketService.findAllByFindId(flightId).forEach(ticketDTO -> printWriter.write("""
          <li>
          %s
          </li>
          """.formatted(ticketDTO.getSeatNo())));
      printWriter.write("</ul>");
    }
    }

}
