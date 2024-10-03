package com.vashajava.http.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Класс HttpServer - реализует HTTP сервер (типа кок TomCat)
 *
 * @author Anton Shatkovskiy created 21.02.2024 г.
 */

/**
 * HttpServer.
 *
 * @author Anton Shatkovskiy
 * @created 21.02.2024 г.
 */
public class HttpServer {

  // вводим переменную для создания пула соединений на сервере
  private final ExecutorService pool;

  // вводим переменную порт
  private final int port;

  // вводим флаг для обработки потоков, т. е. у нас пул потоков, а не один поток
  private boolean stopped;

  // конструктор с инициализацией порта + включаем аргументы пула соединений - размер пула
  public HttpServer(int port, int poolSize) {
    this.port = port;
    this.pool = Executors.newFixedThreadPool(poolSize);
  }

  // метод для создания сокетов
  public void run() {
    try{
      // создаем соединение на сокете, бронируем порт
      var server = new ServerSocket(port);

      // до тех пор пока не stopped, остановили явно - мы принимаем в цикле все запросы
      while (!stopped) {
        // принимаем соединение, т. е. ожидаем, пока кто то не пришлет запрос,
        // т. к. метод accept() блокирующий
        var socket = server.accept();
        // собственно демонстрация
        System.out.println("Socket accepted");

        // для пула соединений, передаем обработку сокета в пул,
        // где какой то из потоков обработает его
        pool.submit(() -> processSocket(socket));

      }

      // далее обрабатываем соединение, подадим туда сокет
      // processSocket(socket);

    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  // метод для обработки соединения
  private void processSocket(Socket socket) {
    // обрабатываем поток черех t-c с ресурсами, чтобы соед. закрывалось автоматически
    try(socket;
        // step 1 handle request
        var inputStream = new DataInputStream(socket.getInputStream());
        // step 2 handle response
    var outputStream = new DataOutputStream(socket.getOutputStream())) {
      // step 1 handle request - отображаем часть request - 400
      System.out.println("Request: " + new String(inputStream.readNBytes(400)));

      // чтобы был долгим
      Thread.sleep(10000);

      // step 2 handle response - создаем в виде HTML странички
      var body = Files.readAllBytes(Path.of("resources", "example.html"));
      // готовим хедеры, т. е. заголовки, т. е. это HTTP response
      var headers = """
          HTTP/1.1 200 OK
          content-type: text/html
          content-length: %s
          """.formatted(body.length).getBytes();

      outputStream.write(headers);
      outputStream.write(System.lineSeparator().getBytes());
      outputStream.write(body);

    } catch (IOException | InterruptedException ex) {
      // TODO: 21.02.2024 г., log error message
      ex.printStackTrace();
    }
  }

  // метод для остановки сервера
  public void setStopped(boolean stopped) {
    this.stopped = stopped;
  }
}
