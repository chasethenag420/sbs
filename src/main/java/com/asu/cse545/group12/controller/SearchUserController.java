package com.asu.cse545.group12.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.asu.cse545.group12.dao.UserDao;
import com.asu.cse545.group12.domain.Form;
import com.asu.cse545.group12.domain.Users;
import com.asu.cse545.group12.services.AuthorizationService;
import com.asu.cse545.group12.services.UserService;
import com.asu.cse545.group12.validator.CreateExternalUserValidator;
import com.asu.cse545.group12.validator.Utils;


@Controller
public class SearchUserController {

	private static final Logger logger = Logger.getLogger(SearchUserController.class);

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
		List<Users> userList = null;
		if(!Utils.isEmpty(formMap.get("accountNumber"))){
			Users user = userservice.getUsersByAccountNumber(Integer.parseInt(formMap.get("accountNumber")));
			if(user!=null){
				userList=new ArrayList<Users>();
				userList.add(user);
			}
		} else if(!Utils.isEmpty(formMap.get("userName"))){
			Users user = userservice.getUserByUserName(formMap.get("userName"));
			if(user!=null){
				userList=new ArrayList<Users>();
				userList.add(user);
			}
		}else if(!Utils.isEmpty(formMap.get("firstName"))){
			List<Users> tempUserList = userservice.getUsersByFirstName(formMap.get("firstName"));
			if(tempUserList!=null){
				userList=new ArrayList<Users>();
				userList.addAll(tempUserList);
			}
		}else if(!Utils.isEmpty(formMap.get("lastName"))){
			List<Users> tempUserList = userservice.getUsersByLastName(formMap.get("lastName"));
			if(tempUserList!=null){
				userList=new ArrayList<Users>();
				userList.addAll(tempUserList);
			}
		}else if(!Utils.isEmpty(formMap.get("emailId"))){
			List<Users> tempUserList = userservice.getUsersByEmailId(formMap.get("emailId"));
			if(tempUserList!=null){
				userList=new ArrayList<Users>();
				userList.addAll(tempUserList);
			}
		}else if(!Utils.isEmpty(formMap.get("phoneNumber"))){
			List<Users> tempUserList = userservice.getUsersByPhoneNumber(formMap.get("phoneNumber"));
			if(tempUserList!=null){
				userList=new ArrayList<Users>();
				userList.addAll(tempUserList);
			}
		}
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("displayuserlist");
		modelView.addObject("userList",userList);
		if(userList==null || userList.size()==0){
			modelView.addObject("errorMessage", "Not data found. Please try by other input");
		}
		return modelView;
	}

	@RequestMapping(value = "/modifyUserForm",method=RequestMethod.POST)
	public ModelAndView modifyUser(@ModelAttribute("form") Form form,HttpServletRequest request) {
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("Modify User data requested");
		}
		Map<String, String> formMap=form.getMap();
		Users user= userDao.getUserByUserId(Integer.parseInt(formMap.get("userId")));
		request.getSession(false).setAttribute("modifyuserId",formMap.get("userId") );
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("user", user);
		modelView.addObject("message", "");
		modelView.setViewName("modifyUser");
		return modelView;
	}

	@RequestMapping(value = "/modifyUserFormDetails",method=RequestMethod.POST)
	public ModelAndView modifyUser(@Valid @ModelAttribute("user") Users user, BindingResult result, HttpServletRequest request) {
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("Modify User data update "+user.toString());
		}
		Integer userId=Integer.parseInt((String)request.getSession(false).getAttribute("modifyuserId"));
		Users userDB = userDao.getUserByUserId(userId);

		userDB.setFirstName(user.getFirstName());
		userDB.setLastName(user.getLastName());
		userDB.setMiddleName(user.getMiddleName());
		userDB.setState(user.getState());
		userDB.setAddress(user.getAddress());
		userDB.setCity(user.getCity());
		userDB.setCountry(user.getCountry());
		userDB.setZipcode(user.getZipcode());
		userDB.setPhoneNumber(user.getPhoneNumber());
		userDB.setEmailId(user.getEmailId());
		//TO Do validations
		/*		if (result.hasErrors()) {
			ModelAndView modelView = new ModelAndView();
			modelView.addObject("user", user);
			modelView.addObject("errorMessage", "There is something wrong with input");
			modelView.setViewName("modifyUser");
			return modelView;
		} else {*/
		int userID = userDao.updateRow(userDB);
		if(logger.isDebugEnabled()){
			logger.debug("Modify User ID "+userID);
		}
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("user", user);
		modelView.addObject("message", "Updated SuccessFully");
		modelView.setViewName("modifyUser");
		return modelView;
		//		}

	}

	@RequestMapping(value = "/deleteUser",method=RequestMethod.POST)
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
		modelView.addObject("message", "Deleted SuccessFully");
		modelView.setViewName("searchuser");
		return modelView;
	}

}
