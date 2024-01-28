package ru.ylab.logger;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Данный класс логгирует действия создания, авторизацию, запрос отчетов, выход пользователя
 */
public class CustomLogger {

    /**
     * Список хранящийся в памяти приложения записей логов действий
     */
    public static final List<String> Logger = new ArrayList<>( );

    /**
     * Данный метод добавляет в список хранящийся в памяти действие пользователя
     */
    public static void addMessageLogger(String message) {
        String out = LocalDateTime.now( ) + " " + message;
        Logger.add(out);

    }


}
