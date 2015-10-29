package com.asu.cse545.group12.interceptors;

import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.asu.cse545.group12.controller.SearchUserController;
import com.asu.cse545.group12.dao.SystemAccessDao;
import com.asu.cse545.group12.domain.SystemAccess;

public class RequestProcessingInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	SystemAccessDao systemAccessDao;

	private static final Logger logger = Logger.getLogger(RequestProcessingInterceptor.class);


	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String ipAddress = request.getHeader("X-FORWARDED-FOR");  
		if (ipAddress == null) {  
			ipAddress = request.getRemoteAddr();  
		}
		String sessionId=request.getRequestedSessionId();
		String requestUrl= request.getRequestURL().toString();
		Date currentTime = new Date(System.currentTimeMillis());
		String action=request.getServletPath();
		
		SystemAccess systemAccess= new SystemAccess();
		systemAccess.setAction(action);
		systemAccess.setIpAddress(ipAddress);
		systemAccess.setRequestUrl(requestUrl);
		systemAccess.setTime(currentTime);
		systemAccess.setSessionId(sessionId); 
		if(request.getUserPrincipal()!=null){
			systemAccess.setUserName(request.getUserPrincipal().getName());
		}else{
			systemAccess.setUserName(null);
		}
		systemAccessDao.insertRow(systemAccess);
		return true;
	}

}