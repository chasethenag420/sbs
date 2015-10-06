<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign Up Form for new Customer</title>

<style>
body {
	background-color: linen;
}

h1 {
	color: maroon;
	margin-left: 40px;
}

td {
	border: 1px dotted #999999;
}
</style>
</head>
<body>
	<center>
		<h3>Welcome, Enter Customer Details</h3>
		<form id="signUpForm" method="post" action="signUpExternalUser">
			<table width="400px" height="150px">

				<tr>
					<td><form:label path="user.userName">Username</form:label></td>
					<td><form:input path="user.userName" /></td>
					<td class="error"><form:errors path="user.userName" /></td>

				</tr>
				<tr>
					<td><form:label path="user.password">Password</form:label></td>
					<td><form:input type="password" path="user.password" /></td>
					<td class="error"><form:errors path="user.password" /></td>
				</tr>
				<tr>
					<td><form:label path="user.firstName">First Name</form:label></td>
					<td><form:input path="user.firstName" /></td>
					<td class="error"><form:errors path="user.firstName" /></td>
				</tr>
				<tr>
					<td><form:label path="user.middleName">Middle(M I)</form:label></td>
					<td><form:input path="user.middleName" /></td>
				</tr>
				<tr>
					<td><form:label path="user.lastName">Last Name</form:label></td>
					<td><form:input path="user.lastName" /></td>
					<td class="error"><form:errors path="user.lastName" /></td>
				</tr>

				<tr>
					<td><form:label path="user.gender">Gender</form:label></td>
					<td><form:select path="user.gender">
							<form:option value="NONE" label="--- Select ---" />
							<form:option value="M" label="Male" />
							<form:option value="F" label="Female" />
						</form:select></td>
					<td class="error"><form:errors path="user.gender" /></td>
				</tr>
				<tr>
					<td><form:label path="user.roleId">Role</form:label></td>
					<td><form:select path="user.roleId">
							<form:option value="-1" label="--- Select ---" />
							<form:option value="0" label="Individual" />
							<form:option value="1" label="Organization/Merchant" />
						</form:select></td>
					<td class="error"><form:errors path="user.roleId" /></td>
				</tr>

				<tr>
					<td><form:label path="user.phoneNumber">Phone Number</form:label></td>
					<td><form:input path="user.phoneNumber" /></td>
					<td class="error"><form:errors path="user.phoneNumber" /></td>

				</tr>
				<tr>
					<td><form:label path="user.emailId">Email</form:label></td>
					<td><form:input path="user.emailId" /></td>
					<td class="error"><form:errors path="user.emailId" /></td>

				</tr>
				<tr>
					<td><form:label path="user.userpii.ssn">SSN</form:label></td>
					<td><form:input path="user.userpii.ssn" /></td>
					<td class="error"><form:errors path="userpii.ssn" /></td>

				</tr>
				
				<tr>
					<td><input type="submit" value="Submit" /></td>
					<td><input type="button" value="Cancel" /></td>
				</tr>

			</table>
		</form>
	</center>
</body>
</html>