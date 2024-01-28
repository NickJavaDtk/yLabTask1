package ru.ylab.service.imp;

import ru.ylab.entyties.AbstractMonitoringService;
import ru.ylab.entyties.MetersData;
import ru.ylab.service.MeterInterface;
import ru.ylab.users.Users;
import ru.ylab.users.enums.UserRole;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MetersDataImp implements MeterInterface {
    @Override
    public Boolean addMeterDataList(MetersData metersData) {
        return MetersData.metersDataList.add(metersData);
    }

    @Override
    public List<MetersData> getMeterReader(Users user) {
        List<MetersData> metersDataList = MetersData.metersDataList.stream( )
                .filter(meter -> meter.getUser( ).getUsername( ).equals(user.getUsername( )))
                .collect(Collectors.toList( ));
        return metersDataList;
    }

    @Override
    public List<MetersData> getViewMonthMeter(Users user, Integer month) {
        List<MetersData> metersData = (user.getRole( ).equals(UserRole.USER)) ? getMeterReader(user) : MetersData.metersDataList;
        List<MetersData> metersDataMonth = metersData.stream( )
                .filter(data -> data.getDate( ).getMonth( ).getValue( ) == month)
                .collect(Collectors.toList( ));
        return metersDataMonth;
    }

    @Override
    public Map<String, Integer> getCurrentIndicationMeter(Users user) {
        List<MetersData> metersData = (user.getRole( ).equals(UserRole.USER)) ? getMeterReader(user) : MetersData.metersDataList;
        List<AbstractMonitoringService> monitoringServices = metersData.stream( )
                .map(MetersData::getMeters)
                .collect(Collectors.toList( ));
        Map<String, Integer> mapMeter = new HashMap<>( );
        for (AbstractMonitoringService monitoring : monitoringServices) {
            String counterName = monitoring.getClass( ).getSimpleName( );
            updateMapValue(mapMeter, counterName, monitoring.getCounterValue( ));
        }
        return mapMeter;
    }


    public void updateMapValue(Map<String, Integer> map, String key, Integer value) {
        if (map.containsKey(key)) {
            map.put(key, map.get(key) + value);
        } else {
            map.put(key, value);
        }
    }


}
