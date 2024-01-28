package ru.ylab.entyties;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

/**
 * Данный абстрактный класс создан для упрощения внедрения новых типов счетчиков
 */
@AllArgsConstructor
public abstract class AbstractMonitoringService {

    private int counterValue;


    /**
     * Данный метод возращает внесенное показание счетчика
     *
     * @return возращает показания счетчика
     */
    public int getCounterValue() {
        return counterValue;
    }
}
