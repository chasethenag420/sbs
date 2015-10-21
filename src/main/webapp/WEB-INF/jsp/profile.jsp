<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="web_resources/theme/css/bootstrap.css" rel="stylesheet">
<link href="web_resources/theme/css/bootstrap-responsive.css"
	rel="stylesheet">

<title>User Profile</title>

<style type="text/css">
.wrapper {
	width: 500px;
	margin-left: auto;
	margin-right: auto
}

label {
	padding-left: 0 !important
}
</style>

</head>

<body>
<<<<<<< HEAD
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
			
			<tr>
			
			</tr>
		</table>
	</center>

	<div class="container">

		<form class="form-horizontal" name="profileForm" id="profileForm" method="post" onsubmit="return OnSubmitForm();">
			<fieldset>
				<legend>User Profile</legend>
				<br /> <br /> <br />
				<center>

					<table>
						<tr>
							<td><label for="username">Username </label></td>
							<td><input type="text" id="username"
								value="${user.userName}"></td>
						</tr>
						<tr>
							<td><label for="FirstName">FirstName </label></td>
							<td><input type="text" id="FirstName"
								value="${user.firstName}"></td>
						</tr>
						<tr>
							<td><label for="LastName">LastName </label></td>
							<td><input type="text" id="LastName"
								value="${user.lastName}"></td>
						</tr>
						<tr>
							<td><label for="MiddleName">MiddleName </label></td>
							<td><input type="text" id="MiddleName"
								value="${user.middleName}"></td>
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
							<td><input type="text" id="phoneNum"
								value="${user.phoneNumber}"></td>
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
						<tr>
							<td><input type="submit"
								onclick="document.pressed=this.value" value="Back" /></td>
						</tr>
						<tr>
							<td></td>
						</tr>
						<tr>
							<td></td>
						</tr>
						<tr>
							<td><input type="submit"
								onclick="document.pressed=this.value"
								value="Set Security Questions" /></td>
						</tr>
						<tr>
							<td></td>
						</tr>
						<tr>
							<td></td>
						</tr>
						<tr>
							<td></td>
						</tr>
						<tr>
							<td><input type="submit"
								onclick="document.pressed=this.value" value="Change Password" /></td>
							<td><input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" /></td>
						</tr>

					</table>
				</center>
			</fieldset>
		</form>
	</div>

	<script type="text/javascript">
		function OnSubmitForm() {
			if (document.pressed == 'Set Security Questions') {
				document.profileForm.action = "enterSecurityQuestions";
			} else if (document.pressed == 'Change Password') {
				document.profileForm.action = "changeUserPassword";
			} else if (document.pressed == 'Back') {
				document.profileForm.action = "goBack";
			}
			return true;
		}
	</script>


</body>
</html>