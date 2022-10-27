package com.sliit.ead.service;

import com.sliit.ead.model.Queue;
import com.sliit.ead.model.Shed;
import com.sliit.ead.repository.QueueRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * @author Gimhana P.S.
 * @IT_number IT19143682
 */
@Service
public class QueueService {
    private final QueueRepository repository;
    private final ShedService shedService;
    private final MongoTemplate mongoTemplate;

    public QueueService(QueueRepository repository, ShedService shedService, MongoTemplate mongoTemplate) {
        this.repository = repository;
        this.shedService = shedService;
        this.mongoTemplate = mongoTemplate;
    }

    // Method will perform the queue insert function
    public Queue insertQueue(Queue queue) {
        shedService.queueOperation(queue.getRegNo(), queue.getFuelType(), "increment");
        queue.setArrivedTime(LocalDateTime.now());
        return repository.save(queue);
    }

    // Method will perform the queue exist function
    public Queue exitQueue(String id) {
        Queue queue = mongoTemplate.findOne(Query.query(Criteria.where("_id").is(id)), Queue.class);
        queue.setDepartTime(LocalDateTime.now());
        mongoTemplate.save(queue);
        shedService.queueOperation(queue.getRegNo(), queue.getFuelType(), "decrement");
        return queue;
    }

    // Method will perform the queue leave function
    public Queue leaveQueue(String id) {
        Queue queue = mongoTemplate.findOne(Query.query(Criteria.where("_id").is(id)), Queue.class);
        queue.setLeftEarly(true);
        mongoTemplate.save(queue);
        shedService.queueOperation(queue.getRegNo(), queue.getFuelType(), "decrement");
        return queue;
    }

    // Method will return the average waiting time for the given regNo and Fuel type
    public long getAverageWaitingTimeByRegNoAndFuelType(String regNo, String type) {
        List<Queue> queues = repository.findQueuesByRegNoAndFuelType(regNo, type);
        long count = 0;
        long totWait = 0;
        for (Queue queue : queues) {
            if (queue.getArrivedTime().isAfter(LocalDateTime.now().minusHours(1)) && queue.getDepartTime() != null && !queue.getLeftEarly()) {
                long wait = ChronoUnit.MINUTES.between(queue.getArrivedTime(), queue.getDepartTime());
                totWait = totWait + wait;
                count++;
            }
        }
        if (count != 0)
            return totWait/count;
        return 0;
    }
}
