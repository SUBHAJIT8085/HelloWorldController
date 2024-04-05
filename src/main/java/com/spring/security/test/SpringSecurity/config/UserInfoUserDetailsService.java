package com.spring.security.test.SpringSecurity.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.spring.security.test.SpringSecurity.entity.UserInfo;
import com.spring.security.test.SpringSecurity.repository.UserInfoRepository;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<UserInfo> user  =userInfoRepository.findByName(username);
		
		return user.map(UserInfoUserDetails:: new).orElseThrow(()-> new UsernameNotFoundException("user not found" + username));
	}

}
