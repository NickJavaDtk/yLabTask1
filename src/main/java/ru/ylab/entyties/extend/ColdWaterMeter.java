package ru.ylab.entyties.extend;

import lombok.Getter;
import lombok.Setter;
import ru.ylab.entyties.AbstractMonitoringService;

import java.time.LocalDateTime;


/**
 * Данный класс - сущность счетчика холодной воды
 * */
@Setter
@Getter
public class ColdWaterMeter extends AbstractMonitoringService {

    /**
     * Конструктор класса унаследованного от абстактного
     * @param counterValue принимает значения показателя счетчика
     * */
    public ColdWaterMeter(int counterValue) {
        super(counterValue);
    }
}
