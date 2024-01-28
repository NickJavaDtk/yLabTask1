package ru.ylab.security;

import ru.ylab.console.UIclass;
import ru.ylab.exception.IOReadConsoleException;
import ru.ylab.in.ReadConsole;
import ru.ylab.logger.CustomLogger;
import ru.ylab.service.imp.UsersInterfaceImp;
import ru.ylab.users.Users;
import ru.ylab.users.enums.UserRole;

import java.beans.Encoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Класс обеспечивающий регистрацию пользователя
 */

public class RegistrationUser {
    ReadConsole readConsole = new ReadConsole( );
    private final String before = "Вы выбрали 1\nДалее вам необходимо ввести сначала логин, потом пароль, а после также выбрать роль\n" +
            "(Пользователь или Администратор) создаваемого пользователя. Пользователя с ролью Администратор можно создать единожды";
    private final String beforeLogin = "Введите логин пользователя";
    private final String beforePassword = "Введите пароль пользователя";
    private final String beforeRole = "Введите 1 для создания пользователя с обычными привилегиями или введите 2 чтобы создать Администратора";


    /**
     * Метод возвращиющий созданного пользователя
     *
     * @return User возвращает объект типа Users
     * @see ReadConsole#readValue() читает данные с консоли и возвращает обертку типа Object для приведения к
     * соответствующему типу
     * @see CustomLogger#Logger записывает сообщения об успешной регистарации или неправильно введенным логиным паролем
     * или не правильно введенном логине/пароле в память приложения
     * @see RegistrationUser#checkUserName(String) метод для проверки на уникальность имени пользователя
     * @see Base64#getEncoder() метод кодирует пароль создаваемого пользователя
     * @see RegistrationUser#getUserRole() метод проверяет на уникальность пользователя с ролью Администратор
     * @see UIclass#sendConsole(String)  метод выводит сообщения в консоль
     */

    public Users createUsers() {
        String username = "";
        UIclass.sendConsole(before);
        UIclass.sendConsole(beforeLogin);
        while (true) {
            username = (String) readConsole.readValue( );
            if (checkUserName(username)) {
                UIclass.sendConsole("Данное имя пользователя уже присутствует во внутренней памяти\nВведите другое имя пользователя");
                CustomLogger.addMessageLogger("Попытка зарегистрировать пользователя с одинаковым логином");
            } else {
                break;
            }
        }
        UIclass.sendConsole(beforePassword);
        String password = Base64.getEncoder( )
                .encodeToString(((String) readConsole.readValue( )).getBytes( ));
        UIclass.sendConsole(beforeRole);
        UserRole role = getUserRole( );
        return Users.builder( )
                .username(username)
                .password(password)
                .role(role)
                .active(false)
                .build( );

    }

    /**
     * Метод проверят на уникальность пользователя с ролью Администратор
     *
     * @throws IOReadConsoleException при ошибке ввода цифры не указанной в подсказках или при попытке добавить еще одного
     *                                пользователя с ролью Администратор
     * @throws ClassCastException     при ошибке ввода символов или не целых чисел
     * @see ReadConsole#readValue() читает данные с консоли и возвращает обертку типа Object для приведения к
     * соответствующему типу
     * @see CustomLogger#Logger записывает сообщения об ошибках ввода или попытке зарегистрировать еще одно пользователя
     * с ролью Администратор
     */

    private UserRole getUserRole() {
        try {
            Integer value = Integer.valueOf((Integer) readConsole.readValue( ));
            if (value == 1) {
                UserRole roleUser = UserRole.USER;
                return roleUser;
            } else if (value == 2) {
                if (checkRole( )) {
                    CustomLogger.addMessageLogger("Попытка пользователя добавить больше одного администратора");
                    throw new IOReadConsoleException("Вы пытаетесь добавить второго администратора, а в подсказках консоли сказано\n" +
                            "\"Пользователя с ролью Администратор можно создать единожды\"");
                }
                UserRole roleAdmin = UserRole.ADMIN;
                return roleAdmin;
            } else {
                CustomLogger.addMessageLogger("Пользователь на этапе регистрации и выбора роли  вместо цифр 1 или 2 ввел другие цифры");
                throw new IOReadConsoleException("Вы ввели цифру не указанную в диалоге. Просьба ввести или 1 или 2");
            }
        } catch (ClassCastException e) {
            CustomLogger.addMessageLogger("Пользователь на этапе регистрации и выбора роли  вместо цифр 1 или 2 ввел символы");
            throw new IOReadConsoleException("Введено не целое число. Просьба в данном поле ввести или 1 или 2");
        }

    }

    /**
     * Метод проверяющий если ли в памяти проложение логин который пользователь указал при попытке регистрации
     *
     * @param username Принимает значение логина введенного пользователем при регистарации
     * @return boolean возвращает true если введенный логин есть в памяти приложения
     */


    private boolean checkUserName(String username) {
        return Users.usersList.stream( ).anyMatch(user -> user.getUsername( ).equals(username));
    }

    /**
     * Метод проверяющий в памяти приложения если ли пользователь с ролью Администратор
     *
     * @return boolean возвращает true если есть пользователь с ролью Администратор
     */

    //Проверка роли пользователя. По задумке у нас есть только один пользователь с ролью Администратор
    private boolean checkRole() {
        return Users.usersList.stream( ).anyMatch(user -> user.getRole( ).equals(UserRole.ADMIN));
    }
}
