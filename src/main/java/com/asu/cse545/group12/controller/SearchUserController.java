package com.asu.cse545.group12.controller;

import java.util.ArrayList;
import java.util.List;
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
import com.asu.cse545.group12.validator.Utils;


@Controller
public class SearchUserController {

	private static final Logger logger = Logger.getLogger(ManagerUserController.class);

	@Autowired
	AuthorizationService  authorizationService;

	@Autowired
	UserService userservice;

	@Autowired
	UserDao userDao;
	@RequestMapping(value = "/searchuser",method=RequestMethod.GET)
	public ModelAndView getSearchUserview() {
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("Seach User form requested");
		}
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("form", new Form());
		modelView.setViewName("searchuser");
		return modelView;
	}

	@RequestMapping(value = "/getuserlist",method=RequestMethod.POST)
	public ModelAndView searchUser(@ModelAttribute("form") Form form) {
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("Modify User data requested");
		}
		Map<String, String> formMap=form.getMap();
		List<Users> userList = new ArrayList<Users>();
		if(!Utils.isEmpty(formMap.get("accountNumber"))){
			userList.add(userservice.getUsersByAccountNumber(Integer.parseInt(formMap.get("accountNumber"))));
		} else if(!Utils.isEmpty(formMap.get("userName"))){
			userList.add(userservice.getUserByUserName(formMap.get("userName")));
		}else if(!Utils.isEmpty(formMap.get("firstName"))){
			userList.addAll(userservice.getUsersByFirstName(formMap.get("firstName")));
		}else if(!Utils.isEmpty(formMap.get("lastName"))){
			userList.addAll(userservice.getUsersByLastName(formMap.get("lastName")));
		}else if(!Utils.isEmpty(formMap.get("emailId"))){
			userList.addAll(userservice.getUsersByEmailId(formMap.get("emailId")));
		}else if(!Utils.isEmpty(formMap.get("phoneNumber"))){
			userList.addAll(userservice.getUsersByPhoneNumber(formMap.get("phoneNumber")));
		}
		return new ModelAndView("displayuserlist","userList",userList);
	}

}
