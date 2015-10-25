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
<link href="web_resources/theme/css/bootstrap.css" rel="stylesheet">
<link href="web_resources/theme/css/bootstrap-responsive.css"
	rel="stylesheet">
<title>Search User</title>
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
		<br>
		<h2>${message}</h2>
		<div style="color: teal; font-size: 30px">Search User</div>
		<br>
		<form:form id="searchUser" method="post" modelAttribute="form">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<table class="table table-striped" style="width: auto;">
				<tr>
					<td style="white-space: nowrap"><form:label for="accountnum"
							path="map['accountNumber']"> Account Number</form:label></td>
					<td><form:input path="map['accountNumber']" name="accountnum"
							data-parsley-length="[1,10]" data-parsley-required="true"
							data-parsley-type="digits" /></td>
				</tr>
				<tr>
					<td style="white-space: nowrap"><form:label
							path="map['userName']" for="username">User Name</form:label></td>
					<td><form:input path="map['userName']" name="username"
							 data-parsley-required="true"
							data-parsley-type="alphanum" data-parsley-length="[6, 15]"
							data-parsley-length-message="Username should be between 6 to 15 characters" /></td>
				</tr>
				<tr>
					<td style="white-space: nowrap"><form:label
							path="map['firstName']" for="fname">First Name</form:label></td>
					<td><form:input path="map['firstName']" name="fname"
							data-parsley-required="true" data-parsley-pattern="[a-zA-Z ]+"
							data-parsley-length="[1, 15]" /></td>
				</tr>
				<tr>
					<td style="white-space: nowrap"><form:label
							path="map['lastName']" for="lname">Last Name</form:label></td>
					<td><form:input path="map['lastName']" name="lname"
							data-parsley-required="true" data-parsley-pattern="[a-zA-Z ]+"
							data-parsley-length="[1, 15]" /></td>
				</tr>
				<tr>
					<td style="white-space: nowrap"><form:label
							path="map['emailId']" for="email">Email Id</form:label></td>
					<td><form:input path="map['emailId']" name="email" data-parsley-required="true"
							data-parsley-type="email" id="email"
							data-parsley-length="[1, 25]" /></td>
				</tr>
				<tr>
					<td style="white-space: nowrap"><form:label
							path="map['phoneNumber']" for="phonenumber">Phone Number</form:label></td>
					<td><form:input path="map['phoneNumber']" name="phonenumber"
							data-parsley-required="true" data-parsley-type="digits"
							data-parsley-length="[10, 10]"
							data-parsley-length-message="Should be 10 digits" /></td>
				</tr>
			</table>
			<input class="btn" type="submit" id="search" value="Search" />
			<input class="btn" type="submit" id="cancel" value="Cancel" />
		</form:form>

	</center>
	<!-- jQuery -->
	<script src="web_resources/theme/js/jquery.min.js"></script>


	<!-- Bootstrap Core JavaScript -->
	<script src="web_resources/theme/js/bootstrap.min.js"></script>
	<script src="web_resources/theme/js/parsley.min.js"></script>
	<script type="text/javascript">
		$('#search').click(function() {

			$('#searchUser').parsley().validate();
			if (true == $('#searchUser').parsley().isValid()) {
				$('#searchUser').parsley().destroy();
				$$('#searchUser').attr("action", "getuserlist");
			} else {
				return false;
			}
		});
		$('#cancel').click(function() {
			$('#searchUser').parsley().destroy();
			$('#searchUser').attr("action", "goBack");
		});
	</script>
</body>
</html>

