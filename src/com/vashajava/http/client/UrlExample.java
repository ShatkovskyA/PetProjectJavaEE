package com.vashajava.http.client;

import java.io.IOException;
import java.net.URL;

/**
 * Класс UrlExample - реализует класс для реализации методов по протоколоу HTTP для обращения через URL
 *
 * @author Anton Shatkovskiy created 19.02.2024 г.
 */

public class UrlExample {

  public static void main(String[] args) throws IOException {

    // обращаемся к файлу через url и записать все в виде коноекшена и считывать побайтно инфу
    // путь к файлу должен быть прописан
    var url = new URL("file:/Study/ServletsDMDev/src/ru.tern.http/main/java/socket");

    // открываем коннекшн, получаем коннекшн
    var urlConnection = url.openConnection();

    // запихиваем в строку, выводим на печать информацию с файла
    System.out.println(new String(urlConnection.getInputStream().readAllBytes()));

    // checkGoogle();
  }

  // выносим в отдельный файл,
  // в итоге это метод для посещения HTTP серверов и получния внутренних данных с них, таких как, к примеру body, headers и пр. т. п.
  private static void checkGoogle() throws IOException {
    // пишем URL куда обращаемся (это можно и в main вынести)
    var url = new URL("https://www.google.com");

    // открываем соединение, т. е. просто пишем коннекшн - GET запрос
    var urlConnection = url.openConnection();

    // POST запрос, передаем в айтпутстрим - боди
    urlConnection.setDoOutput(true);

    // записываем байты
    try (var outputStream = urlConnection.getOutputStream()) {

    }

    // проверяем что вернет урлсоннекшн в дебаге
    // вообще тут можно и вывести все headers страницы, к примеру https://www.google.com
    System.out.println();

    // далее можем получить следующее
    // urlConnection.getContent();
  }
}
