package com.asu.cse545.group12.controller;

import org.apache.log4j.Logger;



import java.sql.Date;
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

import com.asu.cse545.group12.domain.UserPII;
import com.asu.cse545.group12.domain.Users;
import com.asu.cse545.group12.services.UserService;
import com.asu.cse545.group12.services.UserPiiService;
import com.asu.cse545.group12.validator.CreateExternalUserValidator;


@Controller
public class UserController {
	private static final Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	//@Autowired
	//UserPiiService userPiiService;
	//public void setUserService(UserService userService) {
	//	this.userService = userService;
	//}

	/* @RequestMapping("home")
 public ModelAndView homePage(Model model) {
	// userService.insertRow(user);
	 Map <String,String> genderList = new LinkedHashMap<String,String>();
	 	genderList.put("Male", "Male");
	 	genderList.put("Female","Female");
		model.addAttribute("genderList", genderList);

		Map <String,String> roleList = new LinkedHashMap<String,String>();			
		roleList.put("manager", "manager");
		roleList.put("employee", "employee");
		model.addAttribute("roleList", roleList);

  return new ModelAndView("user_creation", "user", new User());
 }*/

	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView getForm() {
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("getWelcome is executed!");
		}
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("user", new Users());
		modelView.addObject("userpii", new UserPII());
		modelView.setViewName("signup");
		
		return modelView;
	}

	@RequestMapping( value= "signUpExternalUser")

	public ModelAndView registerUser(@ModelAttribute Users user) {
		//Users user=(Users)modelMap.get("user");
		user.setUserStatus("A");
		java.util.Date date = Calendar.getInstance().getTime();
		user.setLastModifieddate(date);
		user.setRegistrationDate(date);
		System.out.println("9999"+user.getUserpii());
		user.setUserId(user.getUserpii().getUserId());
		user.getUserpii().setDateOfBirth(date);
//		userPII.setDateOfBirth(Calendar.getInstance().getTime());
//		//userPiiService.insertRow(userPII);
//		userPII.setUserId(user.getUserId());
//		user.setuserpii(userPII);
		userService.insertRow(user);
		return new ModelAndView("user_creation", "user", new Users());
	}
	
	public String registerUser(@Valid @ModelAttribute("user") Users user,  BindingResult result, Model model) {
		
		CreateExternalUserValidator validator = new CreateExternalUserValidator();
		validator.validate(user, result);
		 if (result.hasErrors()) {
		        //return "addEmployee";
		        return "signup";
		    } else {
		    	userService.insertRow(user);
		    	return "successfulSignUp";
		    }
	}

	/* @RequestMapping("list")
 public ModelAndView getList() {
  List<Employee> employeeList = dataService.getList();
  return new ModelAndView("list", "employeeList", employeeList);
 }

 @RequestMapping("delete")
 public ModelAndView deleteUser(@RequestParam int id) {
  dataService.deleteRow(id);
  return new ModelAndView("redirect:list");
 }

 @RequestMapping("edit")
 public ModelAndView editUser(@RequestParam int id,
   @ModelAttribute Employee employee) {
  Employee employeeObject = dataService.getRowById(id);
  return new ModelAndView("edit", "employeeObject", employeeObject);
 }

 @RequestMapping("update")
 public ModelAndView updateUser(@ModelAttribute Employee employee) {
  dataService.updateRow(employee);
  return new ModelAndView("redirect:list");
 }*/

}

