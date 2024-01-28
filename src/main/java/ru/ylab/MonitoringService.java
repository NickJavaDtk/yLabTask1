package ru.ylab;

import ru.ylab.console.UIclass;
import ru.ylab.logger.CustomLogger;


public class MonitoringService {
    /**
     * В точке вызова программы создается объект класса UIclass у которого вызываются методы стадии регистрации и авторизации
     * пользователя
     * @see CustomLogger#addMessageLogger(String) добавляем в логгер сообщение о выходе из приложения
     * @see System#out# вызывается метод println() чтобы отправить сообщение пользователю в консоль о выходе из приложения
     */
    public static void main(String[] args) {
        UIclass ui = new UIclass( );

        while (true) {
           Boolean b = ui.stageRegistrationUser();
           if (!b) {
               break;
           }
        }
        while (true) {
            Boolean b = ui.stageAuthenticationUser();
            if (!b) {
                break;
            }
        }
        CustomLogger.addMessageLogger("Совершен выход из приложения");
        System.out.println("Вы вышли из приложения");
    }


}
