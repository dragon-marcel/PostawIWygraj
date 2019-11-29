package component;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.stereotype.Component;

import com.example.PostawIWygraj.model.UserPrincipal;


@Component
public class LogoutListener implements ApplicationListener<SessionDestroyedEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(LogoutListener.class);

    @Override
    public void onApplicationEvent(SessionDestroyedEvent event) {
    	List<SecurityContext> lstSecurityContext = event.getSecurityContexts();
    	for (SecurityContext securityContext : lstSecurityContext)
    	{
    		UserPrincipal user = (UserPrincipal) securityContext.getAuthentication().getPrincipal();
	        System.out.print("Wylogowal"+user.getUser().getName() + user.getUser().getId());

    	}
    }
}