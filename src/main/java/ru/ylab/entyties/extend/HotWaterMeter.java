package ru.ylab.entyties.extend;

import lombok.Getter;
import lombok.Setter;
import ru.ylab.entyties.AbstractMonitoringService;

import java.time.LocalDateTime;

/**
 * Данный класс - сущность  счетчика горячей воды
 * */
@Setter
@Getter
public class HotWaterMeter extends AbstractMonitoringService {


    /**
     * Конструктор класса унаследованного от абстактного
     * @param counterValue принимает значения показателя счетчика
     * */
    public HotWaterMeter(int counterValue) {
        super(counterValue);
    }
}
