<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Secure Bank System | Registration Form</title>
</head>
<body>
 <center>

  <div style="color: teal; font-size: 30px">Secure Bank System |
   Registration Form</div>
  <form:form id="registerForm" modelAttribute="user" method="post"
   action="register">
   <table width="400px" height="150px">
   <tr>
     <td><form:label path="username">User Name</form:label>
     </td>
     <td><form:input path="username" />
     </td>
    </tr>
    <tr>
     <td><form:label path="password">Password</form:label>
     </td>
     <td><form:input type="password" path="password" />
     </td>
    </tr>
    <tr>
     <td><form:label path="firstName">First Name</form:label>
     </td>
     <td><form:input path="firstName" />
     </td>
    </tr>
    <tr>
     <td><form:label path="middleName">Middle Name</form:label>
     </td>
     <td><form:input path="middleName" />
     </td>
    </tr>
    <tr>
     <td><form:label path="lastName">Last Name</form:label>
     </td>
     <td><form:input path="lastName" />
     </td>
    </tr>
    <tr>
     <td><form:label path="emailId">Email ID</form:label>
     </td>
     <td><form:input path="emailId" />
     </td>
    </tr>
    <tr>
     <td><form:label path="phoneNumber">Phone Number</form:label>
     </td>
     <td><form:input path="phoneNumber" />
     </td>
    </tr> 
    <tr>
     <td><form:label path="gender">Gender</form:label>
     </td>
     <td><form:select path = "gender">
   	     <form:option value="NONE" label="--- Select ---" />
   	     <form:option value="Male" label="Male" />
   	     <form:option value="Female" label="Female" />
   	     </form:select> 
     </td>
    </tr>
    <tr> 
<%--     <tr>
     <td><form:label path="roleid">Role</form:label>
     </td>
     <td><form:select path = "roleid">
   	     <form:options items="${rolesList}"/>
   	     </form:select> 
     </td>
    </tr> --%>
    <tr> 
     <td><input type="submit" value="Submit" /></td>
     <td><input type="button" value="Cancel" /></td>
    </tr>
   </table>
  </form:form>
 </center>
</body>
</html>

