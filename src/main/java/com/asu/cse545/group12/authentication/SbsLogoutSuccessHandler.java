package com.asu.cse545.group12.authentication;

 
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.asu.cse545.group12.controller.LoginController;

public class SbsLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {
	private static final Logger logger = Logger.getLogger(LoginController.class);
    public SbsLogoutSuccessHandler() {
        super();
    }

    // API

    @Override
    public void onLogoutSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException, ServletException {
    	if(logger.isDebugEnabled()){
			logger.debug("Logout page is requested");
		}
    	super.onLogoutSuccess(request, response, authentication);
    }

}