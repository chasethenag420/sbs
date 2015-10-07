package com.asu.cse545.group12.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class NotificationController {
		private static final Logger logger = Logger.getLogger(HomePageController.class);
		@RequestMapping("/notifications")
		public ModelAndView homePage(Model model) {
			return new ModelAndView("notifications");
			}
}
