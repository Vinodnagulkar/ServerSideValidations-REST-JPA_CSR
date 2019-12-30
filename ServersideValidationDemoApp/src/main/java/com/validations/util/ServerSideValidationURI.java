package com.validations.util;

/**
 * This is utility class for Rest API end points
 * @author vinod.nagulkar
 */
public class ServerSideValidationURI 
{
	/**
	 * UserController API End points
	 */
	public static final  String USER_REGISTER="api/validations/userRegister";
	public static final String USER_LOGIN="api/validations/userLogin";
	public static final String GET_USER="api/validations/getUser/{id}";
	public static final String GET_ALL_USER="api/validations/getAllUsers";
	public static final String DELETE_USER="api/validations/deleteUser/{id}";
}
