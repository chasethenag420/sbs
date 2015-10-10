[1mdiff --git a/src/main/java/com/asu/cse545/group12/controller/UserController.java b/src/main/java/com/asu/cse545/group12/controller/UserController.java[m
[1mindex 6ec1930..c1f17e8 100644[m
[1m--- a/src/main/java/com/asu/cse545/group12/controller/UserController.java[m
[1m+++ b/src/main/java/com/asu/cse545/group12/controller/UserController.java[m
[36m@@ -4,7 +4,6 @@[m [mimport org.apache.log4j.Logger;[m
 [m
 [m
 [m
[31m-[m
 import java.util.Date;[m
 import java.text.SimpleDateFormat;[m
 import java.util.Calendar;[m
[36m@@ -18,7 +17,6 @@[m [mimport javax.validation.Valid;[m
 [m
 [m
 [m
[31m-[m
 import org.springframework.beans.factory.annotation.Autowired;[m
 import org.springframework.beans.propertyeditors.CustomDateEditor;[m
 import org.springframework.beans.propertyeditors.CustomNumberEditor;[m
[36m@@ -32,6 +30,7 @@[m [mimport org.springframework.web.bind.ServletRequestDataBinder;[m
 import org.springframework.web.bind.WebDataBinder;[m
 import org.springframework.web.bind.annotation.InitBinder;[m
 import org.springframework.web.bind.annotation.ModelAttribute;[m
[32m+[m[32mimport org.springframework.web.bind.annotation.PathVariable;[m[41m[m
 import org.springframework.web.bind.annotation.RequestMapping;[m
 import org.springframework.web.bind.annotation.RequestMethod;[m
 import org.springframework.web.bind.annotation.RequestParam;[m
[36m@@ -42,7 +41,6 @@[m [mimport org.springframework.stereotype.Service;[m
 [m
 import com.asu.cse545.group12.domain.UserPII;[m
 import com.asu.cse545.group12.domain.Users;[m
[31m-import com.asu.cse545.group12.services.AuthorizationService;[m
 import com.asu.cse545.group12.services.UserService;[m
 import com.asu.cse545.group12.validator.CreateExternalUserValidator;[m
 [m
[36m@@ -52,17 +50,14 @@[m [mpublic class UserController {[m
 	private static final Logger logger = Logger.getLogger(UserController.class);[m
 [m
 	@Autowired[m
[31m-	UserService userService;[m
[31m-	[m
[31m-	@Autowired[m
[31m-	AuthorizationService  authorizationService;[m
[32m+[m	[32mUserService userService;[m[41m	[m
 [m
 	@Autowired[m
 	private PasswordEncoder passwordEncoder;[m
 [m
 [m
 	@RequestMapping( value= "signUpExternalUser")[m
[31m-	public String registerUser(@ModelAttribute("userpii") UserPII userpii, @Valid @ModelAttribute("user") Users user, BindingResult result, Model model) {[m
[32m+[m	[32mpublic String registerUser( @RequestParam("userpii.ssn") Integer ssn,@ModelAttribute("userpii") UserPII userpii, @Valid @ModelAttribute("user") Users user, BindingResult result, Model model) {[m[41m[m
 [m
 		CreateExternalUserValidator validator = new CreateExternalUserValidator();[m
 		validator.validate(user, result);[m
[36m@@ -85,19 +80,10 @@[m [mpublic class UserController {[m
 			 * 0 none[m
 			 * [m
 			 * */[m
[31m-			[m
[31m-			if(user.getRoleId()==5)[m
[31m-				user.setRoleId(5);[m
[31m-			if(user.getRoleId()==4)[m
[31m-				user.setRoleId(4);[m
[31m-			if(user.getRoleId()==3)[m
[31m-				user.setRoleId(3);[m
[31m-			if(user.getRoleId()==2)[m
[31m-				user.setRoleId(2);[m
[31m-			if(user.getRoleId()==1)[m
[31m-				user.setRoleId(1);[m
[32m+[m			[32muser.setRoleId(5);[m[41m[m
 			userpii.setDateOfBirth(date);[m
 			userpii.setUser(user);[m
[32m+[m			[32muserpii.setSsn(ssn);[m[41m[m
 			user.setUserpii(userpii);[m
 [m
 			if(logger.isDebugEnabled()){[m
[36m@@ -108,7 +94,6 @@[m [mpublic class UserController {[m
 			}[m
 [m
 			userService.insertRow(user);[m
[31m-			authorizationService.signupInsertRow(user);[m
 			return "successfulSignUp";[m
 		}[m
 	}[m
[1mdiff --git a/src/main/java/com/asu/cse545/group12/domain/UserPII.java b/src/main/java/com/asu/cse545/group12/domain/UserPII.java[m
[1mindex 6de8d1c..66d63b8 100644[m
[1m--- a/src/main/java/com/asu/cse545/group12/domain/UserPII.java[m
[1m+++ b/src/main/java/com/asu/cse545/group12/domain/UserPII.java[m
[36m@@ -33,7 +33,7 @@[m [mpublic class UserPII implements Serializable{[m
 	private Integer userId;[m
 [m
 	@Column(name = "SSN")[m
[31m-	private String ssn;[m
[32m+[m	[32mprivate Integer ssn;[m[41m[m
 [m
 [m
 [m
[36m@@ -52,11 +52,11 @@[m [mpublic class UserPII implements Serializable{[m
 		this.userId = userId;[m
 	}[m
 [m
[31m-	public String getSsn() {[m
[32m+[m	[32mpublic Integer getSsn() {[m[41m[m
 		return ssn;[m
 	}[m
 [m
[31m-	public void setSsn(String ssn) {[m
[32m+[m	[32mpublic void setSsn(Integer ssn) {[m[41m[m
 		this.ssn = ssn;[m
 	}[m
 [m
[1mdiff --git a/src/main/resources/database.properties b/src/main/resources/database.properties[m
[1mindex a7d65b9..a801838 100644[m
[1m--- a/src/main/resources/database.properties[m
[1m+++ b/src/main/resources/database.properties[m
[36m@@ -1,5 +1,5 @@[m
 database.driver=com.mysql.jdbc.Driver[m
[31m-database.url=jdbc:mysql://localhost:3306/ss[m
[32m+[m[32mdatabase.url=jdbc:mysql://localhost:3306/test[m
 database.user=root[m
[31m-database.password=root[m
[32m+[m[32mdatabase.password=ganesh[m
 hibernate.show_sql=true[m
\ No newline at end of file[m
[1mdiff --git a/src/main/webapp/WEB-INF/jsp/signup.jsp b/src/main/webapp/WEB-INF/jsp/signup.jsp[m
[1mindex f8bcca7..cdfd579 100644[m
[1m--- a/src/main/webapp/WEB-INF/jsp/signup.jsp[m
[1m+++ b/src/main/webapp/WEB-INF/jsp/signup.jsp[m
[36m@@ -72,7 +72,7 @@[m [mlabel {[m
 				</div>[m
 				<div class="control-group">[m
 					<form:label class="control-label" path="userpii.ssn">SSN</form:label>[m
[31m-					<form:input type="number" class="controls" path="userpii.ssn" />[m
[32m+[m					[32m<form:input type="number" class="controls" path="userpii.ssn" name="userpii.ssn"/>[m[41m[m
 					<form:errors class="alert alert-danger" path="userpii.ssn" />[m
 [m
 				</div>[m
