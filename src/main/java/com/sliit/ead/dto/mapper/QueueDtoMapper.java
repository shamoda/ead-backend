package com.sliit.ead.dto.mapper;

import com.sliit.ead.dto.QueueDto;
import com.sliit.ead.model.Queue;

/**
 * @author S.M. Jayasekara
 * @IT_number IT19161648
 */
public class QueueDtoMapper {
    public static QueueDto getQueueDto(Queue queue) {
        if (queue != null) {
            QueueDto queueDto = new QueueDto();
            queueDto.setId(queue.getId());
            queueDto.setRegNo(queueDto.getRegNo());
            queueDto.setFuelType(queue.getFuelType());
            queueDto.setLeftEarly(queue.getLeftEarly());
            queueDto.setArrivedTime(queueDto.getArrivedTime());
            queueDto.setNic(queueDto.getNic());
            queueDto.setDepartTime(queueDto.getDepartTime());
            return queueDto;
        }
        return null;
    }
}
