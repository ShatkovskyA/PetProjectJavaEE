package com.vashajava.http.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Класс PreFirstServlet - класс начального уровня для запуска и демонстрации работы servletsa.
 * Дублирует код класса FirstServlet до добавления туда дополнительного функционала
 *
 * @author Anton Shatkovskiy
 * @created 25.09.2024 г.
 */

@WebServlet("/prefirst")
public class PreFirstServlet extends HttpServlet {

  // 3 основных шага
  // инициализация сервлета
  @Override
  public void init(ServletConfig config) throws ServletException {
    // config.getServletContext();
    super.init(config);
  }

  // метод с логикой, но, по правилам хорошего тона - лучше переопределить
  // методы, которые "спрятаны" внутри метода service, а именно doGet / doPost и пр.
  // @Override
//  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    super.service(req, resp);
//  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    // устанавливаем хедеры + вариант с установкой кодировки
     resp.setContentType("text/html");
//  resp.setContentType("text/html; charset=UTF-8");

    // записываем выходной поток через tru - catch с русурсами
    try(var writer = resp.getWriter())  {
      writer.write("<h1>Hello from First Servlet, maza Fucka!</h2>");
    }
  }

   // удаление ресурсов
  @Override
  public void destroy() {
    super.destroy();
  }

}
