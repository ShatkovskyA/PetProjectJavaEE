package com.vashajava.http.server;

/**
 * Класс HttpServerRunner - реализует проверку сервера HttpServer
 *
 * @author Anton Shatkovskiy created 21.02.2024 г.
 */
/**
 * HttpServerRunner.
 *
 * @author Anton Shatkovskiy
 * @created 21.02.2024 г.
 */
public class HttpServerRunner {

  public static void main(String[] args) {

    // создаем экземпляр HttpServer и запускаем его + для пула включаем кол-во соединений
    var httpServer = new HttpServer(9000, 100);
    httpServer.run();
  }
}
