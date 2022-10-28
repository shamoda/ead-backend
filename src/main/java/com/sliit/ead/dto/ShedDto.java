package com.sliit.ead.dto;

import lombok.*;

import java.time.LocalDateTime;

/**
 * @author S.M. Jayasekara
 * @IT_number IT19161648
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Data
public class ShedDto {
    private String regNo;
    private String name;
    private String address;
    private String petrolArrivalTime;
    private String petrolFinishTime;
    private int petrolQueueLength;
    private boolean petrolAvailable;
    private String dieselArrivalTime;
    private String dieselFinishTime;
    private int dieselQueueLength;
    private boolean dieselAvailable;
    private String password;
}
