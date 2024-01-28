package ru.ylab.exception;

/**
 * Класс собственного исключения для вывода информации о ошибке понятной пользователю
 */

public class IOReadConsoleException extends RuntimeException {

    /**
     * Конструктор класса собственного исключения принимает в параметрах строку, которая будет выведена в консоль при
     * при возникновении исключения
     *
     * @param message текст ошибки для вывода в консоль
     */
    public IOReadConsoleException(String message) {
        super(message);
    }
}
