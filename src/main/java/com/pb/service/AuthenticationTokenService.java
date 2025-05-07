package com.pb.service;

import java.util.Objects;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pb.entity.AuthenticationToken;
import com.pb.entity.User;
import com.pb.exception.AuthenticationFailException;
import com.pb.repo.TokenRepo;

@Service
public class AuthenticationTokenService {
	@Autowired
	TokenRepo tokenRepo;

	public void saveConfirmationToken(AuthenticationToken authenticatonToken) {
		tokenRepo.save(authenticatonToken);
	}

	public AuthenticationToken getToken(User user) {

		return tokenRepo.findByUser(user);
	}

	public User getUser(String token) {

		final AuthenticationToken autenticatinToken = tokenRepo.findByToken(token);
		if (Objects.isNull(autenticatinToken)) {
			return null;
		}

		return autenticatinToken.getUser();
	}

	public void autenticate(String token)  throws AuthenticationFailException {
		if (Objects.isNull(token)) {
			throw new AuthenticationFailException("Token is not present");
		}
		if (Objects.isNull(getUser(token)))
		{
			throw new AuthenticationFailException("token is not valid");
		}
		
	}
}
