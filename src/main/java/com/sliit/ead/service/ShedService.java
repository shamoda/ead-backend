package com.sliit.ead.service;

import com.sliit.ead.model.Shed;
import com.sliit.ead.repository.ShedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author S.M. Jayasekara
 * @IT_number IT19161648
 */
@Service
public class ShedService {
    private final ShedRepository repository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public ShedService(ShedRepository repository, MongoTemplate mongoTemplate) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
    }

    // Shed creation method
    public Shed insertShed(Shed shed) {
        return repository.save(shed);
    }

    // Method will return a list of Sheds for the given address
    public List<Shed> getShedByAddress(String address) {
        return repository.findShedsByAddress(address);
    }

    // Shed login method
    public Shed login(String regNo, String password) {
        Shed shed = repository.findById(regNo).get();
        if (shed != null) {
            if (shed.getPassword().equalsIgnoreCase(password)) {
                return shed;
            }
            return null;
        }
        return null;
    }

    // Method will return the Shed with shortest queue for the given Address and Fuel type
    public Shed getShortestQueueByAddressAndType(String address, String type) {
        List<Shed> sheds = getShedByAddress(address);
        Shed tmpShed = sheds.get(0);
        int i = 0;
        for (Shed shed : sheds) {
            if (type.equalsIgnoreCase("petrol") && shed.getPetrolQueueLength() <= tmpShed.getPetrolQueueLength()) {
                if (!shed.isPetrolAvailable())
                    continue;
                tmpShed = shed;
                i++;
            }
            else if (type.equalsIgnoreCase("diesel") && shed.getDieselQueueLength() <= tmpShed.getDieselQueueLength()) {
                if (!shed.isDieselAvailable())
                    continue;
                tmpShed = shed;
                i++;
            }
        }

        if (i == 0)
            return null;
        return tmpShed;
    }

    // Method will update petrol arrival date and time
    public Shed updatePetrolArrived(String regNo) {
        Shed shed = mongoTemplate.findOne(Query.query(Criteria.where("_id").is(regNo)), Shed.class);
        shed.setPetrolArrivalTime(LocalDateTime.now());
        shed.setPetrolAvailable(true);
        shed.setPetrolFinishTime(null);
        mongoTemplate.save(shed);
        return shed;
    }

    // Method will update petrol finished date and time
    public Shed updatePetrolFinished(String regNo) {
        Shed shed = mongoTemplate.findOne(Query.query(Criteria.where("_id").is(regNo)), Shed.class);
        shed.setPetrolArrivalTime(null);
        shed.setPetrolAvailable(false);
        shed.setPetrolFinishTime(LocalDateTime.now());
        mongoTemplate.save(shed);
        return shed;
    }

    // Method will update diesel arrival date and time
    public Shed updateDieselArrived(String regNo) {
        Shed shed = mongoTemplate.findOne(Query.query(Criteria.where("_id").is(regNo)), Shed.class);
        shed.setDieselArrivalTime(LocalDateTime.now());
        shed.setDieselAvailable(true);
        shed.setDieselFinishTime(null);
        mongoTemplate.save(shed);
        return shed;
    }

    // Method will update diesel finished date and time
    public Shed updateDieselFinished(String regNo) {
        Shed shed = mongoTemplate.findOne(Query.query(Criteria.where("_id").is(regNo)), Shed.class);
        shed.setDieselArrivalTime(null);
        shed.setDieselAvailable(false);
        shed.setDieselFinishTime(LocalDateTime.now());
        mongoTemplate.save(shed);
        return shed;
    }

    // Method will return the shed for the given ID
    public Shed getShedById(String regNo) {
        return repository.findById(regNo).get();
    }

    // Method will perform queue length update operations according to the regNo, Fuel type and Operation
    public Shed queueOperation(String regNo, String fuelType, String operation) {
        Shed shed = mongoTemplate.findOne(Query.query(Criteria.where("_id").is(regNo)), Shed.class);
        if (fuelType.equalsIgnoreCase("petrol")) {
            int tempLen;
            if (operation.equalsIgnoreCase("increment")) {
                assert shed != null;
                tempLen = shed.getPetrolQueueLength() + 1;
            } else {
                assert shed != null;
                tempLen = shed.getPetrolQueueLength() - 1;
            }
            shed.setPetrolQueueLength(tempLen);
            mongoTemplate.save(shed);
        } else {
            int tempLen;
            if (operation.equalsIgnoreCase("increment")) {
                assert shed != null;
                tempLen = shed.getDieselQueueLength() + 1;
            } else {
                assert shed != null;
                tempLen = shed.getDieselQueueLength() - 1;
            }
            shed.setPetrolQueueLength(tempLen);
            mongoTemplate.save(shed);
        }
        return shed;
    }

}
