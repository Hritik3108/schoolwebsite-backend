package com.numetry.service;

import com.numetry.entity.User;
import com.numetry.request.LoginRequest;

public interface RegistrationAndLoginService {

	public User register(User user);
	public User login(LoginRequest req);
		
}
