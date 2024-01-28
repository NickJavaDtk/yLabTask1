package ru.ylab.service;

import ru.ylab.users.Users;

import java.util.List;

public interface UsersInterface {

    /**
     * Метод добавляет в память приложения введенные пользователя
     *
     * @param user передается для добавления в список пользователей
     * @return boolean возвращает try если пользователь успешно добавлен
     */
    Boolean addUser(Users user);

    /**
     * Метод возвращает пользователя по введенному логину
     *
     * @param username передается логин пользователя
     * @return Users возвращает пользователя или null
     */

    Users getUserByLogin(String username);

    /**
     * Метод возвращает пользователя который активен (авторизовался)
     *
     * @return Users возвращает пользователя или null
     */

    Users getUserByIsActive();

    /**
     * Метод устанавливает признак пользователю переданному в параметрах что он активен (авторизован)
     *
     * @param user передается пользователь чтобы ему установить признак активности (авторизованности)
     */
    void updateUserEntrance(Users user);

    /**
     * Метод устанавливает признак пользователю переданному в параметрах что он не активен (вышел из авторизации)
     *
     * @param user передается пользователь чтобы ему установить признак не активности
     */

    void updateUserExit(Users user);


}
