package com.vashajava.http.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Класс DownloadServlet - демонстрирует реализацию скачивания файла с сайта
 *
 * @author Anton Shatkovskiy created 28.02.2024 г.
 */

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // метод по возврату с исп. определенного контента, кот. применяется в этом случае
    // сначала устанавливаем заголовок, который применяется в случае, если необходимо что то скачать,
    // где s - название хедера, а s1 - значения
    resp.setHeader("Content-Disposition", "attachment; filename=\"filename.txt\"");
    // устанавливаем что это обычный текст
    // resp.setContentType("text/plain");
    // устанавливаем что это json
    resp.setContentType("application/json");
    // и устанавливаем кодировку
    resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

    // собственно скачиваем через класс лоадер - как загрузчик ресурсов
    // DownloadServlet.class.getClassLoader().getResourceAsStream("first.json");

    // метод для передачи файла из ресурсов, но работать не будет, т. к. архив в Томкате
    // для этого надо папку resources объявить как ресурс в корне
    // Files.readAllBytes(Path.of("resources", "first.json"));

    // записываем текстовую информацию в файл
    try(// var printWriter = resp.getWriter();

        // работа с байтами
        var outputStream = resp.getOutputStream();
        // собственно скачиваем через класс лоадер - как загрузчик ресурсов
        // чтобы не хардкодить, скачиваем отсюда
        var stream = DownloadServlet.class.getClassLoader().getResourceAsStream("first.json")) {

      // printWriter.write("Data from servlet!");
      // работа с байтами
      outputStream.write(stream.readAllBytes());
    }
  }
}
