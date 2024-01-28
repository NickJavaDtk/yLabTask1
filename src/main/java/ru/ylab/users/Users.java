package ru.ylab.users;

import lombok.*;
import ru.ylab.users.enums.UserRole;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс сущности пользователь
 */
@Getter
@Setter
@Builder
public class Users {
    private String username;
    private String password;
    private UserRole role;

    private Boolean active;

    /**
     * Данный список хранит список пользователей в памяти приложения
     */
    public static List<Users> usersList = new ArrayList<>( );


}

