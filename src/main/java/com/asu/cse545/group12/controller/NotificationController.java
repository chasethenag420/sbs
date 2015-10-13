package com.asu.cse545.group12.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.asu.cse545.group12.domain.Form;
import com.asu.cse545.group12.domain.Users;
import com.asu.cse545.group12.services.AuthorizationService;


@Controller
public class NotificationController {

	private static final Logger logger = Logger.getLogger(NotificationController.class);

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
		//notificationView.addObject("notificationRows", authorizationService.getNotifications());
		notificationView.addObject("form", new Form());
		notificationView.addObject("notificationRows", authorizationService.getNotifications());
		notificationView.setViewName("notifications");
		return notificationView;
	}


	@RequestMapping("approvenotification")
	public ModelAndView approveRequest(@ModelAttribute("form") Form form,HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		String username=(String)session.getAttribute("username");
		Map<String, String> formMap=form.getMap();
		Integer authorizationId= Integer.parseInt(formMap.get("authorizationId"));
		if(logger.isDebugEnabled()){
			logger.debug("***************************************************username in notifications: "+authorizationId);
		}
		ModelAndView notificationView = new ModelAndView();
		
		//Integer authorizationId= Integer.parseInt((String)modelMap.get("authorizationId"));
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("Request Approved by "+authorizationId);
		}
		//********************************************************************************
		//    Have to get the Internal User Who clicked on the APPROVE button along with authorization Object()
		//********************************************************************************
		authorizationService.approve(authorizationId,username);
		notificationView.addObject("notificationRows", authorizationService.getNotifications());
		notificationView.addObject("authorizationId", new Integer(0));
		notificationView.setViewName("notifications");
		return notificationView;
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
