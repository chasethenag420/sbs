<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

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
		<div style="color: teal; font-size: 30px">Account Details</div>

		<br> <br> <br>
		
			<c:if test="${!empty accountsRows}">

				<table border="1" bgcolor="black" >
				<col width="230">
				<col width="230">
				<col width="230">
					<tr
						style="background-color: teal; color: white; text-align: center;"
						height="40px">
						
						<td><b>Account Number</b></td>
						<td><b>Account Type</b></td>
						<td><b>Balance</b></td>
						
					</tr>
					<c:forEach items="${accountsRows}" var="eachnotification">
						<tr
							style="background-color: white; color: black; text-align: center;"
							height="30px">
							
							<td><c:out value="${eachnotification.accountNumber}" /></td>
							<td><c:out value="${eachnotification.accountType}" /></td>
							<td><c:out value="${eachnotification.balance}" /></td>
							
						</tr>
					</c:forEach>

				</table>
			</c:if>

	



	</center>
	

</body>
</html>