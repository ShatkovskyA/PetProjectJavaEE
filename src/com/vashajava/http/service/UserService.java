package com.vashajava.http.service;

/**
 * Класс UserService - принимает данные из сервлета регистрация
 *
 * @author Anton Shatkovskiy created 28.03.2024 г.
 */

import com.vashajava.http.dao.UserDao;
import com.vashajava.http.dto.CreateUserDto;
import com.vashajava.http.dto.UserDto;
import com.vashajava.http.exception.ValidationException;
import com.vashajava.http.mapper.CreateUserMapper;
import com.vashajava.http.validator.CreateUserValidator;
import java.sql.SQLException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;


// аннотация на сонструктор приватны т. к. синглтон

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {

  // константа для сохранения места хранения картинок
  private static final String IMAGE_FOLDER = "users/";
  // добавляем паттерн синглтон
  private static final UserService INCTANCE = new UserService();

  // подключаем очередную зависимостИ
  private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
  private final UserDao userDao = UserDao.getInstance();
  private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
  // для загрузки картинок
//  private final ImageService imageService = ImageService.getInstance();

  // зависимость на ЮзерМаппер для аутентификации
//  private final UserMapper userMapper = UserMapper.getInstance();

  // добавляем метод для получения логина пользователя по логину и паролю
  // не забываем обновить все поля, в сущности UserDto
//  public Optional<UserDto> login(String email, String password) {
//    // вызываем из ДАО и преобразовываем сущность в ДТО
//    return userDao.findByEmailAndPassword(email, password)
//        // ссылка на метод
//        .map(userMapper::mapFrom);
//  }

  // метод по созданию юзера, возвращает id сущности из юзердто
  @SneakyThrows
  public Integer create(CreateUserDto usersDto) throws SQLException {

    // ШАГИ:
    // валидируем вводимые данные, все ли соответсвует введенным данным, дата, пользователь и т. п.
    // преобразуем map в сущность, т. е. DTO
    // сохраняем в СУБД
    // возвращаем ID

    // вызываем по очереди
    var validationResult = createUserValidator.isValid(usersDto);
    // проееряем, если validationResult - не валидный, т. е. !, то пробрасываем exp
    if (!validationResult.isValid()) {
      throw new ValidationException(validationResult.getErrors());
    }
    // в противном случае получается юзерэнтити
    var userEntity = createUserMapper.mapFrom(usersDto);
    // сохраняем картинку до сохранении сущности
//    imageService.upload(userEntity.getImage(), usersDto.getImage().getInputStream());
    // сохраняем сущность
    userDao.save(userEntity);
    // и возвращаем id
    return userEntity.getId();
  }

  // делаем геттер на синглтон (инстанс)
public static UserService getInstance() {
  return INCTANCE;
}

}
