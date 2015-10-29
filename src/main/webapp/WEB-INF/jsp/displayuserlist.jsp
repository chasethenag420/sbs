<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="web_resources/theme/css/bootstrap.css" rel="stylesheet">
<link href="web_resources/theme/css/bootstrap-responsive.css"
	rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Secure Bank System | User List</title>

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
		<label for="userrecord"><b>User List</b></label>
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
							<td><form:radiobutton path="map['userId']" name="userrecord" data-parsley-required="true"
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
			<sec:authorize access="hasAnyRole('REGULAR')">
			<input type="submit" id="requestUser" class="btn btn-success"
					value="Request User">
			</sec:authorize>
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
	
	<script src="web_resources/theme/js/parsley.min.js"></script>
	<script type="text/javascript">
		$('#viewUser').click(function() {
			$('#displayUserList').parsley().validate();
			if (true == $('#displayUserList').parsley().isValid()) {
				$('#displayUserList').parsley().destroy();
				$('#displayUserList').attr("action", "viewExternalprofileform");
			} else {
				return false;
			}
		});
		$('#requestUser').click(function() {
			$('#displayUserList').parsley().validate();
			if (true == $('#displayUserList').parsley().isValid()) {
				$('#displayUserList').parsley().destroy();
				$('#displayUserList').attr("action", "raiseInternalRequest");
			} else {
				return false;
			}
		});
		
		$('#modifyUser').click(function() {
			$('#displayUserList').parsley().validate();
			if (true == $('#displayUserList').parsley().isValid()) {
				$('#displayUserList').parsley().destroy();
				$('#displayUserList').attr("action", "modifyUserForm");
			} else {
				return false;
			}
		});
		$('#deleteUser').click(function() {
			$('#displayUserList').parsley().validate();
			if (true == $('#displayUserList').parsley().isValid()) {
				$('#displayUserList').parsley().destroy();
				$('#displayUserList').attr("action", "deleteUser");
			} else {
				return false;
			}
		});
		$('#cancel').click(function() {
			$('#displayUserList').parsley().destroy();
			$('#displayUserList').attr("action", "goBack");
		});
	</script>

</body>
</html>