package ru.ylab.in;

import ru.ylab.exception.IOReadConsoleException;
import ru.ylab.logger.CustomLogger;

import java.io.Console;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс отвечающий за работу с консолью, а именно считывания информации с консоли
 */
public class ReadConsole {
    Scanner scanner = new Scanner(System.in);
    String stringDate = "dd.MM.yyyy";


    /**
     * Метод обертка для возвращаемых значений типа String и Integer
     *
     * @throws IOReadConsoleException выводит информацию пользователю что введены вещественные числа и другая иформация
     * @@return Object возвращает обертку в виде объекта Object чтобы ее можно привести к соответствующему классу
     * @see Scanner#hasNextInt() проверка на то, что считанное значение будет целом числом
     * @see Scanner#hasNextDouble() проверка на то, что считанное значение будет вещественынм числом
     * @see Scanner#hasNextFloat() проверка на то, что считанное значение будет вещественном числом
     * @see ReadConsole#readInteger() возвращает в обертку целое число
     * @see ReadConsole#readString() возвращает в обертку строку
     */
    public Object readValue() {
        if (scanner.hasNextInt( )) {
            return readInteger( );
        } else if (scanner.hasNextDouble( ) || scanner.hasNextFloat( )) {
            CustomLogger.addMessageLogger("В консоль введены данные которые приводят к ошибке ввода/вывода");
            throw new IOReadConsoleException("Введены цифры не содержащиеся в подсказках\n" +
                    " или вы пытаетесь ввести в показание счетчиков не целые числа");
        }
        return readString( );
    }

    /**
     * Метод для возврата считанной с консоли строки
     *
     * @return String возвращает значение типа String
     */
    private String readString() {
        String input = scanner.nextLine( );
        return input;
    }

    /**
     * Метод для возврата считанной с консоли целого значения
     *
     * @return String возвращает значение типа Integer
     */

    private Integer readInteger() {
        Integer value = Integer.parseInt(scanner.nextLine( ));
        return value;
    }

    /**
     * Метод для считывания строки с консоли приведению ее к типу LocalDateTime и возврат значения LocalDateTime
     *
     * @return LocalDateTime возвращает значение типа LocalDateTime
     * @throws ClassCastException     исключения при попытке привести считанную строку к формату String
     * @throws ParseException         исключения при попытке привести тип String к типу Date
     * @throws IOReadConsoleException выводит информацию пользователю что введена дата не соответствующая формату
     * @see SimpleDateFormat#parse(String)
     * @see LocalDateTime#ofInstant(Instant, ZoneId)
     */

    public LocalDateTime readDate() {
        String input = scanner.nextLine( );
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(stringDate);
            Date date = simpleDateFormat.parse(input);
            LocalDateTime local = LocalDateTime.ofInstant(date.toInstant( ), ZoneId.systemDefault( ));
            return local;
        } catch (ClassCastException | ParseException e) {
            CustomLogger.addMessageLogger("В консоль введены дата которые не соответствует рекомендуемым  ввода/вывода");
            throw new IOReadConsoleException("Введенная дата не может быть прочитана. Просьба ввести дату в формате dd.MM.yyyy" +
                    "например 31.12.2023,\n если же все-таки ошибка не устраняется введите разделители точки \".\" " +
                    "на английской раскладке");
        }

    }


}
