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


<!-- Keyboard Code start -->
<link href="web_resources/theme/css/jquery-ui.min.css" rel="stylesheet">
<link href="web_resources/theme/css/keyboard.css" rel="stylesheet">


<link href="web_resources/theme/css/bootstrap.css" rel="stylesheet">
<link href="web_resources/theme/css/bootstrap-responsive.css"
	rel="stylesheet">
<title>Create Employee</title>

<style type="text/css">
.wrapper {
	width: 500px;
	margin-left: auto;
	margin-right: auto
}

label {
	padding-left: 0 !important
}

.invalid-form-error-message {
	margin-top: 10px;
	padding: 5px;
}

.invalid-form-error-message.filled {
	border-left: 2px solid red;
}

.parsley-errors-list li {
	color: #B94A48;
	background-color: #F2DEDE;
	border: 1px solid #EED3D7;
	margin: 5px;
}
</style>



</head>
<body>
	<center>
		<c:if test="${!empty message}">
			<div class="alert alert-danger">${message}</div>
		</c:if>
		<div class="container">

			<form class="form-horizontal" id="createEmployeeForm"
				name="createEmployeeForm" method="post" action="signUp"
				data-parsley-validate>
				<legend>Create Employee</legend>

				<table class="table table-striped">
					<tr>
						<td style="white-space: nowrap"><form:label for="fname"
								path="user.firstName">First Name</form:label></td>
						<td><form:input path="user.firstName" id="fname" name="fname"
								data-parsley-required="true" data-parsley-pattern="[a-zA-Z]+" data-parsley-length="[1, 15]"/>
							<form:errors class="alert alert-danger" path="user.firstName" /></td>
					</tr>
					<tr>
						<td style="white-space: nowrap"><form:label for="mname"
								path="user.middleName">Middle(M I)</form:label></td>
						<td><form:input path="user.middleName" id="mname"
								name="mname" data-parsley-pattern="[a-zA-Z]*" data-parsley-length="[0, 15]" /></td>
					</tr>
					<tr>
						<td style="white-space: nowrap"><form:label
								path="user.lastName">Last Name</form:label></td>
						<td><form:input path="user.lastName" id="lname"
								data-parsley-required="true" data-parsley-pattern="[a-zA-Z]+" />
							<form:errors class="alert alert-danger" path="user.lastName" data-parsley-length="[1, 15]"/></td>
					</tr>
					<tr>
						<td style="white-space: nowrap"><form:label for="gender"
								path="user.gender">Gender</form:label></td>
						<td><form:select name="gender" path="user.gender"
								data-parsley-required="true">
								<form:option value="" label="--- Select ---" />
								<form:option value="Male" label="Male" />
								<form:option value="Female" label="Female" />
							</form:select> <form:errors class="alert alert-danger" path="user.gender" /></td>
					</tr>
					<tr>
						<td style="white-space: nowrap"><form:label for="ssn"
								path="userpii.ssn">SSN</form:label></td>
						<td><form:input type="number" name="ssn" path="userpii.ssn"
								id="ssn" data-parsley-required="true" data-parsley-type="digits"
								data-parsley-length="[9, 9]"
								data-parsley-length-message="Should be 9 digits" /> <form:errors
								class="alert alert-danger" path="userpii.ssn" /></td>
					</tr>

					<tr>
						<td style="white-space: nowrap"><form:label for="dob"
								path="userpii.DateOfBirth">Date of Birth</form:label></td>
						<td><form:input name="dob" type="text"
								data-parsley-trigger="change" placeholder="MM/DD/YYYY"
								data-date-format="MM/DD/YYYY" data-date-minDate="01/01/1900"
								data-parsley-mindate="01/01/1900" data-parsley-required="true"
								path="userpii.DateOfBirth" /> <form:errors
								class="alert alert-danger" path="userpii.DateOfBirth" /></td>
					</tr>
					<tr>
						<td style="white-space: nowrap"><form:label for="address"
								path="user.address">Address</form:label></td>
						<td><form:textarea rows="3" cols="30" name="address"
								path="user.address" id="address" data-parsley-required="true"
								data-parsley-type="alphanum" data-parsley-length="[5, 50]"
								data-parsley-length-message="Address should be between 5 to 50 characters" />
							<form:errors class="alert alert-danger" path="user.address" /></td>
					</tr>

					<tr>
						<td style="white-space: nowrap"><form:label for="city"
								path="user.city">City</form:label></td>
						<td><form:input path="user.city" id="city" name="city"
								data-parsley-required="true" data-parsley-pattern="[a-zA-Z]+" data-parsley-length="[1, 15"]/>
							<form:errors class="alert alert-danger" path="user.city" /></td>
					</tr>

					<tr>
						<td style="white-space: nowrap"><form:label for="state"
								path="user.state">State</form:label></td>
						<td><form:input name="state" path="user.state" id="state"
								data-parsley-required="true" data-parsley-pattern="[a-zA-Z]+" data-parsley-length="[1, 15"] />
							<form:errors class="alert alert-danger" path="user.state" /></td>
					</tr>

					<tr>
						<td style="white-space: nowrap"><form:label for="country"
								path="user.country">Country</form:label></td>
						<td><form:input name="country" path="user.country"
								id="country" data-parsley-required="true"
								data-parsley-pattern="[a-zA-Z]+" data-parsley-length="[1, 15"]/> <form:errors
								class="alert alert-danger" path="user.country" /></td>
					</tr>
					<tr>
						<td style="white-space: nowrap"><form:label for="zip"
								path="user.zipcode">ZIP</form:label></td>
						<td><form:input name="zip" path="user.zipcode" id="zip"
								data-parsley-required="true" data-parsley-type="digits"
								data-parsley-length="[5, 5]"
								data-parsley-length-message="Should be 5 digits" /> <form:errors
								class="alert alert-danger" path="user.zipcode" /></td>
					</tr>



					<tr>
						<td style="white-space: nowrap"><form:label for="roleid"
								path="user.roleId">Role</form:label></td>
						<td><form:select name="roleid" path="user.roleId"
								data-parsley-required="true">
								<form:option value="" label="--- Select ---" />
								<form:option value="3" label="Regular" />
								<form:option value="4" label="Manager" />
								<form:option value="5" label="Admin" />
							</form:select> <form:errors class="alert alert-danger" path="user.roleId" /></td>
					</tr>
					<tr>
						<td style="white-space: nowrap"><form:label for="phonenumber"
								path="user.phoneNumber">Phone Number</form:label></td>
						<td><form:input path="user.phoneNumber" id="phno"
								data-parsley-required="true" data-parsley-type="digits"
								data-parsley-length="[10, 10]"
								data-parsley-length-message="Should be 10 digits" /> <form:errors
								class="alert alert-danger" path="user.phoneNumber" /></td>
					</tr>
					<tr>
						<td style="white-space: nowrap"><form:label for="email"
								path="user.emailId">Email</form:label></td>
						<td><form:input type="email" name="email"
								data-parsley-type="email" path="user.emailId" id="email"  data-parsley-length="[1, 25"]/> <form:errors
								class="alert alert-danger" path="user.emailId" /></td>
					</tr>
				</table>
				<input id='submitbutton' class="btn " type="submit" value="Submit" />
				<input class="btn" class="btn" type="submit" value="Cancel" /> <input
					type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
		</div>
	</center>
	<script src="web_resources/theme/js/jquery.min.js"></script>
	<script src="web_resources/theme/js/jquery-ui.min.js"></script>
	<script src="web_resources/theme/js/bootstrap.min.js"></script>

	<!-- keyboard widget css & script (required) -->

	<script src="web_resources/theme/js/jquery.keyboard.js"></script>

	<!-- keyboard extensions (optional) -->
	<script src="web_resources/theme/js/jquery.mousewheel.js"></script>
	<script src="web_resources/theme/js/parsley.min.js"></script>

	<!-- initialize keyboard (required) -->
	
		window.ParsleyValidator.addValidator(
				'mindate',
				function(value, requirement) {
					// is valid date?
					var timestamp = Date.parse(value), minTs = Date
							.parse(requirement);

					return isNaN(timestamp) ? false : timestamp > minTs;
				}, 32).addMessage('en', 'mindate',
				'This date should be greater than %s');
	</script>
	<script type="text/javascript">
		$('#submitbutton').click(function() {
			$('#createEmployeeForm').attr("action", "createEmployeeUser");
		});
		$('#cancel').click(function() {
			$('#createEmployeeForm').attr("action", "goBack");
		});
	</script>
</body>
</html>