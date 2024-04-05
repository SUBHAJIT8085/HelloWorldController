package com.spring.security.test.SpringSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.test.SpringSecurity.entity.UserInfo;
import com.spring.security.test.SpringSecurity.service.UserService;

@RestController
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/normal")
	public ResponseEntity<String> adminUSer(){
		return ResponseEntity.ok("Yes I am OK");
	
	}
	
	@GetMapping("/public")
	public ResponseEntity<String> publicUser(){
		return ResponseEntity.ok("Yes I am OK");
	
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin")
	public ResponseEntity<String> adminUser(){
		String string = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println(string);
		return ResponseEntity.ok("Yes I am OK");
	
	}
	
	@PostMapping("/user")
	public ResponseEntity<UserInfo> addNewUserInfo(@RequestBody UserInfo userInfo) {
		return new ResponseEntity<>(userService.addUser(userInfo), HttpStatus.OK);
		
	}
	
	@GetMapping("/hellobranch")
	public String hello() {
		return "Hello World to the branch";
	}

}
