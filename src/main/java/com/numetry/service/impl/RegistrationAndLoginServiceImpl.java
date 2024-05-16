package com.numetry.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.numetry.entity.User;
import com.numetry.exceptions.UserNotFoundException;
import com.numetry.repository.SchoolWebsiteRepo;
import com.numetry.request.LoginRequest;
import com.numetry.service.RegistrationAndLoginService;

@Service
public class RegistrationAndLoginServiceImpl implements RegistrationAndLoginService {

	@Autowired
	private SchoolWebsiteRepo repo; 
	
	private Logger logger = LoggerFactory.getLogger(RegistrationAndLoginServiceImpl.class);
	
	@Override
	public User register(User user) {
		logger.info("Service Login Request |"+user);
		return repo.save(user);
	}

	@Override
	public User login(LoginRequest req) {
		logger.info("service Login Request |"+req);
		return repo.findByEmailAndPassword(req.getEmail(), req.getPassword()).orElseThrow(() -> new UserNotFoundException("User name and pssword is not matching -->email: " + req.getEmail()));
	}
	
	
}


