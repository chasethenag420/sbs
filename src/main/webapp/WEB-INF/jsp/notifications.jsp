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
<title>Secure Bank System | Notification</title>

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
		<div style="color: teal; font-size: 30px">Secure Bank System |
			Notifications</div>

		<br> <br> <br>

		<h3>Notifications: Pending Approval</h3>
				<br> <br>
				<form:form class="form-horizontal" id="notificationsForm"
					method="post" modelAttribute="form">
					<c:if test="${!empty notificationRows}">
						<label for="notification"><b>Notifications</b></label>
						<p>
						<table class="table table-striped" style="width: auto;">
							<tr>
								<td><b>&nbsp;</b></td>
								<td><b>Request Type</b></td>
								<td><b>Request Description</b></td>
								<td><b>Requested Date</b></td>
								<td><b>Requester UserId</b></td>
								<td><b>Request Status</b></td>
								<td><b>Approver UserId</b></td>
							</tr>
							<c:forEach items="${notificationRows}" var="eachnotification">
								<tr>
									<td><form:radiobutton path="map['authorizationId']"
											data-parsley-required="true" name="notification"
											value="${eachnotification.authorizationId}" /></td>
									<td><c:out value="${eachnotification.requestType}" /></td>
									<td><c:out value="${eachnotification.requestDescription}" /></td>
									<td><c:out
											value="${eachnotification.requestCreationTimeStamp}" /></td>
									<td><c:out value="${eachnotification.authorizedToUserId}" /></td>
									<td><c:out value="${eachnotification.requestStatus}" /></td>
									<td><c:out value="${eachnotification.authorizedByUserId}" /></td>
								</tr>
							</c:forEach>

						</table>
						</p>
						<input id="approve" type="submit" class="btn btn-success"
							value="Approve" />
						<input id="reject" type="submit" class="btn btn-danger"
							value="Reject" />
					</c:if>
					<c:if test="${empty notificationRows}">
						<br>
						<div>You do not have any Notifications to approve.</div>
						<br>
					</c:if>
					<input class="btn" type="submit" id="cancel" value="Cancel" />
				</form:form>

				<br />
				<br />
				<br />
				<h3>Notifications: Approved or Created by User</h3>
				<table class="table table-striped" style="width: auto;">
					<tr>
						<td><b>Request Type</b></td>
						<td><b>Request Description</b></td>
						<td><b>Requested Date</b></td>
						<td><b>Requester UserId</b></td>
						<td><b>Request Status</b></td>
						<td><b>Approver UserId</b></td>
					</tr>
					<c:forEach items="${approvedNotificationRows}" var="eachnotification">
						<tr>
							
							<td><c:out value="${eachnotification.requestType}" /></td>
							<td><c:out value="${eachnotification.requestDescription}" /></td>
							<td><c:out
									value="${eachnotification.requestCreationTimeStamp}" /></td>
							<td><c:out value="${eachnotification.authorizedToUserId}" /></td>
							<td><c:out value="${eachnotification.requestStatus}" /></td>
							<td><c:out value="${eachnotification.authorizedByUserId}" /></td>


						</tr>
					</c:forEach>

				</table>
	</center>

	<!-- jQuery -->
	<script src="web_resources/theme/js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="web_resources/theme/js/bootstrap.min.js"></script>
	<script src="web_resources/theme/js/parsley.min.js"></script>

	<script type="text/javascript">
		$('#approve').click(function() {
			if (true == $('#notificationsForm').parsley().isValid()) {
				$('#notificationsForm').parsley().destroy();
				$('#notificationsForm').attr("action", "approvenotification");
			} else {
				return false;
			}

		});
		$('#reject').click(function() {
			if (true == $('#notificationsForm').parsley().isValid()) {
				$('#notificationsForm').parsley().destroy();
				$('#notificationsForm').attr("action", "rejectnotification");
			} else {
				return false;
			}
		});
		$('#cancel').click(function() {
			$('#notificationsForm').parsley().destroy();
			$('#notificationsForm').attr("action", "goBack");
		});
	</script>

</body>
</html>