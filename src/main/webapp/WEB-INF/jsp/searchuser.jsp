<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="messages" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-Frame-Options" content="allow">
<title>
Search User
</title>
</head>
<body>
 <center>

  <div style="color: teal; font-size: 30px">Search User</div>
  <form:form id="searchUser"  method="post" modelAttribute="form"
   action="getuserlist">
   <center>
   <table  width="700px" height="150px" cellspacing="10">
    <tr>
     <td style="white-space: nowrap"><form:label path="map['accountNumber']"> Account Number</form:label>
     </td>
     <td><form:input path="map['accountNumber']" />
     </td>
    </tr>
    <tr>
     <td style="white-space: nowrap"><form:label path="map['userName']">User Name</form:label>
     </td>
     <td><form:input path="map['userName']" />
     </td>
    </tr>
    <tr>
     <td style="white-space: nowrap"><form:label path="map['firstName']">First Name</form:label>
     </td>
     <td><form:input path="map['firstName']" />
     </td>
    </tr>
    <tr>
     <td style="white-space: nowrap"><form:label path="map['lastName']">Last Name</form:label>
     </td>
     <td><form:input path="map['lastName']" />
     </td>
    </tr>
    <tr>
     <td style="white-space: nowrap"><form:label path="map['emailId']">Email Id</form:label>
     </td>
     <td><form:input path="map['emailId']" />
     </td>
    </tr>
    <tr>
     <td style="white-space: nowrap"><form:label path="map['phoneNumber']">Phone Number</form:label>
     </td>
     <td><form:input path="map['phoneNumber']" />
     </td>
    </tr>
    <tr> 
     <td><input type="submit" value="Search" /></td>
     <td><input type="button" value="Cancel" /></td>
    </tr>
   </table>
   </center>
  </form:form>
  
 </center>
</body>
</html>

