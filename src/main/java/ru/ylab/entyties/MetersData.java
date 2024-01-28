package ru.ylab.entyties;

import lombok.*;
import ru.ylab.users.Users;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Данный класс - сущность показаний счетчиков
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MetersData {
    private Users user;
    private LocalDateTime date;
    private AbstractMonitoringService meters;
    /**
     * Данный список хранит показания счетчиков в памяти приложения
     */

    public static List<MetersData> metersDataList = new ArrayList<>( );

    /**
     * Переопределенный метод toString() для лучшего понимания выведенной в консоль информации о счетчиках
     *
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "Показания счетчиков{" +
                " Логин пользователя " + user.getUsername( ) +
                ", Дата показаний " + date.toString( ) +
                ", Название счетчика " + meters.getClass( ).getSimpleName( ) +
                ", Показания счетчика - " + meters.getCounterValue( ) + " " +
                '}';
    }
}
