package com.spring.security.test.SpringSecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.security.test.SpringSecurity.entity.UserInfo;
import com.spring.security.test.SpringSecurity.repository.UserInfoRepository;

@Service
public class UserService {
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserInfoRepository userInfoRepository;
	
	public UserInfo addUser(UserInfo u) {
		
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		UserInfo newUser = userInfoRepository.save(u);
		return newUser;
	}
}
