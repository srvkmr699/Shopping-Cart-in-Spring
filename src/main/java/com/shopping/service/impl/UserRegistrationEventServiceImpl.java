package com.shopping.service.impl;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.shopping.entities.User;
import com.shopping.entities.VerificationToken;
import com.shopping.event.OnRegistrationCompleteEvent;
import com.shopping.models.EmailPayload;
import com.shopping.service.EmailService;
import com.shopping.service.IUserService;
import com.shopping.service.UserRegistrationEventService;

@Service
public class UserRegistrationEventServiceImpl implements UserRegistrationEventService{
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private EmailService emailService;

	@Override
	public void confirmUserRegistrationEvent(OnRegistrationCompleteEvent event) {
		User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.createVerificationToken(user, token,VerificationToken.calculateExpiryDate(60*24));
        
        String recipientAddress = user.getEmail();
        String subject = "Account registration confirmation";
        String confirmationUrl = event.getAppUrl() + "/regitration_confirm?token=" + token;
       
        
        EmailPayload emailPayload = new EmailPayload();
        emailPayload.setRecipients(recipientAddress);
        emailPayload.setSubject(subject);
        emailPayload.setContent("http://localhost:8080" + confirmationUrl);
        emailService.sendEmail(emailPayload);
		
	}
}
