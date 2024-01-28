package ru.ylab.service.imp;

import ru.ylab.exception.IOReadConsoleException;
import ru.ylab.logger.CustomLogger;
import ru.ylab.service.UsersInterface;
import ru.ylab.users.Users;

import java.util.List;

public class UsersInterfaceImp implements UsersInterface {
    @Override
    public Boolean addUser(Users user) {
        Users current = getUserByLogin(user.getUsername( ));
        if (current == null) {
            CustomLogger.addMessageLogger("Пользователь " + user.getUsername( ) + " добавлен во внутреннюю память");
            return Users.usersList.add(user);

        } else {
            return false;
        }

    }

    @Override
    public Users getUserByLogin(String username) {
        return Users.usersList.stream( )
                .filter(users -> users.getUsername( ).equals(username))
                .findAny( ).orElse(null);
    }

    @Override
    public Users getUserByIsActive() {
        return Users.usersList.stream( )
                .filter(users -> users.getActive( ))
                .findAny( ).orElse(null);
    }

    @Override
    public void updateUserEntrance(Users user) {
        Users current = getUserByLogin(user.getUsername( ));
        int index = Users.usersList.indexOf(current);
        current.setActive(true);
        Users.usersList.set(index, current);

    }

    @Override
    public void updateUserExit(Users user) {
        Users current = getUserByLogin(user.getUsername( ));
        int index = Users.usersList.indexOf(current);
        current.setActive(false);
        Users.usersList.set(index, current);
    }

}
