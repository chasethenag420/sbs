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
     <td><form:label path="firstName">First Name</form:label>
     </td>
     <td><form:input path="firstName" />
     </td>
    </tr>
    <tr>
     <td><form:label path="lastName">Last Name</form:label>
     </td>
     <td><form:input path="lastName" />
     </td>
    </tr>
    <tr>
     <td><form:label path="emailid">Email ID</form:label>
     </td>
     <td><form:input path="emailid" />
     </td>
    </tr>
    <tr>
     <td><form:label path="phonenum">Phone Number</form:label>
     </td>
     <td><form:input path="phonenum" />
     </td>
    </tr> 
    <tr> 
     <td><input type="submit" value="Submit" /></td>
     <td><input type="button" value="Cancel" /></td>
    </tr>
   </table>
  </form:form>
 </center>
</body>
</html>

