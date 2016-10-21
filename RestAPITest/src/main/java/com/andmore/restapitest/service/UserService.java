/**
 * 
 */
package com.andmore.restapitest.service;

import com.andmore.restapitest.model.User;

/**
 * User Service.
 * @author Andres
 *
 */
public interface UserService {
	
	 
	public User getUser(String name, String password);
	
	 

}
