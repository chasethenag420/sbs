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

<center>
	<div class="container">

		<form class="form-horizontal" name="modifyUserForm" id="modifyUserForm" method="post">
				<fieldset>
		<legend>Modify User Details</legend>
				<h2>${message}</h2>
				<div class="control-group">
					<form:label class="control-label" path="user.firstName">First Name</form:label>
					<form:input class="controls" path="user.firstName" />
					<form:errors class="alert alert-danger" path="user.firstName" />
				</div>
				<div class="control-group">
					<form:label class="control-label" path="user.middleName">Middle(M I)</form:label>
					<form:input  class="controls" path="user.middleName" />
				</div>
				<div class="control-group">
					<form:label class="control-label" path="user.lastName">Last Name</form:label>
					<form:input class="controls" path="user.lastName" />
					<form:errors class="alert alert-danger" path="user.lastName" />
				</div>

				<div class="control-group">
					<form:label class="control-label" path="user.address">Address</form:label>
					<form:textarea class="controls" rows="3" cols="30" path="user.address" />
					<form:errors class="alert alert-danger" path="user.address" />

				</div>
				
				<div class="control-group">
					<form:label class="control-label" path="user.city">City</form:label>
					<form:input class="controls" path="user.city" />
					<form:errors class="alert alert-danger" path="user.city" />
				</div>
				
				<div class="control-group">
					<form:label class="control-label" path="user.state">State</form:label>
					<form:input class="controls" path="user.state" />
					<form:errors class="alert alert-danger" path="user.state" />
				</div>
				
				<div class="control-group">
					<form:label class="control-label" path="user.country">Country</form:label>
					<form:input class="controls" path="user.country" />
					<form:errors class="alert alert-danger" path="user.country" />

				</div>
				<div class="control-group">
					<form:label class="control-label" path="user.zipcode">ZIP</form:label>
					<form:input class="controls" path="user.zipcode" />
					<form:errors class="alert alert-danger" path="user.zipcode" />

				</div>

				<div class="control-group">
					<form:label class="control-label" path="user.phoneNumber">Phone Number</form:label>
					<form:input class="controls" path="user.phoneNumber" />
					<form:errors class="alert alert-danger" path="user.phoneNumber" />
				</div>
				<div class="control-group">
					<form:label class="control-label" path="user.emailId">Email</form:label>
					<form:input class="controls" type="email" path="user.emailId" />
					<form:errors class="alert alert-danger" path="user.emailId" />
				</div>

					<br>
					<input type="submit" id="update" class="btn btn-danger" value="Update">
					<input type="submit" id="cancel" class="btn btn-danger" value="Cancel">
					<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
				
			</fieldset>
		</form>
	</div>
</center>
<!-- jQuery -->
	<script src="web_resources/theme/js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="web_resources/theme/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$('#update').click(function() {
			$('#modifyUserForm').attr("action", "modifyUserFormDetails");
		});
		$('#cancel').click(function() {
			$('#modifyUserForm').attr("action", "goBack");
		});

	</script>



</body>
</html>