package com.validations.web;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.validations.exceptions.InvalidCredentialsException;
import com.validations.model.Login;
import com.validations.model.User;
import com.validations.serviceApi.UserServiceApi;
/**
 * @author vinod.nagulkar
 */

/**
 * This is Rest controller which handles all HTTP requests related to User Entity.
 * @RestController marks this class as controller which handles all HTTP requests.
 * @CrossOrigin annotation to handle Cross-Origin-Resource-Sharing (CORS). 
 */
@RestController
@CrossOrigin
public class UserController {

	@Autowired
	private UserServiceApi userServiceApi;
	
	/**
	 * This is logger instance.
	 */
	private final Logger LOGGER = LoggerFactory.getLogger(UserController.class); 
	
	/**
	 * This method registers the user.
	 * It takes User object as a input and pass it to service layer
	 * @RequestBody annotation binds the HTTPRequest body to the domain object.
	 * @PostMapping is used to handle POST type of request method
	 * @param User Object
	 * @return void
	 */
	@PostMapping("api/validations/userRegister")
	public void registerUser(@RequestBody User user) {
		LOGGER.info("Creating user {}",user);
		userServiceApi.createUser(user);
	}

	/**
	 * This method validate the if user is registered or not
	 * It takes Login object as input and pass it to service layer for authentication.
	 * @PostMapping is used to handle POST type of request method
	 * @Valid: This annotation validate the user name and password.
	 * @param Login Object
	 * @return User object
	 * @throws InvalidCredentialsException if invalid credentials entered.
	 */
	@PostMapping("api/validations/userLogin")
	public User authenticateUser(@Valid @RequestBody Login login) throws InvalidCredentialsException {
		
		LOGGER.info("****************Inside the login action****************************");
		User loginUserObj = userServiceApi.findByLoginNameAndPassword(login.getLoginName(), login.getPassword());
		LOGGER.info("Login user:", loginUserObj);
		return loginUserObj;
	}
	
	/**
	 * This method fetches the user from DB based on user id.
	 * @GetMapping is used to handle GET type of request method
	 * @param User id
	 * @return User object
	 */
	@GetMapping("api/validations/getUser/{id}")
	public Optional<User> getUserById(@Min(value = 1) @PathVariable Integer id) {
		LOGGER.info("**************************Inside getUserById*********************");
		Optional<User> user=userServiceApi.getUserById(id);
		LOGGER.info("*********************User:"+user+"****************");
		return user;
		
	}
	
	/**
	 * This method delete the user from DB based on user id.
	 * @DeleteMapping annotation for mapping HTTP DELETE requests onto specific handler methods
	 * @param User id
	 * @return void
	 */
	@DeleteMapping("api/validations/deleteUser/{id}")
	public void deleteUser(@Min(value = 1) @PathVariable Integer id) 
	{
		LOGGER.info("**************************Inside deleteUser*********************");
		userServiceApi.deleteUser(id);
	}

}
