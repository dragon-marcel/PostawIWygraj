package com.example.PostawIWygraj.component;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.stereotype.Component;


@Component
public class Helper {
    
    
    public boolean isValidEmailAddress(String email) {
	boolean result = true;
	try {
	    InternetAddress emailAddr = new InternetAddress(email);
	    emailAddr.validate();
	} catch (AddressException ex) {
	    result = false;
	}
	return result;
    }
}
