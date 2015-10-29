<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="web_resources/theme/css/bootstrap.css" rel="stylesheet">
<link href="web_resources/theme/css/bootstrap-responsive.css"
	rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Secure Bank System | System Access Log</title>

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
		<br> <br>
		<div style="color: teal; font-size: 30px">Secure Bank System |
			System Access Log</div>
		<br>
		<form:form class="form-horizontal" id="systemAccessLog" method="post">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<c:if test="${!empty systemAccessList}">
				<table class="table table-striped" style="width: auto;">
					<tr>
						<td>ID</td>
						<td><b>User Name</b></td>
						<td><b>IP ADDRESS</b></td>
						<td><b>SESSSION ID</b></td>
						<td><b>ACTION</b></td>
						<td><b>TIME</b></td>
						<td><b>REQUEST URL</b></td>
					</tr>
					<c:forEach items="${systemAccessList}" var="systemAccess">
						<tr>
							<td><c:out value="${systemAccess.id}" /></td>
							<td><c:out value="${systemAccess.userName}" /></td>
							<td><c:out value="${systemAccess.ipAddress}" /></td>
							<td><c:out value="${systemAccess.sessionId}" /></td>
							<td><c:out value="${systemAccess.action}" /></td>
							<td><c:out value="${systemAccess.time}" /></td>
							<td><c:out value="${systemAccess.requestUrl}" /></td>

						</tr>
					</c:forEach>

				</table>
			</c:if>
			<br>
			<input type="submit" id="cancel" class="btn btn-danger" value="Back">
		</form:form>

	</center>

	<!-- jQuery -->
	<script src="web_resources/theme/js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="web_resources/theme/js/bootstrap.min.js"></script>

	<script type="text/javascript">
			$('#cancel').click(function() {
			$('#systemAccessLog').attr("action", "goBack");
		});
	</script>

</body>
</html>