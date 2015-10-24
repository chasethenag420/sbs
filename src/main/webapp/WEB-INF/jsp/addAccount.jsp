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
<title><spring:message code="title.credit"></spring:message></title>
</head>
<body>
	<center>
		<br /> <br />
		<div style="color: teal; font-size: 30px">Add Account</div>
		<br /> <br />
		<form:form name="addAccount" id="addAccount" method="post"
			onsubmit="return OnSubmitForm();">
			<center>
				<h3>Do you want to add saving account?</h3>
				<br />
				<div class="alert alert-danger">${errorMessage}</div>
				<input type="submit" onclick="document.pressed=this.value"
					value="Submit" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
					type="submit" onclick="document.pressed=this.value" value="Cancel" />
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />

				<div>
					<h2>${successfulMessage}</h2>
				</div>
			</center>
		</form:form>

	</center>

	<script type="text/javascript">
		function OnSubmitForm() {
			if (document.pressed == 'Submit') {
				document.addAccount.action = "addUserAccount";
			} else if (document.pressed == 'Cancel') {
				document.addAccount.action = "goBack";
			}
			return true;
		}
	</script>

</body>
</html>

