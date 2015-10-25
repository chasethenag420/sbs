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
<meta http-equiv="X-Frame-Options" content="allow">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!--  Virtual keyboard code starts here -->
<link href="web_resources/theme/css/jquery-ui.min.css" rel="stylesheet">
<script src="web_resources/theme/js/jquery.min.js"></script>
<script src="web_resources/theme/js/jquery-ui.min.js"></script>
<script src="web_resources/theme/js/bootstrap.min.js"></script>

<!-- keyboard widget css & script (required) -->
<link href="web_resources/theme/css/keyboard.css" rel="stylesheet">
<script src="web_resources/theme/js/jquery.keyboard.js"></script>

<!-- keyboard extensions (optional) -->
<script src="web_resources/theme/js/jquery.mousewheel.js"></script>

<!-- initialize keyboard (required) -->

<!-- Vitual Keyboard code ends here -->

<link href="web_resources/theme/css/bootstrap.css" rel="stylesheet">
<link href="web_resources/theme/css/bootstrap-responsive.css"
	rel="stylesheet">
<title><spring:message code="title.credit"></spring:message></title>
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
		<br /> <br />
		<div style="color: teal; font-size: 30px">Change Password</div>
		<br /> <br />
		<form:form name="changePasswordForm" id="changePasswordForm"
			method="post" modelAttribute="form" >
			<center>
				<c:if test="${!empty errorMessage}">
					<div class="alert alert-danger">${errorMessage}</div>
				</c:if>
				<table class="table table-striped" style="width: auto;">
					<tr></tr>
					<!-- <tr>
						<td style="white-space: nowrap"><form:label
								path="map['currentPassword']">Current Password</form:label></td>
						<td><form:input id="keyboard" type="password" path="map['currentPassword']" /></td>

					</tr> -->
					<tr>
						<td style="white-space: nowrap"><form:label for="password"
								path="map['newPassword']">New Password</form:label></td>
						<td><form:input id="password" name="password" type="password"
								path="map['newPassword']" data-parsley-required="true"
								data-parsley-type="alphanum" data-parsley-length="[6, 15]"
								data-parsley-length-message="Password should be between 6 to 15 characters" /></td>

					</tr>

					<tr>
						<td style="white-space: nowrap"><form:label
								for="confirmPassword" path="map['confirmPassword']">Confirm New Password</form:label>
						</td>
						<td><form:input id="confirmPassword" name="confirmPassword"
								type="password" path="map['confirmPassword']"
								data-parsley-required="true" data-parsley-type="alphanum"
								data-parsley-length="[6, 15]" data-parsley-equalto="#password"
								data-parsley-equalto-message="Paswords didnt match"
								data-parsley-length-message="Confirm Password should be between 6 to 15 characters" /></td>

					</tr>

				</table>
				<br> <input type="submit" class="btn"
					id="submit" value="Submit" /> <input
					type="submit" class="btn" id="cancel"
					value="Cancel" />
				<div>
					<h2>${successfulMessage}</h2>
				</div>
			</center>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form:form>

	</center>
	<script src="web_resources/theme/js/parsley.min.js"></script>

	<script type="text/javascript">
		$(function() {
			$('#password').keyboard();
			$('#confirmPassword').keyboard();

		});
		
		$('#submit').click(function() {
			$('#changePasswordForm').parsley().validate();
			if (true == $('#changePasswordForm').parsley().isValid()) {
				$('#changePasswordForm').parsley().destroy();
				$('#changePasswordForm').attr("action", "setNewPassword");
			} else {
				return false;
			}
		});

		$('#cancel').click(function() {
			$('#changePasswordForm').parsley().destroy();
			$('#changePasswordForm').attr("action", "goBack");

		});
	</script>

</body>
</html>

