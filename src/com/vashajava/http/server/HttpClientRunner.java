package com.vashajava.http.server;

import static java.net.http.HttpRequest.BodyPublishers.ofFile;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.util.concurrent.ExecutionException;

/**
 * Класс HttpClientRunner - реализует HTTP отправку запроса на сервер,
 * т. е. это HTTP - клиент
 *
 * @author Anton Shatkovskiy created 21.02.2024 г.
 */

public class HttpClientRunner {

  public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {

    // созадем HTTP клиента
    var httpClient = HttpClient.newBuilder()
        .version(Version.HTTP_1_1)
        .build();

        // создаем запрос с отправкой JSONa
    var request = HttpRequest.newBuilder()
        // куда передаем
        .uri(URI.create("http://localhost:9000"))
        // что передаем
        .header("content-type", "application/json")
        // запрос в виде файла - для Бодипаблишер сделать статитческий импорт
        .POST(ofFile(Path.of("resources", "first.json")))
        .build();

    // var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

    // случай когда пул соединений, отправляем асинхронно несколько запросов
    var response = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    var response2 = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    var response3 = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());

    // выводим на консоль запросы и ответы, в случае пула их нет
    // System.out.println(response.headers());
    // System.out.println(response.body());

    // для того чтобы ожидать их
    System.out.println(response3.get().body());
  }

}
