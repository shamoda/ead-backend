package com.sliit.ead.controller;

import com.sliit.ead.model.Queue;
import com.sliit.ead.service.QueueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.sliit.ead.dto.mapper.QueueDtoMapper.getQueueDto;

/**
 * @author Gimhana P.S.
 * @IT_number IT19143682
 */
@RestController
@RequestMapping("/api/v1/queue")
@CrossOrigin(origins = "*")
public class QueueController {
    private final QueueService service;

    public QueueController(QueueService service) {
        this.service = service;
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insertQueue(@RequestBody Queue queue) {
        queue.setId(String.valueOf(UUID.randomUUID()));
        return new ResponseEntity<>(getQueueDto(service.insertQueue(queue)), HttpStatus.CREATED);
    }

    @PostMapping("/exit/{id}")
    public ResponseEntity<?> existQueue(@PathVariable String id) {
        return new ResponseEntity<>(service.exitQueue(id), HttpStatus.OK);
    }

    @PostMapping("/leave/{id}")
    public ResponseEntity<?> leaveQueue(@PathVariable String id) {
        return new ResponseEntity<>(service.leaveQueue(id), HttpStatus.OK);
    }

    @GetMapping("/waiting-time/{regNo}/{type}")
    public ResponseEntity<?> getAverageWaitingTimeByRegNoAndFuelType(@PathVariable String regNo, @PathVariable String type) {
        return new ResponseEntity<>(service.getAverageWaitingTimeByRegNoAndFuelType(regNo, type), HttpStatus.OK);
    }
}
