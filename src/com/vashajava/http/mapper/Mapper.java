package com.vashajava.http.mapper;

/**
 * Класс Mapper - реализут методы преобразование одного типа в другой тип.
 *
 * @author Anton Shatkovskiy
 * @created 28.03.2024 г.
 */

public interface Mapper<F, T> {

 // метод для преоборазования из одного в другое - заготовка
 T mapFrom(F object);

}
