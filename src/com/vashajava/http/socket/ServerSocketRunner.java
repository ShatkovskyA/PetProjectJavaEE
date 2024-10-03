package com.vashajava.http.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

/**
 * Класс ServerSocketRunner - реализует сервер.
 *
 * @author Anton Shatkovskiy created 15.02.2024 г.
 */
public class ServerSocketRunner {

  public static void main(String[] args) throws IOException {

    // I вариант - базовый:
    // указываем порт для связи и оборачиваем в try cath для закрытия, если не исп.
//    try (var serverSocket = new ServerSocket(7777);
//    // метод accept возвращает клиента (т. е. socket), кот. подключился к серверу
//        var socket = serverSocket.accept();
//        // запись resp клиенту
//        var outputStream = new DataOutputStream(socket.getOutputStream());
//        // ответ клиенту
//        var inputStream = new DataInputStream(socket.getInputStream())) {
//
//      // вывод информации, которую считаем
//       System.out.println("Client request " + inputStream.readUTF());
//       outputStream.writeUTF("Hello Evrybady cool Server!");
//    }

    // II вариант - с отправкой сообщений:
    // указываем порт для связи и оборачиваем в try cath для закрытия, если не исп.
    try (var serverSocket = new ServerSocket(7777);
        // метод accept возвращает клиента (т. е. socket), кот. подключился к серверу
        var socket = serverSocket.accept();
        // запись resp клиенту
        var outputStream = new DataOutputStream(socket.getOutputStream());
        // ответ клиенту
        var inputStream = new DataInputStream(socket.getInputStream());
        // для цикла while, клавиатурный ввод
        var scanner = new Scanner(System.in)) {
      // переменная для цикла while, чтобы по кругу запрос и ответы отсылать и получать
      var request = inputStream.readUTF();
      // пока не пришлют кодовое слово - работу продолжаем
      while (!"stop".equals(request)) {
        // вывод информации, которую считаем
        System.out.println("Client request " + request);
        // запрос для клиента на основе сканера
        var response = scanner.nextLine();
        //  outputStream.writeUTF("Hello Evrybady cool Server!");
        outputStream.writeUTF(response);
        // читаем реквест
        request = inputStream.readUTF();

      }
//
//      // вывод информации, которую считаем
//      // System.out.println("Client request " + inputStream.readUTF());
//      // outputStream.writeUTF("Hello Evrybady cool Server!");
//    }
    }
  }
}
