package ru.ylab.security;

import ru.ylab.console.UIclass;
import ru.ylab.in.ReadConsole;
import ru.ylab.logger.CustomLogger;
import ru.ylab.service.imp.UsersInterfaceImp;
import ru.ylab.users.Users;

import java.util.Base64;


/**
 * Класс обеспечивающий аутентификацию пользователя
 * */
public class AuthenticationUser {
    ReadConsole readConsole = new ReadConsole();
    UsersInterfaceImp usersInterfaceImp = new UsersInterfaceImp();
    private final String before = "Авторизация пользователя. Вам необходимо ввести сначала логин, потом пароль, после успешного входа\n" +
            "вас ждет приветствие программы. Если комбинация логин и пароль введена неверно программа предложит ввести ее заново";


    /**
     * Метод обеспечивающий аутентификацию (совпадение введенных в консоле логина и пароля с логином и паролем содержащимися
     * в памяти приложения)
     * @see AuthenticationUser#checkIsActiveUser() метод проверяет если активные (авторизованные пользователи) если они
     * есть принудительно обнуляет активность
     * @see ReadConsole#readValue() читает данные с консоли и возвращает обертку типа Object для приведения к
     * соответствующему типу
     * @see UsersInterfaceImp#updateUserEntrance(Users) метод устанавливает признак активности пользователя
     * @see UsersInterfaceImp#getUserByLogin(String) метод возвращает пользователя по его логину
     * @see CustomLogger#Logger записывает сообщения об успешной авторизации или не правильно введенном логине/пароле
     * в память приложения
     * @see UsersInterfaceImp#addUser(Users)  добавляет пользователя в память приложения
     * */
    public void authentication() {
        UIclass.sendConsole(UIclass.DELIMITER);
        UIclass.sendConsole(before);
        checkIsActiveUser();
        while (true) {
            UIclass.sendConsole("Введите логин");
            String username = (String) readConsole.readValue();
            UIclass.sendConsole("Введите пароль");
            String password = (String) readConsole.readValue();
            if (checkUserNamePassword(username, password)) {
                usersInterfaceImp.updateUserEntrance(usersInterfaceImp.getUserByLogin(username));
                CustomLogger.addMessageLogger("Пользователь с логином " + username + " успешно авторизовался");
                UIclass.sendConsole(UIclass.DELIMITER);
                UIclass.sendConsole("Поздравляем! Вы успешно авторизовались");
                break;
            } else {
                CustomLogger.addMessageLogger("Неудачная попытка авторизация с комбинацией логина "
                        + username + " пароля " + password);
                UIclass.sendConsole("Не правильно введен логин или пароль");
            }
        }


    }
    /**
     * Метод проверяет соответствии введенных в консоль имени пользователя и пароля данными которые содержатся в
     * памяти приложения
     * @see UsersInterfaceImp#getUserByLogin(String) метод возвращает пользователя по его логину
     * @see Base64#getDecoder() метод декодирует пароль содержащийся в памяти приложения
     * */

    private boolean checkUserNamePassword(String username, String password) {
        Users user = usersInterfaceImp.getUserByLogin(username);
        if (user != null) {
            String passwordDecode = new String(Base64.getDecoder().decode(user.getPassword().getBytes()));
            return password.equals(passwordDecode);
        }
        return false;
    }


    /**
     * Метод проверяет если активные (авторизованные пользователи) если они есть принудительно обнуляет активность
     * @see UsersInterfaceImp#updateUserExit(Users) метод присваивает полю active класса Users значение false
     * */
    private void checkIsActiveUser() {
        Users activeUser = Users.usersList.stream()
                .filter(user -> user.getActive()).findAny().orElse(null);
        if (activeUser != null) {
            usersInterfaceImp.updateUserExit(activeUser);
            CustomLogger.addMessageLogger("Пользователь с логином " + activeUser.getUsername() + " вышел из системы");
        }
    }
}
