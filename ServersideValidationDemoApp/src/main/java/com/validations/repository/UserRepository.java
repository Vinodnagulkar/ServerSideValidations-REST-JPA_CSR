package com.validations.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.validations.model.User;
/**
 * @author vinod.nagulkar
 */

/**
 * CRUD repository for User entity
 * Which performs all operations related to user.
 */
@Repository 	
public interface UserRepository extends CrudRepository<User, Integer> {
	User findByLoginNameAndPassword(String username, String password);
}
