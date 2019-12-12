package com.validations.serviceApi;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.validations.exceptions.InvalidCredentialsException;
import com.validations.model.User;
/**
 * @author vinod.nagulkar
 */

/**
 * This is service interface which contains User entity related methods.
 *  @Service annotation is used in your service layer and annotates classes that perform service tasks
 */
@Service
@Component
public interface UserServiceApi 
{
	/**
	 * This method Creates User.
	 */
	User createUser(User user);
	
	/**
	 * Authenticate user credential if user is valid or not.
	 * @throws InvalidCredentialsException if incorrect details entered.
	 */
	User findByLoginNameAndPassword(String username, String password) throws InvalidCredentialsException;
	
	/**
	 * This method fetches User object from DB based on user id
	 * @param User id
	 * @return User object
	 */
	Optional<User> getUserById(Integer id);
	
	/**
	 * This method deletes User object based on user id
	 * @param user id
	 * @return void
	 */
	void deleteUser(Integer id);
	
}
