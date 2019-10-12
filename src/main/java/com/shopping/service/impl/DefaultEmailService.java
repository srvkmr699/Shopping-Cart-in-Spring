package com.shopping.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.shopping.models.EmailPayload;
import com.shopping.service.EmailService;

@Service
public class DefaultEmailService implements EmailService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public void sendEmail(EmailPayload emailPayload) {
		SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(emailPayload.getRecipients());
        email.setSubject(emailPayload.getSubject());
        email.setText(emailPayload.getContent());
        mailSender.send(email);
		
	}

}
