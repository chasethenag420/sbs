package com.asu.cse545.group12.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.asu.cse545.group12.services.AuthorizationService;


@Controller
public class NotificationController {
	
	private static final Logger logger = Logger.getLogger(HomePageController.class);
	
	@Autowired
	AuthorizationService  authorizationService;

	
	@RequestMapping(value = "/notifications", method = RequestMethod.GET)
	public ModelAndView getNotificationPage() {
	    //logs debug message
	    if(logger.isDebugEnabled()){
	      logger.debug("Notification page is requested");
	    }
	    
	    ModelAndView notificationView = new ModelAndView();
	    
	    //********************************************************************************
	    //    GET THE VALUES FROM THE getNotifications() method from the AuthorizationDao
	    //********************************************************************************
	    notificationView.addObject("notificationRows", authorizationService.getNotifications());
	    notificationView.addObject("authorizationId", new Integer(0));
	    notificationView.setViewName("notifications");
		return notificationView;
	}
	
	
	@RequestMapping(value = "/approvenotification", method = RequestMethod.GET)
	public ModelAndView approveRequest(Model model) {
		
		ModelAndView notificationView = new ModelAndView("notificationapprove");
		String approver,authorizationid;
		
		//logs debug message
	    if(logger.isDebugEnabled()){
	      logger.debug("Request Approved");
	    }
	    //********************************************************************************
	    //    Have to get the Internal User Who clicked on the APPROVE button along with authorization Object()
	    //********************************************************************************
	    //approver = 
	    //authorizationid = 
	    //authorizationService.approve(authorizationid,approver);
	    //return notificationView;
		return null;
	}
	
	
	@RequestMapping(value = "/rejectnotification", method = RequestMethod.GET)
	public ModelAndView rejectRequest(Model model) {
	    //logs debug message
	    if(logger.isDebugEnabled()){
	      logger.debug("Notification page is requested");
	    }
	    
	    ModelAndView notificationView = new ModelAndView();
	    //********************************************************************************
	    //    GET THE VALUES FROM THE getNotifications() method from the AuthorizationDao
	    //********************************************************************************
	    notificationView.addObject("notificationRows", authorizationService.getNotifications());
	    notificationView.setViewName("notifications");
		return notificationView;
	}
	
	@RequestMapping(value = "/forwardnotification", method = RequestMethod.GET)
	public ModelAndView forwardRequest(Model model) {
	    //logs debug message
	    if(logger.isDebugEnabled()){
	      logger.debug("Notification page is requested");
	    }
	    
	    ModelAndView notificationView = new ModelAndView();
	    //********************************************************************************
	    //    GET THE VALUES FROM THE getNotifications() method from the AuthorizationDao
	    //********************************************************************************
	    notificationView.addObject("notificationRows", authorizationService.getNotifications());
	    notificationView.setViewName("notifications");
		return notificationView;
	}
}
