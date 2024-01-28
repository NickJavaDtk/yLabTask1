package ru.ylab.entyties.extend;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ylab.entyties.AbstractMonitoringService;

import java.time.LocalDateTime;

/**
 * Данный класс - сущность  счетчика отопления
 * */
@Setter
@Getter
public class HeatingMeter extends AbstractMonitoringService {

    /**
     * Конструктор класса унаследованного от абстактного
     * @param counterValue принимает значения показателя счетчика
     * */

    public HeatingMeter(int counterValue) {
        super(counterValue);
    }





}
