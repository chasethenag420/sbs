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
	<script>
		$(function(){
		$("input[id^='keyboard']").keyboard();
			<!-- $('#keyboard').keyboard(); -->
		});
	</script>
	<!-- Vitual Keyboard code ends here -->

<link href="web_resources/theme/css/bootstrap.css" rel="stylesheet">
<link href="web_resources/theme/css/bootstrap-responsive.css"
	rel="stylesheet">
<title><spring:message code="title.credit"></spring:message></title>
</head>
<body>
	<center>
		<br /> <br />
		<div style="color: teal; font-size: 30px">Change Password</div>
		<br /> <br />
		<form:form name="changePasswordForm" id="changePasswordForm" method="post" modelAttribute="form" onsubmit="return OnSubmitForm();">
			<center>
				<div class="alert alert-danger">${errorMessage}</div>

				<table width="700px" height="150px" cellspacing="10">
					<tr></tr>
					<!-- <tr>
						<td style="white-space: nowrap"><form:label
								path="map['currentPassword']">Current Password</form:label></td>
						<td><form:input id="keyboard" type="password" path="map['currentPassword']" /></td>

					</tr> -->
					<tr>
						<td style="white-space: nowrap"><form:label
								path="map['newPassword']">New Password</form:label></td>
						<td><form:input id="keyboard" type="password" path="map['newPassword']" /></td>

					</tr>

					<tr>
						<td style="white-space: nowrap"><form:label
								path="map['confirmPassword']">Confirm New Password</form:label>
						</td>
						<td><form:input id="keyboard" type="password" path="map['confirmPassword']" /></td>

					</tr>


					<tr>
						<td><input type="submit"
							onclick="document.pressed=this.value" value="Submit" /></td>
						<td><input type="submit"
							onclick="document.pressed=this.value" value="Cancel" /></td>
						<td><input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" /></td>
					</tr>
				</table>
				<div>
					<h2>${successfulMessage}</h2>
				</div>
			</center>
		</form:form>

	</center>

	<script type="text/javascript">
		function OnSubmitForm() {
			if (document.pressed == 'Submit') {
				document.changePasswordForm.action = "setNewPassword";
			} else if (document.pressed == 'Cancel') {
				document.changePasswordForm.action = "goBack";
			}
			return true;
		}
	</script>

</body>
</html>

