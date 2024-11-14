package com.vashajava.http.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Класс DatagramRunner - реализует взаимодействие по протоколу UDP
 * просто отправляет датаграм пакеты, а сервер принимает эти пакет
 *
 * @author Anton Shatkovskiy created 19.02.2024 г.
 */

public class DatagramRunner {

  public static void main(String[] args) throws IOException {

    // кому передаем пакет, адрес
    var inetAddress = InetAddress.getByName("localhost");

    // также оборачиваем в try-catch ресурсы
    try (var datagramSocket = new DatagramSocket()) {

      // ------> [buffer] <------, указываем размер
      // var bytes = new byte[512];

      // представляем то, что мы хотим отправить, как массив байт
      var bytes = "Hello from masa fucker UDP client".getBytes();

      // исп. датаграм пакеты для отправки и получения - send отправка пакета на сервер, класс DatagramServerRunner
      DatagramPacket packet = new DatagramPacket(bytes, bytes.length, inetAddress, 7777);
      datagramSocket.send(packet);
    }
  }
}
