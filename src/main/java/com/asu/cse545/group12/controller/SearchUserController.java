package com.asu.cse545.group12.controller;

import java.util.ArrayList;
import java.util.Calendar;
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

import com.asu.cse545.group12.constantfile.Const;
import com.asu.cse545.group12.dao.UserDao;
import com.asu.cse545.group12.domain.AccessControl;
import com.asu.cse545.group12.domain.Authorization;
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
	public ModelAndView searchUser(@ModelAttribute("form") Form form, HttpServletRequest request) {
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("Modify User data requested");
		}

		Users loginUser = userservice.getUserByUserName((String)request.getSession(false).getAttribute("username"));
		Map<String, String> formMap=form.getMap();
		List<Users> userList = null;
		if(!Utils.isEmpty(formMap.get("accountNumber"))){
			Users user = userservice.getUsersByAccountNumber(Integer.parseInt(formMap.get("accountNumber")));
			if(user!=null){
				userList=new ArrayList<Users>();
				userList.add(user);
			}
		} else if(!Utils.isEmpty(formMap.get("userName"))){
			//only get the external users
			Users user = userservice.getUserByUserName(formMap.get("userName"));
			if(user!=null)
			{
				userList=new ArrayList<Users>();
				//only return if the user is external user
				if(loginUser != null && loginUser.getRoleId() == 5)
				{
					//for admin give only internal bank employee
					if(user.getRoleId() == 3 || user.getRoleId() == 4 || user.getRoleId() == 5 || user.getRoleId() == 6)
					{
						userList.add(user);
					}
				}
				//for manager and regular user, give external users only
				else
				{
					if(user.getRoleId() == 1 || user.getRoleId() == 2)
					{
						userList.add(user);
					}
				}
			}
		}else if(!Utils.isEmpty(formMap.get("firstName"))){
			List<Users> tempUserList = userservice.getUsersByFirstName(formMap.get("firstName"));
			if(tempUserList!=null){
				userList=new ArrayList<Users>();
				for(int i=0; i<tempUserList.size(); i++)
				{
					if(loginUser != null && loginUser.getRoleId() == 5)
					{
						//for admin give only internal bank employee
						if(tempUserList.get(i)!= null && (tempUserList.get(i).getRoleId() == 3 || tempUserList.get(i).getRoleId() == 4 || tempUserList.get(i).getRoleId() == 5 || tempUserList.get(i).getRoleId() == 6))
							userList.add(tempUserList.get(i));
					}
					else
					{
						//only return if the user is external user for Manager and Regular
						if(tempUserList.get(i)!= null && (tempUserList.get(i).getRoleId() == 1 || tempUserList.get(i).getRoleId() == 2))
							userList.add(tempUserList.get(i));
					}
				}
			}
		}else if(!Utils.isEmpty(formMap.get("lastName"))){
			List<Users> tempUserList = userservice.getUsersByLastName(formMap.get("lastName"));
			if(tempUserList!=null){
				userList=new ArrayList<Users>();
				for(int i=0; i<tempUserList.size(); i++)
				{
					if(loginUser != null && loginUser.getRoleId() == 5)
					{
						//for admin give only internal bank employee
						if(tempUserList.get(i)!= null && (tempUserList.get(i).getRoleId() == 3 || tempUserList.get(i).getRoleId() == 4 || tempUserList.get(i).getRoleId() == 5|| tempUserList.get(i).getRoleId() == 6))
							userList.add(tempUserList.get(i));
					}
					else
					{
						//only return if the user is external user for Manager and Regular
						if(tempUserList.get(i)!= null && (tempUserList.get(i).getRoleId() == 1 || tempUserList.get(i).getRoleId() == 2))
							userList.add(tempUserList.get(i));
					}
				}
			}
		}else if(!Utils.isEmpty(formMap.get("emailId"))){
			List<Users> tempUserList = userservice.getUsersByEmailId(formMap.get("emailId"));
			if(tempUserList!=null){
				userList=new ArrayList<Users>();
				for(int i=0; i<tempUserList.size(); i++)
				{
					if(loginUser != null && loginUser.getRoleId() == 5)
					{
						//for admin give only internal bank employee
						if(tempUserList.get(i)!= null && (tempUserList.get(i).getRoleId() == 3 || tempUserList.get(i).getRoleId() == 4 || tempUserList.get(i).getRoleId() == 5|| tempUserList.get(i).getRoleId() == 6))
							userList.add(tempUserList.get(i));
					}
					else
					{
						//only return if the user is external user for Manager and Regular
						if(tempUserList.get(i)!= null && (tempUserList.get(i).getRoleId() == 1 || tempUserList.get(i).getRoleId() == 2))
							userList.add(tempUserList.get(i));
					}
				}
			}
		}else if(!Utils.isEmpty(formMap.get("phoneNumber"))){
			List<Users> tempUserList = userservice.getUsersByPhoneNumber(formMap.get("phoneNumber"));
			if(tempUserList!=null){
				userList=new ArrayList<Users>();
				for(int i=0; i<tempUserList.size(); i++)
				{
					if(loginUser != null && loginUser.getRoleId() == 5)
					{
						//for admin give only internal bank employee
						if(tempUserList.get(i)!= null && (tempUserList.get(i).getRoleId() == 3 || tempUserList.get(i).getRoleId() == 4 || tempUserList.get(i).getRoleId() == 5|| tempUserList.get(i).getRoleId() == 6))
							userList.add(tempUserList.get(i));
					}
					else
					{
						//only return if the user is external user for Manager and Regular
						if(tempUserList.get(i)!= null && (tempUserList.get(i).getRoleId() == 1 || tempUserList.get(i).getRoleId() == 2))
							userList.add(tempUserList.get(i));
					}
				}
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

	@RequestMapping(value="raiseInternalRequest",method=RequestMethod.POST)
	public ModelAndView getRaiseRequestForm(@ModelAttribute("form") Form form, HttpServletRequest request){
		ModelAndView model = new ModelAndView();
		Map<String, String> formMap = form.getMap();
		request.getSession(false).setAttribute("raiseInternalRequestuserId",formMap.get("userId") );
		model.addObject("user", new Users());
		model.addObject("authorization", new Authorization());
		model.setViewName("regularEmprequest");
		return model;
	}


	@RequestMapping(value = "regularrequest")
	public ModelAndView getInteralEmplRequest(@ModelAttribute("authorization") Authorization authorization,HttpServletRequest request) {
		if(logger.isDebugEnabled()){
			logger.debug("create request");
		}
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("message", "You are not allowed to raise request");
		modelView.addObject("form", new Form());


		try{
			Integer userId=Integer.parseInt((String)request.getSession(false).getAttribute("raiseInternalRequestuserId"));

			HttpSession session = request.getSession(false);
			String reuqesterusername=(String) session.getAttribute("username");
			Users requesteruser = userservice.getUserByUserName(reuqesterusername);
			int requesteruserid = requesteruser.getUserId();
			if(logger.isDebugEnabled()){
				logger.debug("requesteruserid: "+requesteruserid);
				logger.debug("requestedto: "+userId);
			}
			authorization.setAuthorizedByUserId(userId);
			authorization.setAuthorizedToUserId(requesteruserid);

			//CHANGE THE ROLE ID TO 0 SO THAT ONLY THE USERS INVOLVED CAN SEE THE NOTIFICATIONS APART FROM MANAGER		
			authorization.setAssignedToRole(Const.NOROLEID);

			authorization.setRequestStatus(Const.PENDING);
			authorization.setRequestCreationTimeStamp(Calendar.getInstance().getTime());
			authorizationService.regularEmpRequest(authorization);	
			modelView.addObject("message", "Request created successfully");
			modelView.setViewName(getViewName(reuqesterusername));
			// need to write the message that request was successful.
		}catch(Exception e){
			e.printStackTrace();
			modelView.setViewName("searchuser");
		}
		return modelView;

	}

	@RequestMapping(value = "/viewExternalprofileform",method=RequestMethod.POST)
	public ModelAndView getExternalprofile(@ModelAttribute("form") Form form, HttpServletRequest request) {
		if (logger.isDebugEnabled()) {
			logger.debug("viewExternalprofileform");
		}
		ModelAndView model = new ModelAndView();
		model.setViewName("searchuser");
		model.addObject("message", "You are not authorized View. Raise a request");
		model.addObject("form", new Form());
		try{
			Map<String, String> formMap = form.getMap();
			Integer userId= Integer.parseInt(formMap.get("userId"));
			Users user = userDao.getUserByUserId(userId);
			int requesttoUserId=user.getUserId();

			//List<Authorization> authorizationList = authorizationService.getAuthorizedNotifications(requesterUserId, requesttoUserId, "VIEWPROFILE","APPROVED");
			List<AccessControl> accessControl= authorizationService.getAccessControlToView(requesttoUserId,3); 
			if(isAdmin(request) || isManager(request)||user!=null && accessControl != null && !accessControl.isEmpty() && accessControl.size()==1)
			{
				model.addObject("user",user);
				model.setViewName("viewExternalprofile");
			}else{
				model.addObject("message", "You are not authorized View. Raise a request");
				model.addObject("user",null);
			}
		}catch(Exception e){
			e.printStackTrace();
		}


		return model;


	}


	@RequestMapping(value = "/modifyUserForm",method=RequestMethod.POST)
	public ModelAndView modifyUser(@ModelAttribute("form") Form form,HttpServletRequest request) {
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("Modify User data requested");
		}
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("form", new Form());
		modelView.setViewName("searchuser");
		modelView.addObject("user",null);
		modelView.addObject("message", "You are not authorized to Modify. Raise a request");
		try{
			Map<String, String> formMap=form.getMap();
			int userId = Integer.parseInt(formMap.get("userId"));
			Users user= userDao.getUserByUserId(userId);
			List<AccessControl> accessControl= authorizationService.getAccessControlToModify(userId,3); 

			if(isAdmin(request) ||isManager(request)|| user!=null && accessControl != null && !accessControl.isEmpty() && accessControl.size()==1)
			{
				request.getSession(false).setAttribute("modifyuserId",formMap.get("userId") );
				modelView.addObject("user", user);
				modelView.addObject("message", "");
				modelView.setViewName("modifyUser");
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		return modelView;
	}

	@RequestMapping(value = "/modifyUserFormDetails",method=RequestMethod.POST)
	public ModelAndView modifyUser(@Valid @ModelAttribute("user") Users user, BindingResult result, HttpServletRequest request) {
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("Modify User data update "+user.toString());
		}
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("form", new Form());
		modelView.setViewName("searchuser");
		modelView.addObject("user",null);
		modelView.addObject("message", "You are not authorized to Modify. Raise a request");
		try{
			Integer userId=Integer.parseInt((String)request.getSession(false).getAttribute("modifyuserId"));

			List<AccessControl> accessControl= authorizationService.getAccessControlToModify(userId,3); 
			Users userDB = userDao.getUserByUserId(userId);		

			if(isAdmin(request) || isManager(request)||userDB !=null && accessControl != null && !accessControl.isEmpty() && accessControl.size()==1)
			{

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
				int userID = userDao.updateRow(userDB);
				if(logger.isDebugEnabled()){
					logger.debug("Modify User ID "+userID);
				}
				modelView.addObject("user", user);
				modelView.addObject("message", "Updated SuccessFully");
				modelView.setViewName("searchuser");					

			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return modelView;

	}

	@RequestMapping(value = "/deleteUser",method=RequestMethod.POST)
	public ModelAndView deleteUser(@ModelAttribute("form") Form form,HttpServletRequest request) {
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("Delete User data requested");
		}

		ModelAndView modelView = new ModelAndView();
		modelView.addObject("form", new Form());
		modelView.setViewName("searchuser");
		modelView.addObject("message", "You are not authorized to Delete. Raise a request");
		try{
			HttpSession session = request.getSession(false);
			String username=(String)session.getAttribute("username");
			Map<String, String> formMap=form.getMap();
			Integer userId= Integer.parseInt(formMap.get("userId"));
			Users user= userDao.getUserByUserId(userId);
			List<AccessControl> accessControl= authorizationService.getAccessControlToDelete(userId,3); 

			if(isAdmin(request) || isManager(request)|| user!=null && accessControl != null && !accessControl.isEmpty() && accessControl.size()==1)
			{
				request.getSession(false).setAttribute("modifyuserId",formMap.get("userId") );
				userservice.deActivateUserByUserId(userId);
				modelView.addObject("message", "Deleted SuccessFully");
				modelView.setViewName("searchuser");
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		return modelView;
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
	private boolean isAdmin(HttpServletRequest request){
		Users loginUser = userservice.getUserByUserName((String)request.getSession(false).getAttribute("username"));
		if(loginUser != null && loginUser.getRoleId() == 5)
		{			
			return true;
		}else{
			return false;
		}
	}
	private boolean isManager(HttpServletRequest request){
		Users loginUser = userservice.getUserByUserName((String)request.getSession(false).getAttribute("username"));
		if(loginUser != null && loginUser.getRoleId() == 4)
		{			
			return true;
		}else{
			return false;
		}
	}

}
