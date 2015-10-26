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

import com.asu.cse545.group12.constantfile.Const;
import com.asu.cse545.group12.dao.UserDao;
import com.asu.cse545.group12.domain.Authorization;
import com.asu.cse545.group12.domain.Form;
import com.asu.cse545.group12.domain.Users;
import com.asu.cse545.group12.services.AuthorizationService;
import com.asu.cse545.group12.services.UserService;

@Controller
public class NotificationController {

	private static final Logger logger = Logger.getLogger(NotificationController.class);

	@Autowired
	AuthorizationService authorizationService;

	@Autowired
	UserDao userDao;

	@Autowired
	UserService userService;

	@RequestMapping(value = "/notifications", method = RequestMethod.GET)
	public ModelAndView getNotificationPage(HttpServletRequest request) {
		// logs debug message
		if (logger.isDebugEnabled()) {
			logger.debug("Notification page is requested");
		}

		HttpSession session = request.getSession(false);
		String username=(String)session.getAttribute("username");

		Users user = new Users();
		user=userService.getUserByUserName(username);

		ModelAndView notificationView = new ModelAndView();

		notificationView.addObject("form", new Form());
		notificationView.addObject("notificationRows", authorizationService.getNotifications(user));
		
		logger.debug("**********************************Authorizations: "+authorizationService.getNotifications(user));
		notificationView.addObject("approvedNotificationRows", authorizationService.getApprovedPendingNotifications(user));
		notificationView.setViewName("notifications");
		return notificationView;
	}

	@RequestMapping(value = "approvenotification", method = RequestMethod.POST)
	public ModelAndView approveRequest(@ModelAttribute("form") Form form, HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		String username=(String)session.getAttribute("username");

		Users user = new Users();
		user=userService.getUserByUserName(username);

		Map<String, String> formMap=form.getMap();
		if(logger.isDebugEnabled()){
			logger.debug("***************************************************authorizationId in notifications: "+ formMap.get("authorizationId"));
		}
		Integer authorizationId= Integer.parseInt(formMap.get("authorizationId"));
		if(logger.isDebugEnabled()){
			logger.debug("***************************************************username in notifications: "+ username);
		}
		ModelAndView notificationView = new ModelAndView();

		//Integer authorizationId= Integer.parseInt((String)modelMap.get("authorizationId"));
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("Request Approved by:"+ username);
			logger.debug("Authorization Request Number is:"+ username);
		}

		/*
		 * If the approver is Merchant and authorization is for transfer i.e. merchant payment from customer.
		 * Then handle the approving differently than usual.
		 */
		Authorization authorization =  authorizationService.getAuthorizationByAuthorizationId(authorizationId);
		if(authorization != null && user.getRoleId() == 2 && authorization.getRequestType().equals(Const.TRANSFER_REQUEST) )
		{
			authorizationService.approveTransferByMerchant(authorizationId, username);
			
		}
		else
		{
			// ********************************************************************************
			// Have to get the Internal User Who clicked on the APPROVE button along
			// with authorization Object()
			// ********************************************************************************
			authorizationService.approve(authorizationId, username);
		}
		notificationView.addObject("notificationRows", authorizationService.getNotifications(user));
		notificationView.addObject("authorizationId", new Integer(0));
		notificationView.setViewName(getViewName(username));

		return notificationView;
	}

	@RequestMapping(value = "rejectnotification", method = RequestMethod.POST)
	public ModelAndView rejectRequest(@ModelAttribute("form") Form form, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");

		Users user = new Users();
		user=userService.getUserByUserName(username);

		// logs debug message
		if (logger.isDebugEnabled()) {
			logger.debug("Notificatio Reject page is requested");
		}
		Map<String, String> formMap = form.getMap();
		Integer authorizationId = Integer.parseInt(formMap.get("authorizationId"));

		ModelAndView notificationView = new ModelAndView();
		// ********************************************************************************
		// GET THE VALUES FROM THE getNotifications() method from the
		// AuthorizationDao
		// ********************************************************************************
		authorizationService.reject(authorizationId, username);
		notificationView.addObject("notificationRows", authorizationService.getNotifications(user));
		//authorizationService.reject(authorizationId, username);
		notificationView.setViewName(getViewName(username));
		return notificationView;
	}

	@RequestMapping(value = "forwardnotification", method = RequestMethod.POST)
	public ModelAndView forwardRequest(@ModelAttribute("form") Form form, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");

		Users user = new Users();
		user=userService.getUserByUserName(username);

		// logs debug message
		if (logger.isDebugEnabled()) {
			logger.debug("Notification Forward page is requested");
		}

		Map<String, String> formMap = form.getMap();
		Integer authorizationId = Integer.parseInt(formMap.get("authorizationId"));

		ModelAndView notificationView = new ModelAndView();
		// ********************************************************************************
		// GET THE VALUES FROM THE getNotifications() method from the
		// AuthorizationDao
		// ********************************************************************************
		authorizationService.forward(authorizationId, username);
		notificationView.addObject("notificationRows", authorizationService.getNotifications(user));
		//authorizationService.forward(authorizationId, username);
		notificationView.setViewName(getViewName(username));
		return notificationView;
	}

	private String getViewName(String username){
		int roleId = userDao.getUserByUserName(username).getRoleId();
		// for individual user
		if (roleId == 1) {
			return "individual";
		} else if (roleId == 2) {
			return "merchant";
		} else if (roleId == 3) {
			return "regular";
		} else if (roleId == 4) {
			return "manager";
		} else if (roleId == 5) {
			return "admin";
		} else
			return "404";
	}
}
