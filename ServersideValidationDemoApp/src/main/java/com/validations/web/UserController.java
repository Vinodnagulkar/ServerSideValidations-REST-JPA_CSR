package com.validations.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.validations.util.ServerSideValidationURI;
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
	@PostMapping(ServerSideValidationURI.USER_REGISTER)
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		LOGGER.info("\n\n****************Creating user {}",user+"****************\n\n");
		userServiceApi.createUser(user);
		return new ResponseEntity<User>(HttpStatus.CREATED);
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
	@PostMapping(ServerSideValidationURI.USER_LOGIN)
	public ResponseEntity<User> authenticateUser(@Valid @RequestBody Login login) throws InvalidCredentialsException {
		
		LOGGER.info("\n\n*******************Inside the login action****************************\n\n");
		User loginUserObj = userServiceApi.findByLoginNameAndPassword(login.getLoginName(), login.getPassword());
		LOGGER.info("\n\n*************Login user:", loginUserObj+"********************");
		if(loginUserObj!=null)
		return new ResponseEntity<User>(loginUserObj,HttpStatus.ACCEPTED);
		else
			return new ResponseEntity<User>(HttpStatus.FORBIDDEN);
	}
	
	/**
	 * This method fetches the user from DB based on user id.
	 * @GetMapping is used to handle GET type of request method
	 * @param User id
	 * @return User object
	 */
	@GetMapping(ServerSideValidationURI.GET_USER)
	public ResponseEntity<Optional<User>> getUserById(@Min(value = 1) @PathVariable Integer id) {
		LOGGER.info("\n\n**************************Inside getUserById*********************\n\n");
		Optional<User> user=userServiceApi.getUserById(id);
		LOGGER.info("*********************User:"+user+"****************");
		if(user!=null)
		return new ResponseEntity<Optional<User>>(user,HttpStatus.FOUND);
		else
			return new ResponseEntity<Optional<User>>(HttpStatus.NOT_FOUND);	
	}
	
	/**
	 * This method fetches all users from DB.
	 * @GetMapping is used to handle GET type of request method
	 * @return List of User object
	 */
	@GetMapping(ServerSideValidationURI.GET_ALL_USER)
	public ResponseEntity<?> getAllUsers() {
		LOGGER.info("\n\n**************************Inside getAllUser*********************\n\n");
		List<User>  userList=userServiceApi.getAllUser();
		LOGGER.info("*********************Users:"+userList+"****************");
		if(!userList.isEmpty())
		return new ResponseEntity<>(userList,HttpStatus.OK);
		else
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
	
	
	/**
	 * This method delete the user from DB based on user id.
	 * @DeleteMapping annotation for mapping HTTP DELETE requests onto specific handler methods
	 * @param User id
	 * @return void
	 */
	@DeleteMapping(ServerSideValidationURI.DELETE_USER)
	public ResponseEntity<?> deleteUser(@Min(value = 1) @PathVariable Integer id) 
	{
		LOGGER.info("\n\n**************************Inside deleteUser*********************\n\n");
		userServiceApi.deleteUser(id);
		return new ResponseEntity<User>(HttpStatus.OK);
	}

}
