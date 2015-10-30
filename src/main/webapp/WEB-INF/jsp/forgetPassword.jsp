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
		<div style="color: teal; font-size: 30px">Forget Password</div>
		<br /> <br />
		<form:form name="forgetPasswordForm" id="forgetPasswordForm"
			method="post" modelAttribute="form">
			<center>
				<c:if test="${!empty errorMessage}">
					<div class="alert alert-danger">${errorMessage}</div>
				</c:if>

				<table class="table table-striped" style="width: auto;">
					<tr></tr>

					<tr>
						<td style="white-space: nowrap"><form:label for="username"
								path="map['username']">Username</form:label></td>
						<td><form:input path="map['username']" name="username"
								data-parsley-required="true" data-parsley-type="alphanum"
								data-parsley-length="[6, 15]"
								data-parsley-length-message="Username should be between 6 to 15 characters" /></td>
					</tr>
					<tr>
						<td style="white-space: nowrap"><form:label for="email"
								path="map['email']">Registered Email</form:label></td>
						<td><form:input type="email" path="map['email']" name="email"
								data-parsley-required="true" data-parsley-type="email" id="email"
								data-parsley-length="[1, 25]" /></td>
					</tr>
					<tr>

						<td colspan="2"><input class="btn" type="submit" id="submit" 
							value="Submit" /> &nbsp; &nbsp; 
						<a href="login">Cancel</a>
							
							</td>
					</tr>
				</table>
				<div>
					<h2>${successfulMessage}</h2>
				</div>
			</center>
		</form:form>

	</center>
	<script src="web_resources/theme/js/jquery.min.js"></script>
	<script src="web_resources/theme/js/parsley.min.js"></script>
	<script type="text/javascript">
		$('#submit').click(function() {
			$('#forgetPasswordForm').parsley().validate();
			if (true == $('#forgetPasswordForm').parsley().isValid()) {
				$('#forgetPasswordForm').parsley().destroy();
				$('#forgetPasswordForm').attr("action", "setForgetPassword");
			} else {
				return false;
			}
		});

	<!--	$('#cancel').click(function() {
			$('#forgetPasswordForm').parsley().destroy();
			$('#forgetPasswordForm').attr("action", "login");

		});-->
	</script>

</body>
</html>

