package com.pb.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pb.dto.ResponceDto;
import com.pb.dto.SigninResponceDto;
import com.pb.dto.user.SigninDto;
import com.pb.dto.user.SignupDto;
import com.pb.entity.AuthenticationToken;
import com.pb.entity.User;
import com.pb.exception.AuthenticationFailException;
import com.pb.exception.CustomException;
import com.pb.repo.UserRepo;

@Service
public class UserService {

	@Autowired
	UserRepo usereRepo;
	@Autowired
	AuthenticationTokenService authenticationTokenService;
    @Transactional
	public ResponceDto signUp(SignupDto signupdto) {
		
		// check user is already present
		
		if (Objects.nonNull(usereRepo.findByEmail(signupdto.getEmail()))){
			throw new CustomException("user alredy exit");
			
		}
		
		
		
		//hash the password
		String encypassword= signupdto.getPassword();
      try {
    	  encypassword=hashPassword(signupdto.getPassword());
      }catch(NoSuchAlgorithmException e){
    	  e.printStackTrace();
      }
      
      User user=new User(signupdto.getFirstName(),signupdto.getLastName(),signupdto.getEmail(),
    		  encypassword);
      
        usereRepo.save(user);
		//create the token
        final AuthenticationToken authenticatonToken=new AuthenticationToken(user);
        
      //save the user
        authenticationTokenService.saveConfirmationToken(authenticatonToken);
        ResponceDto responceDto=new ResponceDto("success", "User created successfully");
		return responceDto;
	}

	private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md= MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest=md.digest();
       String hash=DatatypeConverter.printHexBinary(digest).toUpperCase();
       return hash;
	}

	public SigninResponceDto signin(SigninDto signindto) {
		//find user by email
		User user=usereRepo.findByEmail(signindto.getEmail());
		
		if (Objects.isNull(user)) {
			throw new AuthenticationFailException("user is not vaid");
		}
		//hash the password
		try {
	if(!user.getPassword().equals(hashPassword(signindto.getPassword()))){
		throw new AuthenticationFailException("password is not correct");
	}
		}catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		//Compare to password in db
		
		//if password match return token
		AuthenticationToken token=authenticationTokenService.getToken(user);
		if(Objects.isNull(token)) {
			throw new CustomException("token is  not present");
		}
		
		//return response
		return new SigninResponceDto("success", token.getToken());
	}

}
