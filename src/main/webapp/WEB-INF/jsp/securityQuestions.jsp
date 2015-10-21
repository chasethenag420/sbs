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
		<br />
		<br />
		<div style="color: teal; font-size: 30px">Forget Password</div>
		<br />
		<br />
		<form:form name="securityQuestionsForm" id="securityQuestionsForm" method="post" modelAttribute="form" onsubmit="return OnSubmitForm();">
			<center>
				<div class="alert alert-danger"> ${errorMessage}</div>
			
				<table width="700px" height="150px" cellspacing="10">
					<tr>
						<td style="white-space: nowrap">Question 1: ${form.map.question1}</td>
					</tr>
					
					<tr>
						<td><form:input path="map['answer1']" /></td>
					</tr>
					<tr></tr>
					
					<tr>
						<td style="white-space: nowrap">Question 2: ${form.map.question2}</td>
					</tr>
					
					<tr>
						<td><form:input path="map['answer2']" /></td>
					</tr>
					<tr></tr>
					
					<tr>
						<td style="white-space: nowrap">Question 3: ${form.map.question3}</td>
					</tr>
					
					<tr>
						<td><form:input path="map['answer3']" /></td>
					</tr>
					<tr></tr>
					
					
					<tr>
						<form:input type="hidden" path="map['question1']" value="${form.map.question1}"/>
						<form:input type="hidden" path="map['question2']" value="${form.map.question2}"/>
						<form:input type="hidden" path="map['question3']" value="${form.map.question3}"/>
						<form:input type="hidden" path="map['username']" value="${form.map.username}"/>
						<form:input type="hidden" path="map['email']" value="${form.map.email}"/>
						<td><input type="submit"
							onclick="document.pressed=this.value" value="Submit" /><br/><br/><br/><br/>
						<input type="submit"
							onclick="document.pressed=this.value" value="Cancel" /> <td>
					</tr>
				</table>
				<div>
					<h2>${successfulMessage}</h2>
				</div>
			</center>
		</form:form>

	</center>

	<script type="text/javascript">
		function OnSubmitForm() {
			if (document.pressed == 'Submit') {
				document.securityQuestionsForm.action = "validateSecurityQuestions";
			} else if (document.pressed == 'Cancel') {
				document.securityQuestionsForm.action = "goBack";
			}
			return true;
		}
	</script>

</body>
</html>

