package com.vashajava.http.servlet;

import static java.util.stream.Collectors.toMap;

import com.vashajava.http.dto.FlightDto;
import com.vashajava.http.service.FlightService;
import com.vashajava.http.util.JspHelper;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Класс ContentServlet - для обработки JSP страничек.
 *
 * @author Anton Shatkovskiy
 * @created 26.03.2024 г.
 */
@WebServlet("/content")
public class ContentServlet extends HttpServlet {

  private final FlightService flightService = FlightService.getInstance();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    List<FlightDto> flightDtos = flightService.findAll();
    req.setAttribute("flights", flightDtos);
    // реобразуем в ассоативный массив, исп. статический импорт
    // и запихнем в сешнскоуп
    req.getSession().setAttribute("flightsMap", flightDtos.stream()
        // по ключу получаем значения
        .collect(toMap(FlightDto::getId, FlightDto::getDescription)));

    // отправляем запрос на страничку jsp, указывая пусть от директории web
    // req.getRequestDispatcher("/WEB-INF/jsp/content.jsp")
    req.getRequestDispatcher(JspHelper.getPath("content"))
        // перенапрявляем запрос на обработку другому сервлету
        .forward(req,resp);

  }
}
