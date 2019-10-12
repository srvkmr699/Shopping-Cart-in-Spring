package com.shopping.service;


import java.util.Date;

import com.shopping.entities.User;
import com.shopping.entities.VerificationToken;
import com.shopping.exception.EmailExitsException;
import com.shopping.models.LoginDto;

public interface IUserService {
	
	User createUserAccount(User user)throws EmailExitsException;
	User findByEmail(String email);
	void createVerificationToken(User user, String token,Date expiryDate);
	VerificationToken getVerificatonToken(String token);
	void saveRegisteredUser(User user);
	boolean isUserValid(LoginDto loginDto);
}
