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

import com.asu.cse545.group12.dao.UserDao;
import com.asu.cse545.group12.domain.Form;
import com.asu.cse545.group12.domain.Users;
import com.asu.cse545.group12.services.AuthorizationService;
import com.asu.cse545.group12.services.UserService;


@Controller
public class ManagerUserController {

	private static final Logger logger = Logger.getLogger(ManagerUserController.class);

	@Autowired
	AuthorizationService  authorizationService;

	@Autowired
	UserService userservice;

	@Autowired
	UserDao userDao;

	@RequestMapping(value = "/getmodifyuserview",method=RequestMethod.POST)
	public ModelAndView getModifyUserview(@ModelAttribute("form") Form form,HttpServletRequest request) {
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("Modify User data requested");
		}
		ModelAndView modelView = new ModelAndView();

		Map<String, String> formMap=form.getMap();
		Integer userId= Integer.parseInt(formMap.get("userId"));	
		Users user = userDao.getUserByUserId(userId);
		modelView.addObject("user", user);
		modelView.setViewName("manager");
		return modelView;
	}

	@RequestMapping(value = "/modifyuser",method=RequestMethod.POST)
	public ModelAndView modifyUser(@ModelAttribute("user") Users user,HttpServletRequest request) {
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("Modify User data requested");
		}
		ModelAndView modelView = new ModelAndView();

		HttpSession session = request.getSession(false);
		String username=(String)session.getAttribute("username");
		userDao.updateRow(user);
		modelView.setViewName("manager");
		return modelView;
	}


	@RequestMapping(value = "/deleteuser",method=RequestMethod.POST)
	public ModelAndView deleteUser(@ModelAttribute("form") Form form,HttpServletRequest request) {
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("Delete User data requested");
		}
		ModelAndView modelView = new ModelAndView();

		HttpSession session = request.getSession(false);
		String username=(String)session.getAttribute("username");
		Map<String, String> formMap=form.getMap();
		Integer userId= Integer.parseInt(formMap.get("userId"));	
		userservice.deActivateUserByUserId(userId);
		modelView.setViewName("manager");
		return modelView;
	}
}
