package com.vashajava.http.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Класс DatagramServerRunner - реализует сервер для взаимодействия по протоколу UDP
 *
 * @author Anton Shatkovskiy created 19.02.2024 г.
 */


public class DatagramServerRunner {

  public static void main(String[] args) throws SocketException, IOException {

    // передаем порт, на котором будем работать и оборачиваем в try-catch ресурсы
    try (var datagramServer = new DatagramSocket(7777)) {

      // создаем опять буффер c размером
      // тут нюанс - при запуске сервера, а потом запроса, сообщение выводится на консоль с нулями после фразы,
      // это просиходит потому что, раземер буфера 512, и после фразы, все лишнее заполняется нулями
      // TODO: 10.09.2024 - подумать на досуге, как убрать эти нули, практически это не имеет значения, а для красоты может быть
      byte[] buffer = new byte[512];

      // принимаем пакет из класса DatagramRunner
      // buffer.length - размер buffer
      DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
      // где метод receive(packet - это "заводской" библиотечный метода для того чтобы принять пакет
      datagramServer.receive(packet);

      // а теперь получаем данные
      System.out.println(new String(buffer));
    }
  }
}
