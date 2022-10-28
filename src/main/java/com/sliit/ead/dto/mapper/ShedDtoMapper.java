package com.sliit.ead.dto.mapper;

import com.sliit.ead.dto.ShedDto;
import com.sliit.ead.model.Shed;

import static com.sliit.ead.util.DateTimeUtil.getDateAsString;

/**
 * @author S.M. Jayasekara
 * @IT_number IT19161648
 */
public class ShedDtoMapper {
    public static ShedDto getShedDto(Shed shed) {
        ShedDto shedDto = new ShedDto();
        shedDto.setName(shed.getName());
        shedDto.setAddress(shed.getAddress());
        shedDto.setDieselAvailable(shed.isDieselAvailable());
        shedDto.setDieselArrivalTime(getDateAsString(shed.getDieselArrivalTime()));
        shedDto.setDieselQueueLength(shed.getDieselQueueLength());
        shedDto.setDieselFinishTime(getDateAsString(shed.getDieselFinishTime()));
        shedDto.setPetrolAvailable(shed.isPetrolAvailable());
        shedDto.setPetrolArrivalTime(getDateAsString(shed.getPetrolArrivalTime()));
        shedDto.setPetrolFinishTime(getDateAsString(shed.getPetrolFinishTime()));
        shedDto.setPetrolQueueLength(shed.getPetrolQueueLength());
        shedDto.setRegNo(shed.getRegNo());
        return shedDto;
    }
}
