package com.andmore.restapitest.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.andmore.restapitest.model.User;

/**
 * Imp for UserRepository.
 * 
 * @author Andres.
 */
@Repository
public class UserRepositoryImpl extends GenericRepositoryImpl<User>
		implements UserRepository {

	private static final long serialVersionUID = -1497180899718451209L;
	private final Logger logger = LoggerFactory
			.getLogger(UserRepositoryImpl.class);

	 /**
	 * Consult the existing user, filtering by name and password.  
	 * @param name.
	 * @param password.
	 * @return User List.
	 */
	@Override
	public List<User> getRegisterUser(String name, String password) {
		logger.debug("getting register user: {}, {}", name,password );

		Map<String, Object> paramsNameAndValues = new HashMap<String, Object>();
		paramsNameAndValues.put("name", name);
		paramsNameAndValues.put("password", password);
		StringBuilder query = new StringBuilder(128);
		query.append("select u from user u where u.name = :name AND u.password = :password ");

		List<User> user = this.getAllByQuery(query.toString(),paramsNameAndValues);
	
		if (logger.isDebugEnabled()) {
			logger.debug("User registrated: {}", user.get(0).getName());
		}
		return user;
	}

}
