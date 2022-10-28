package com.sliit.ead.dto;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

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
public class QueueDto {
    private String id;
    private String regNo;
    private String nic;
    private String fuelType;
    private String arrivedTime;
    private String departTime;
    private Boolean leftEarly;
}
