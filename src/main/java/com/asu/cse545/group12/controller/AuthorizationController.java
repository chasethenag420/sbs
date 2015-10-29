package com.asu.cse545.group12.controller;

import org.apache.log4j.Logger;



import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;




import javax.validation.Valid;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.asu.cse545.group12.constantfile.Const;
import com.asu.cse545.group12.domain.Authorization;
import com.asu.cse545.group12.domain.UserPII;
import com.asu.cse545.group12.domain.Users;
import com.asu.cse545.group12.services.UserService;
import com.asu.cse545.group12.validator.CreateExternalUserValidator;


@Controller
public class AuthorizationController {
	private static final Logger logger = Logger.getLogger(AuthorizationController.class);
	

	@RequestMapping(value= "authorizationEntry")
	public ModelAndView createAuthorizationRequest(@ModelAttribute Authorization authorization,@ModelAttribute Users user) {
		
		//********************************************************************************************
		//      THIS METHOD SHOULD PULL ALL THE DATA FROM DIFFERENT BEANS LIKE who is logged in user, what type of request it is
		//              and SET THE VALUES NEEDED TO GO INTO THE AUTHORIZATION TABLE AT THE TIME OF MAKING INITIAL REQUEST
		//********************************************************************************************
		
		Date curdate = new Date();
		authorization.setRequestCreationTimeStamp(curdate);  // TO FORMAT CAN COME
		
		user.setUserStatus(Const.INACTIVE);
		java.util.Date date = Calendar.getInstance().getTime();
		user.setLastModifieddate(date);
		user.setRegistrationDate(date);
		System.out.println("9999"+user.getUserpii());
		user.setUserId(user.getUserpii().getUserId());
		user.getUserpii().setDateOfBirth(date);

		
		
		//userService.insertRow(user);
		return new ModelAndView("authorizationrequest", "authorization", new Authorization());
	}
	

}

