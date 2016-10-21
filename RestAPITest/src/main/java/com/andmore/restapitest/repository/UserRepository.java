package com.andmore.restapitest.repository;

import java.util.List;

import com.andmore.restapitest.model.User;


/**
 * Repository for User
 * 
 * @author Andres.
 *
 */
public interface UserRepository extends GenericRepository<User>, java.io.Serializable {
	List<User> getRegisterUser(String name, String password);
}
