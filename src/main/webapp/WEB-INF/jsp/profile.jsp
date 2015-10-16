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
<title><spring:message code="title.credit"></spring:message></title>
</head>
<body>
	<center>
		<table>
			<tr>
				<td><label for="username">Username </label></td>
				<td><input type="text" id="username" value="${user.userName}"></td>
			</tr>
			<tr>
				<td><label for="FirstName">FirstName </label></td>
				<td><input type="text" id="FirstName" value="${user.firstName}"></td>
			</tr>
			<tr>
				<td><label for="LastName">LastName </label></td>
				<td><input type="text" id="LastName" value="${user.lastName}"></td>
			</tr>
			<tr>
				<td><label for="MiddleName">MiddleName </label></td>
				<td><input type="text" id="MiddleName" value="${user.middleName}"></td>
			</tr>
			<tr>
				<td><label for="gender">Gender </label></td>
				<td><input type="text" id="gender" value="${user.gender}"></td>
			</tr>
				
				<tr>
				<td><label for="Emailid">EmailId</label></td>
				<td><input type="text" id="Emailid" value="${user.emailId}"></td>
			</tr>
			<tr>
				<td><label for="phoneNum">phoneNumber</label></td>
				<td><input type="text" id="phoneNum" value="${user.phoneNumber}"></td>
			</tr>
			<tr>
				<td><label for="address">Address</label></td>
				<td><input type="text" id="address" value="${user.address}"></td>
			</tr>
			<tr>
				<td><label for="city">City</label></td>
				<td><input type="text" id="city" value="${user.city}"></td>
			</tr>
			<tr>
				<td><label for="state">State</label></td>
				<td><input type="text" id="state" value="${user.state}"></td>
			</tr>
			<tr>
				<td><label for="country">Country</label></td>
				<td><input type="text" id="country" value="${user.country}"></td>
			</tr>
			<tr>
				<td><label for="zipcode">Zipcode</label></td>
				<td><input type="text" id="zipcode" value="${user.zipcode}"></td>
			</tr>
		</table>
	</center>
</body>
</html>