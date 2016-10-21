package com.andmore.restapitest.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andmore.restapitest.model.User;
import com.andmore.restapitest.repository.UserRepository;

/**
 * Budget Service implementation.
 * 
 * @author Andres
 *
 */
@Service
@Transactional(readOnly = false)
public class UserServiceImpl implements UserService {

	private final Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);

	private UserRepository userRepository;

	public UserServiceImpl() {
		super();
	}

	@Inject
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;

	}

 

	@Override
	@Transactional(readOnly = true)
	public User getUser(String name, String password) {
		logger.debug("getting registered user by name {} and password {}", name, password);
		User user = new User();	
		List<User> userList = userRepository.getRegisterUser(name, password);
		if(userList.size() >0){
			user = userList.get(0);
		}
		logger.debug("user registered {}", CollectionUtils.size(user));
		return user;
	}

	 
	 

}
