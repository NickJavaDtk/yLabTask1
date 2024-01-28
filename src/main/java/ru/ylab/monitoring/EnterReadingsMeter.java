package ru.ylab.monitoring;

import ru.ylab.console.UIclass;
import ru.ylab.entyties.AbstractMonitoringService;
import ru.ylab.entyties.MetersData;
import ru.ylab.entyties.extend.ColdWaterMeter;
import ru.ylab.entyties.extend.HeatingMeter;
import ru.ylab.entyties.extend.HotWaterMeter;
import ru.ylab.exception.IOReadConsoleException;
import ru.ylab.in.ReadConsole;
import ru.ylab.logger.CustomLogger;
import ru.ylab.service.imp.UsersInterfaceImp;
import ru.ylab.users.enums.UserRole;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс обеспечивающий ввод показаний счетчиков
 */
public class EnterReadingsMeter {
    ReadConsole readConsole = new ReadConsole( );
    UsersInterfaceImp usersInterfaceImp = new UsersInterfaceImp( );

    private static final String STARTBEFORE = "Для ввода показаний счетчиков необходимо ввести дату показаний в формате \"dd.MM.YYYY\" например," +
            "31.12.2023, выбрать тип счетчика\nи ввести целое число его показания";
    private static final String START = "Введите 1 для ввода показаний счетчиков 0 для выхода";
    private static final String BEFOREDATE = "Введите дату показаний";

    private static final String COUNTERTYPE = "Выберите 1 для ввода показаний счетчика отопления 2 для ввода показаний счетчика горячей воды\n" +
            "3 для ввода показаний счетчика холодной воды";

    private static final String BEFORECOUNT = "Введите значение показаний счетчика";


    /**
     * Метод обесчиваюший ввод показателей счетчиков
     *
     * @return MetersData возвращает объект класса показания счетчиков
     * @see UIclass#sendConsole(String) метод отправляет сообщения в консоль      *
     * @see UsersInterfaceImp#getUserByIsActive() метод возвращает авторизованного пользователя
     * @see ReadConsole#readDate() метод возвращает объект типа LocalDateTime
     * @see EnterReadingsMeter#getCounterType() метод возращает карту где ключ имя счетчика значение - значение счетчика
     * @see ReadConsole#readValue() метод возвращает обертку мы приводим к соответствуюещему классу
     */
    public MetersData addReadingMeter() {
        MetersData metersData = new MetersData( );
        metersData.setUser(usersInterfaceImp.getUserByIsActive( ));
        HeatingMeter heatingMeter;
        HotWaterMeter hotWaterMeter;
        ColdWaterMeter coldWaterMeter;
        UIclass.sendConsole(UIclass.DELIMITER);
        UIclass.sendConsole(STARTBEFORE);
        UIclass.sendConsole(START);
        UIclass.sendConsole(BEFOREDATE);
        LocalDateTime date = readConsole.readDate( );
        metersData.setDate(date);
        UIclass.sendConsole(COUNTERTYPE);
        Map<Integer, AbstractMonitoringService> counterType = getCounterType( );
        UIclass.sendConsole(BEFORECOUNT);
        Integer value = (Integer) readConsole.readValue( );
        if (counterType.containsKey(1)) {
            heatingMeter = new HeatingMeter(value);
            metersData.setMeters(heatingMeter);
        } else if (counterType.containsKey(2)) {
            hotWaterMeter = new HotWaterMeter(value);
            metersData.setMeters(hotWaterMeter);
        } else if (counterType.containsKey(3)) {
            coldWaterMeter = new ColdWaterMeter(value);
            metersData.setMeters(coldWaterMeter);
        }
        return metersData;
    }

    /**
     * Метод присваивает значение счетчика определенному классу счетчика и возвращает карту из ключа - название счетчика
     * и значение - показателя счетчика
     *
     * @return Map<Integer, AbstractMonitoringService> возвращает карту
     * @throws IOReadConsoleException при ошибке ввода цифры не указанной в подсказках
     * @throws ClassCastException     при ошибке ввода символов или не целых чисел
     * @see ReadConsole#readValue() возращает обертку типа Object и далее мы приводим ее к соотвествующему классу
     */

    private Map<Integer, AbstractMonitoringService> getCounterType() {
        Map<Integer, AbstractMonitoringService> mapCounter = new HashMap<>( );
        try {
            Integer value = Integer.valueOf((Integer) readConsole.readValue( ));
            if (value == 1) {
                mapCounter.put(value, new HeatingMeter(0));
                return mapCounter;
            } else if (value == 2) {
                mapCounter.put(value, new HotWaterMeter(0));
                return mapCounter;
            } else if (value == 3) {
                mapCounter.put(value, new ColdWaterMeter(0));
                return mapCounter;
            } else {
                CustomLogger.addMessageLogger("Пользователь на этапе выбора ввода показаний счетчика и выбора счетчика  вместо цифр 1 или 2 или 3 ввел другие цифры");
                throw new IOReadConsoleException("Вы ввели цифру не указанную в диалоге. Просьба ввести или 1 или 2 или 3");
            }
        } catch (ClassCastException e) {
            CustomLogger.addMessageLogger("Пользователь на этапе выбора ввода показаний счетчика и выбора счетчика  вместо цифр 1 или 2 или 3 ввел символы");
            throw new IOReadConsoleException("Введено не целое число. Просьба в данном поле ввести или 1 или 2 или 3");
        }
    }


}
