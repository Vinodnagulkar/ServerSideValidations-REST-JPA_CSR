package com.validations.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.validations.exceptions.InvalidCredentialsException;
import com.validations.model.User;
import com.validations.repository.UserRepository;
import com.validations.serviceApi.UserServiceApi;
/**
 * @author vinod.nagulkar
 */


/**
 * This is service implementation class for UserServiceApi interface methods implementation
 * @Service annotation is used in your service layer and annotates classes that perform service tasks
 * @Transactional annotation itself defines the scope of a single database transaction
 */
@Service
@Transactional
public class UserServiceImpl implements UserServiceApi{
	
	/**
	 * User repository instance
	 */
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * This method registers the user.
	 * It takes User object as a input and pass it to DAO layer
	 * @param User Object
	 * @return User object
	 */
	@Override
	public User createUser(User user) {
		userRepository.save(user);
		return user;
	}
	
	
	/**
	 * This method authenticate the user 
	 * @param It takes two parameter as input User name and password
	 * @return User object if user is valid.
	 * @throws if user is not valid it throws InvalidCredentialsException
	 */
	@Override
	public User findByLoginNameAndPassword(String username, String password) throws InvalidCredentialsException
	{
			User loginUserObj = userRepository.findByLoginNameAndPassword(username,password);
			if(loginUserObj==null) 
			throw new InvalidCredentialsException("User not found!");
			return loginUserObj;
	}

	/**
	 * This method fetches the user object from DB based on user id 
	 * @param user id
	 * @return User object
	 * Optional annotation avoids NullPonterException if user is not exist in DB, it returns null.
	 */
	@Override
	public Optional<User> getUserById(Integer id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id);
	}

	/**
	 * This method delete the user 
	 * @param user id
	 * @return void
	 */
	@Override
	public void deleteUser(Integer id) {
		// TODO Auto-generated method stub
		userRepository.deleteById(id);
	}
}
