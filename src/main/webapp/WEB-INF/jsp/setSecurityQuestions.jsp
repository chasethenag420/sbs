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
<title>Security Questions</title>
</head>
<body>
	<center>
		<br /> <br />
		<div style="color: teal; font-size: 30px">Set Security Questions</div>
		<br /> <br />
		<form:form name="setSecurityQuestionsForm"
			id="setSecurityQuestionsForm" method="post" modelAttribute="form"
			onsubmit="return OnSubmitForm();">
			<center>
				<c:if test="${!empty errorMessage}">
					<div class="alert alert-danger">${errorMessage}</div>
				</c:if>

				<table width="700px" height="150px" cellspacing="10">
					<tr>
						<td>Question 1: <form:select path="map['question1']">
								<form:option value="NONE" label="--Select Question --" />
								<form:options items="${form.mapObject.question1}" />
							</form:select></td>
						<td><form:input path="map['answer1']" /></td>
					</tr>
					<tr>

						<td>Question 2: <form:select path="map['question2']">
								<form:option value="NONE" label="--Select Question --" />
								<form:options items="${form.mapObject.question2}" />
							</form:select></td>
						<td><form:input path="map['answer2']" /></td>
					</tr>

					<tr>

						<td>Question 3: <form:select path="map['question3']">
								<form:option value="NONE" label="--Select Question --" />
								<form:options items="${form.mapObject.question3}" />
							</form:select></td>
						<td><form:input path="map['answer3']" /></td>
					</tr>
					<tr></tr>



				</table>
				<br> <input type="submit" class="btn"
					onclick="document.pressed=this.value" value="Submit" /> <input
					type="submit" class="btn" onclick="document.pressed=this.value"
					value="Cancel" />
				<div>
					<h2>${successfulMessage}</h2>
				</div>
			</center>
		</form:form>

	</center>

	<script type="text/javascript">
		function OnSubmitForm() {
			if (document.pressed == 'Submit') {
				document.setSecurityQuestionsForm.action = "addSecurityQuestions";
			} else if (document.pressed == 'Cancel') {
				document.setSecurityQuestionsForm.action = "goBack";
			}
			return true;
		}
	</script>

</body>
</html>

