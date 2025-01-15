package com.vashajava.http.validator;

import lombok.Value;

/**
 * Класс Error - реализует ошибки и сообщения об ршибках.
 *
 * @author Anton Shatkovskiy
 * @created 28.03.2024 г.
 */

// имьютбл, при этой аннотации private не нужен
@Value(staticConstructor = "of")
  public class Error {

  String code;
  String message;

}
