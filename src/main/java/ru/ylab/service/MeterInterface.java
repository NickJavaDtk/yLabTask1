package ru.ylab.service;

import ru.ylab.entyties.AbstractMonitoringService;
import ru.ylab.entyties.MetersData;
import ru.ylab.users.Users;
import ru.ylab.users.enums.UserRole;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface MeterInterface {
    /**
     * Метод добавляет в память приложения введенные показатели счетчиков
     *
     * @param metersData передается для добавления в список показателей счетчиков
     * @return boolean возвращает try если показатели счетчика успешно добавлены в память приложения
     */

    Boolean addMeterDataList(MetersData metersData);

    /**
     * Метод возвращает список показателей счетчиков переданного в параметрах пользователя
     *
     * @param user передаем пользователя чтобы увидеть введенные им показатели счетчиков
     * @return List<MetersData> возвращает список показаний счетчиков пользователем переданным в параметрах метода
     */

    List<MetersData> getMeterReader(Users user);

    /**
     * Метод возвращает список показателей счетчиков переданного в параметрах пользователя в зависимости от месяца
     * даты внесения показаний счетчиков
     *
     * @param user  передаем пользователя чтобы увидеть введенные им показатели счетчиков
     * @param month передаем номер месяца
     * @return List<MetersData> возвращает список показаний счетчиков в зависимости от месяца подачи пользователем
     * переданным в параметрах метода
     */

    List<MetersData> getViewMonthMeter(Users user, Integer month);

    /**
     * Метод возвращает карту актуальных показателей счетчиков переданного в параметрах пользователя, где ключ карты
     * это имя счетчика, а значение это показатель счетчика
     *
     * @param user передаем пользователя чтобы увидеть введенные им показатели счетчиков
     * @return Map<String, Integer> возвращает карту показаний счетчиков где ключ это имя счетчика, а значение - показатель
     * счетчика
     */
    Map<String, Integer> getCurrentIndicationMeter(Users user);


}
