package com.asu.cse545.group12.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class NotificationController {
	
	private static final Logger logger = Logger.getLogger(HomePageController.class);
	
	@RequestMapping(value = "/notifications", method = RequestMethod.GET)
	
	public ModelAndView getNotificationPage(Model model) {
	    //logs debug message
	    if(logger.isDebugEnabled()){
	      logger.debug("Notification page is requested");
	    }
	    
	    ModelAndView notificationView = new ModelAndView();
	    //********************************************************************************
	    //    GET THE VALUES FROM THE getNotifications() method from the AuthorizationDao
	    //********************************************************************************
	    notificationView.addObject("notificationRows", " ");
	    notificationView.setViewName("notifications");
		return notificationView;
	}
}
