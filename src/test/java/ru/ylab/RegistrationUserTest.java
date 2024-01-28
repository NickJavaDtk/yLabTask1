package ru.ylab;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ylab.console.UIclass;
import ru.ylab.security.RegistrationUser;
import ru.ylab.users.Users;

public class RegistrationUserTest {
    private RegistrationUser registrationUser;
    private UIclass uIclass;

    @BeforeEach
    void preparation() {
        uIclass = new UIclass();
        registrationUser = new RegistrationUser();
    }
    @Test
    public void createUsersTest() {
      Boolean result =  uIclass.stageRegistrationUser();
      Assertions.assertTrue(result);

    }

    @Test
    public void authenticationUserTest() {
        Boolean result = uIclass.stageAuthenticationUser();
        Assertions.assertTrue(result);
    }

}
