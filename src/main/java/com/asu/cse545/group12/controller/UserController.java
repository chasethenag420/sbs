package com.asu.cse545.group12.controller;

import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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


@Controller
public class UserController {
	private static final Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	
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
		modelView.addObject("userPII", new UserPII());
		modelView.setViewName("signup");
		
		return modelView;
	}

	@RequestMapping("signUpExternalUser")
	public ModelAndView registerUser(@ModelAttribute Users user, @ModelAttribute UserPII userPII) {
		//Users user=(Users)modelMap.get("user");
		userService.insertRow(user);
		return new ModelAndView("user_creation", "user", new Users());
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

