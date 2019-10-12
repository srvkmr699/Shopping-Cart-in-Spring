package com.shopping.service;

import com.shopping.models.EmailPayload;

public interface EmailService {
	void sendEmail(EmailPayload emailPayload);
}
