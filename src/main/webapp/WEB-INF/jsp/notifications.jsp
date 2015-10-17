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
		<div style="color: teal; font-size: 30px">Secure Bank System |
			Notifications</div>

		<br> <br> <br>
		<form:form class="form-horizontal" id="notificationsForm" method="post" action="approvenotification" modelAttribute="form">
			<c:if test="${!empty notificationRows}">

				<table border="1" bgcolor="black" width="auto">
					<tr
						style="background-color: teal; color: white; text-align: center;"
						height="40px">
						<td><b>  </b>
						<td><b>Request Type</b></td>
						<td><b>Request Description</b></td>
						<td><b>Requested Date</b></td>
						<td><b>Requester UserId</b></td>
						<td><b>Request Status</b></td>
						<td><b>Approver UserId</b></td>
						<td><b>Approve</b></td>
						<td><b>Reject</b></td>
						<td><b>Forward</b></td>
					</tr>
					<c:forEach items="${notificationRows}" var="eachnotification">
						<tr
							style="background-color: white; color: black; text-align: center;"
							height="30px">
							<td><form:radiobutton path="map['authorizationId']" value="${eachnotification.authorizationId}" /></td>
							<td><c:out value="${eachnotification.requestType}" /></td>
							<td><c:out value="${eachnotification.requestDescription}" /></td>
							<td><c:out
									value="${eachnotification.requestCreationTimeStamp}" /></td>
							<td><c:out value="${eachnotification.authorizedByUserId}" /></td>
							<td><c:out value="${eachnotification.requestStatus}" /></td>
							<td><c:out value="${eachnotification.authorizedToUserId}" /></td>

							<td><input type="submit" value=Approve class="btn btn-success"/td>
							<td><input type="submit" value=Reject class="btn btn-danger"/td>
							<td><input type="submit" value=Forward class="btn btn-info"/td>
						</tr>
					</c:forEach>

				</table>
			</c:if>

		</form:form>



	</center>

</body>
</html>