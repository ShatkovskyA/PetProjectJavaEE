package com.vashajava.http.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Класс DispatcherServlet - реализует логику сервлета перенаправления разл. страниц на другие ресурсы.
 *
 * @author Anton Shatkovskiy
 * @created 19.03.2024 г.
 */

@WebServlet("/dispatcher")
public class DispatcherServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // получаем объект с URL куда будем перенапрявлять запрос
    // var reqeustDispatcher = req.getRequestDispatcher("/flights");
    // req.getRequestDispatcher("/flights")
        // второй способ - вызываем forward и пр. сразу
        // .forward(req, resp);
            // .include(req, resp);

    // для проверки записи запроса и отображения при forward и include
//    var writer = resp.getWriter();
//    System.out.println("Hello 2");

    // с redirect
    resp.sendRedirect("/flights");

    // используем атрибуты req, т. е. что то доустановить - т. е. аттрибуты
    // req.setAttribute("1", "234");
    // и далее вызываем метод чтобы перенаправить req либо resp
    // reqeustDispatcher.forward(req, resp);

    // можно получить и через сервлетконтекст, который все знает о всех сервлетах
    // однако с форвардом красивее
    // getServletContext().getRequestDispatcher();

  }
}
