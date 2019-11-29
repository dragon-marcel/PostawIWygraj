package component;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import com.example.PostawIWygraj.model.UserPrincipal;


@Component
	public class LoginListener implements ApplicationListener<AuthenticationSuccessEvent> {

	    private static final Logger LOG = LoggerFactory.getLogger(LoginListener.class);

	    @Override
	    public void onApplicationEvent(AuthenticationSuccessEvent event) {
	    	UserPrincipal user = (UserPrincipal) event.getAuthentication().getPrincipal();
	    	
	       System.out.print("zalogowal sie"+ user.getUser().getId() +user.getUser().getSurname());
	    }
	
}
