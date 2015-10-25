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
<title>Secure Bank System | User List</title>

<style>
table tr td:empty {
	width: 50px;
}

table tr td {
	padding-top: 10px;
	padding-bottom: 10px;
}
</style>

</head>
<body>
	<center>
	<br>
	<br>
		<div style="color: teal; font-size: 30px">Secure Bank System |
			User List</div>
<br>
		<h2>${errorMessage}</h2>
		<form:form class="form-horizontal" id="displayUserList" method="post"
			modelAttribute="form">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<c:if test="${!empty userList}">

				<table class="table table-striped" style="width: auto;">
					<tr>
						<td><b></b></td>
						<td><b>User Name</b></td>
						<td><b>First Name</b></td>
						<td><b>Last Name</b></td>
						<td><b>Phone Number</b></td>
						<td><b>Email Id</b></td>
					</tr>
					<c:forEach items="${userList}" var="user">
						<tr>
							<td><form:radiobutton path="map['userId']"
									value="${user.userId}" /></td>
							<td><c:out value="${user.userName}" /></td>
							<td><c:out value="${user.firstName}" /></td>
							<td><c:out value="${user.lastName}" /></td>
							<td><c:out value="${user.phoneNumber}" /></td>
							<td><c:out value="${user.emailId}" /></td>

						</tr>
					</c:forEach>

				</table>
			</c:if>
			<br>
			<c:if test="${!empty userList}">
			<input type="submit" id="requestUser" class="btn btn-success"
					value="Request User">
			<input type="submit" id="viewUser" class="btn btn-success"
					value="View User">
				<input type="submit" id="modifyUser" class="btn btn-success"
					value="Modify User">
				<input type="submit" id="deleteUser" class="btn btn-danger"
					value="Delete User">
			</c:if>

			<input type="submit" id="cancel" class="btn btn-danger"
				value="Cancel">
		</form:form>

	</center>

	<!-- jQuery -->
	<script src="web_resources/theme/js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="web_resources/theme/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$('#viewUser').click(function() {
			$('#displayUserList').attr("action", "viewExternalprofileform");
		});
		$('#requestUser').click(function() {
			$('#displayUserList').attr("action", "raiseInternalRequest");
		});
		
		$('#modifyUser').click(function() {
			$('#displayUserList').attr("action", "modifyUserForm");
		});
		$('#deleteUser').click(function() {
			$('#displayUserList').attr("action", "deleteUser");
		});
		$('#cancel').click(function() {
			$('#displayUserList').attr("action", "goBack");
		});
	</script>

</body>
</html>