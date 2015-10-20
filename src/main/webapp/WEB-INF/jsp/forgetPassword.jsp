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
<link href="web_resources/theme/css/bootstrap.css" rel="stylesheet">
<link href="web_resources/theme/css/bootstrap-responsive.css"
	rel="stylesheet">
<title>Forget Password</title>
</head>
<body>
	<center>
		<br />
		<br />
		<div style="color: teal; font-size: 30px">Forget Password</div>
		<br />
		<br />
		<form:form name="forgetPasswordForm" id="forgetPasswordForm" method="post" modelAttribute="form" onsubmit="return OnSubmitForm();">
			<center>
				<div class="alert alert-danger"> ${errorMessage}</div>
			
				<table width="700px" height="150px" cellspacing="10">
					<tr></tr>
					
					<tr>
						<td style="white-space: nowrap"><form:label path="map['username']">Username</form:label>
						</td>
						<td><form:input path="map['username']" /></td>
					</tr>
					<tr>
						<td style="white-space: nowrap"><form:label path="map['email']">Registered Email</form:label></td>
						<td><form:input type="email" path="map['email']" /></td>
					</tr>
					<tr>
						
						<td><input type="submit"
							onclick="document.pressed=this.value" value="Submit" /></td>
						<td><input type="submit"
							onclick="document.pressed=this.value" value="Cancel" /></td>
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
				document.forgetPasswordForm.action = "setForgetPassword";
			} else if (document.pressed == 'Cancel') {
				document.forgetPasswordForm.action = "goBack";
			}
			return true;
		}
	</script>

</body>
</html>

