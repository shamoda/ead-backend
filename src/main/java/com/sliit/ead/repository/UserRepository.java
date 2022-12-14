package com.sliit.ead.repository;

import com.sliit.ead.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author S.M. Jayasekara
 * @IT_number IT19161648
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
