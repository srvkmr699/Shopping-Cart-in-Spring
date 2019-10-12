package com.shopping.service;

import com.shopping.event.OnRegistrationCompleteEvent;

public interface UserRegistrationEventService {
	void confirmUserRegistrationEvent(OnRegistrationCompleteEvent event);
}
