package com.vashajava.http.dao;

import java.util.List;
import java.util.Optional;

/**
 * Класс Dao - общий интерфейс с методами для всех классов в слое DAO
 * с пераметрами айдишник + тип сущности
 *
 * @author Anton Shatkovskiy created 29.02.2024 г.
 */

public interface Dao<K, T> {

  // возвращаем все сущности
  List<T> findAll();

  // возвращаем по Id сущность
  Optional<T> findById(K id);

  // проверка удалили сущнсоть или нет
  boolean delete(K id);

  // передаем и обновляем сущность полностью
  void update(T entity);

  // возврат сохраненной сущности
  T save(T entity);

}
