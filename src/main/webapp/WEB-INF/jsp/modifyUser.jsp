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
	<div class="container">

		<form class="form-horizontal" name="modifyUserForm" id="modifyUserForm" method="post">
				<fieldset>
		<legend>Modify User Details</legend>
				<h2>${message}</h2>
				
				<table class="table table-striped" style="width: auto;">
					<tr>
						<td style="white-space: nowrap"><form:label for="fname"
								path="user.firstName">First Name</form:label></td>
						<td><form:input path="user.firstName" id="fname" name="fname"
								data-parsley-required="true" data-parsley-pattern="[a-zA-Z]+"
								data-parsley-length="[1, 15]" /> <form:errors
								class="alert alert-danger" path="user.firstName" /></td>
					</tr>
					<tr>
						<td style="white-space: nowrap"><form:label for="mname"
								path="user.middleName">Middle(M I)</form:label></td>
						<td><form:input path="user.middleName" id="mname"
								name="mname" data-parsley-pattern="[a-zA-Z]*"
								data-parsley-length="[0, 15]" /></td>
					</tr>
					<tr>
						<td style="white-space: nowrap"><form:label
								path="user.lastName">Last Name</form:label></td>
						<td><form:input path="user.lastName" id="lname"
								data-parsley-required="true" data-parsley-pattern="[a-zA-Z]+" />
							<form:errors class="alert alert-danger" path="user.lastName"
								data-parsley-length="[1, 15]" /></td>
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
								data-parsley-required="true" data-parsley-pattern="[a-zA-Z]+"
								data-parsley-length="[1, 15]"/> <form:errors
								class="alert alert-danger" path="user.city" /></td>
					</tr>

					<tr>
						<td style="white-space: nowrap"><form:label for="state"
								path="user.state">State</form:label></td>
						<td><form:input name="state" path="user.state" id="state"
								data-parsley-required="true" data-parsley-pattern="[a-zA-Z]+"
								data-parsley-length="[1, 15]" /> <form:errors
								class="alert alert-danger" path="user.state" /></td>
					</tr>

					<tr>
						<td style="white-space: nowrap"><form:label for="country"
								path="user.country">Country</form:label></td>
						<td><form:input name="country" path="user.country"
								id="country" data-parsley-required="true"
								data-parsley-pattern="[a-zA-Z]+" data-parsley-length="[1, 15]"/>
							<form:errors class="alert alert-danger" path="user.country" /></td>
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
								data-parsley-type="email" path="user.emailId" id="email"
								data-parsley-length="[1, 25]"/> <form:errors
								class="alert alert-danger" path="user.emailId" /></td>
					</tr>
					<tr><td colspan="2"><input type="submit" id="update" class="btn btn-danger" value="Update">&nbsp; &nbsp;
					<input type="submit" id="cancel" class="btn btn-danger" value="Cancel"></td></tr>
				</table>

					
					<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
				
			</fieldset>
		</form>
	</div>
</center>
<!-- jQuery -->
	<script src="web_resources/theme/js/jquery.min.js"></script>
	<script src="web_resources/theme/js/parsley.min.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script src="web_resources/theme/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$('#update').click(function() {
			if (true == $('#modifyUserForm').parsley().isValid()) {
				$('#modifyUserForm').attr("action", "modifyUserFormDetails");
			} else {
				return false;
			}
		});
		$('#cancel').click(function() {
			$('#modifyUserForm').parsley().destroy();
			$('#modifyUserForm').attr("action", "goBack");
		});		

	</script>



</body>
</html>