package com.vashajava.http.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Класс FirstServlet - реализует HTTPсервлет
 *
 * @author Anton Shatkovskiy created 27.02.2024 г.
 */

@WebServlet("/first")
// новые пути с исп. утилитного класса UrlPath
//@WebServlet(UrlPath.FIRST)
public class FirstServlet extends HttpServlet {

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

    // обработка параметра - одного параметра
    var paramValue = req.getParameter("param");
    // обработка параметра - всех параметов
    var parameterMap = req.getParameterMap();
    System.out.println();

    // получение хедеров(заголовков) по ключу - получение информации о том, откдуа пришел запрос
    // req.getHeader("content-type");
    // req.getHeader("user-agent");

    // получение всех заголовков(хедеров) по итератору, с пом. цикла
    var headerNames = req.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      var header = headerNames.nextElement();
      System.out.println(req.getHeader(header));
    }

    // устанавливаем хедеры + вариант с установкой кодировки
    // resp.setContentType("text/html");
    resp.setContentType("text/html; charset=UTF-8");

    // кастомный заголовок - устанавливаем и передаем
    resp.setHeader("token", "12345");

    // явное указание кодировки, чтобы не выводилась абракадабра
    //resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

    // записываем выходной поток через tru - catch с русурсами
    try(var writer = resp.getWriter())  {
      writer.write("<h1>Hello from First Servlet, maza Fucka!</h2>");
      writer.write("<h1>Привет, первый Servlet, хелло maza Fucka!</h2>");
    }
  }

  // метод для отправки параметров - проверка через Postman (тут ознакомительный раздел как работать с Postman)
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // отправка текстового файла через tru - catch с русурсами
    try (var reader = req.getReader();
        // считываем построчно
        var lines = reader.lines()) {
      // выводим все переданные строки на консоль
      lines.forEach(System.out::println);
    }
  }

    // отправка параметров
    // var parameterMap = req.getParameterMap();
    // отобразили на консоль эти самые параметры, которые отправили
    // System.out.println(parameterMap);

  // удаление ресурсов
  @Override
  public void destroy() {
    super.destroy();
  }
}
