package com.pb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pb.dto.ResponceDto;
import com.pb.dto.SigninResponceDto;
import com.pb.dto.user.SigninDto;
import com.pb.dto.user.SignupDto;
import com.pb.service.UserService;


@RestController
@RequestMapping("user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	@Autowired
	UserService userService;
	// signup

	@PostMapping("/signup")
	public ResponceDto signup(@RequestBody SignupDto signupdto) {
		return userService.signUp(signupdto);
	}

	// signin
	@PostMapping("/signin")
	public SigninResponceDto signin(@RequestBody SigninDto signindto) {
		return userService.signin(signindto);
	}
	
}
