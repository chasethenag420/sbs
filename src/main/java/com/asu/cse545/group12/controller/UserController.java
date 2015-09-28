package com.asu.cse545.group12.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.asu.cse545.group12.domain.User;
import com.asu.cse545.group12.services.UserService;

@Controller
public class UserController {

 @Autowired
 UserService userService;

 @RequestMapping("form")
 public ModelAndView getForm(@ModelAttribute User user) {
  return new ModelAndView("form");
 }

 @RequestMapping("register")
 public ModelAndView registerUser(@ModelAttribute User user) {
	 userService.insertRow(user);
  return new ModelAndView("form");
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

