package com.vashajava.http.servlet;

import com.vashajava.http.dto.CreateUserDto;
import com.vashajava.http.exception.ValidationException;
import com.vashajava.http.service.UserService;
import com.vashajava.http.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Класс RegistrationServlet - реализует регистрацию.
 *
 * @author Anton Shatkovskiy
 * @created 27.03.2024 г.
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024)
@WebServlet(value = "/registration", name = "RegistrationServlet")
public class RegistrationServlet extends HttpServlet {

  // создаем зависимость на UsersService, т. к. там синглтон - создаем объект
  private final UserService userService = UserService.getInstance();

  // возвращаем JSP страницу
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // код к фильтру с ошибками, выдает страничку 500.jsp, если true
//    if (true) {
//      throw new RuntimeException();
//    }
    // пока хардкодим - это для JSTL страничек,
    // где List.of () — это "заводской" фабричный метод,
    // впервые представленный в Java 9, который создает неизменяемый список, содержащий указанные элементы
    req.setAttribute("roles", List.of("USER", "ADMIN"));
    req.setAttribute("genders", List.of("MALE", "FEMALE"));
    // меняем list.of на соотв. enums: Role.values() и Gender.values()
    req.getRequestDispatcher(JspHelper.getPath("registration"))
        .forward(req, resp);
  }
  // принимаем и обрабатываем форму - т. е. обрабатываем запросы
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // получаем парамтры по ключю name из jsp страницы
    //  var name = req.getParameter("name");

    // код для загрузки файла, но его в итоге установили ниже
    // var image = req.getPart("image");

    // получаем из DTO параметры и передаем на урочень сервисов
    var userDto = CreateUserDto.builder()
        .name(req.getParameter("name"))
        .birthday(req.getParameter("birthday"))
        .image(req.getPart("image"))
        .email(req.getParameter("email"))
        .password(req.getParameter("password"))
        .role(req.getParameter("role"))
        .gender(req.getParameter("gender"))
        .build();

    // тут без цикла try-catch, это вызывает ошибку, поэтому активируем с циклом, а тут закомментим
    // userService.create(userDto);
    // перенаправляем запрос на логин-страничку
    // resp.sendRedirect("/login");

    // создаем и перенаправляем запрос на логин страничку
    try {
      userService.create(userDto);
      resp.sendRedirect("/login");
    } catch (ValidationException exception) {
      req.setAttribute("errors", exception.getErrors());
      doGet(req, resp);
    }
    catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
