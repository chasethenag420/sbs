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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Successful</title>

<style>
body {
	background-color: linen;
}

h1 {
	color: maroon;
	margin-left: 40px;
}

td {
	border: 1px dotted #999999;
}
</style>
</head>
<body>

	<center>
		<br /> <br />
		<div style="color: teal; font-size: 30px">Successful Action</div>
		<br /> <br />
		<h3>${successfulMessage}</h3>
		<form:form name="successful" id="successful" method="post"
			action="goBack">


			<br />
			<br />
			<br />
			<br />
			<input type="submit" value="Back" />


		</form:form>

	</center>





</body>
</html>