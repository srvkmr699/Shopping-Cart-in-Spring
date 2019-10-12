package com.shopping.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.entities.User;
import com.shopping.entities.VerificationToken;
import com.shopping.exception.EmailExitsException;
import com.shopping.models.LoginDto;
import com.shopping.repositories.UserRepository;
import com.shopping.repositories.VerificationTokenRepository;
import com.shopping.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	VerificationTokenRepository verificationTokenRepository;

	@Override
	public User findByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}

	@Override
	public void createVerificationToken(User user, String token, Date expiryDate) {
		VerificationToken myToken = new VerificationToken(token, user, expiryDate);
		verificationTokenRepository.save(myToken);
	}

	@Override
	public User createUserAccount(User user) throws EmailExitsException {
		if (userRepository.existsByEmail(user.getEmail())) {
			throw new EmailExitsException("This email is already exists");
		}
		return userRepository.save(user);
	}

	@Override
	public VerificationToken getVerificatonToken(String token) {
		return verificationTokenRepository.FindByToken(token);
	}

	@Override
	public void saveRegisteredUser(User user) {
		userRepository.save(user);
	}

	@Override
	public boolean isUserValid(LoginDto loginDto) {
		User user = userRepository.findByEmail(loginDto.getEmail());
		if(user.getPassword().equals(loginDto.getPassword()) && user.isEnabled()) {
			return true;
		}
		return false;
	}

}
