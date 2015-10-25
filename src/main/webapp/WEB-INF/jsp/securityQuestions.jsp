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
		<form:form name="securityQuestionsForm" id="securityQuestionsForm"
			method="post" modelAttribute="form">
			<center>
				<c:if test="${!empty errorMessage}">
					<div class="alert alert-danger">${errorMessage}</div>
				</c:if>

				<table class="table table-striped" style="width: auto;">
					<tr>
						<td style="white-space: nowrap"><label for="question1">Question
								1: ${form.map.question1}</label></td>
						<td><form:input path="map['answer1']" name="question1"
								data-parsley-required="true" data-parsley-pattern="[a-z A-Z]+"
								data-parsley-length="[1, 20]" /></td>
					</tr>
					<tr></tr>

					<tr>
						<td style="white-space: nowrap"><label for="question2">Question
								2: ${form.map.question2}</label></td>

						<td><form:input path="map['answer2']" name="question2"
								data-parsley-required="true" data-parsley-pattern="[a-z A-Z]+"
								data-parsley-length="[1, 20]" /></td>
					</tr>
					<tr></tr>

					<tr>
						<td style="white-space: nowrap"><label for="question3">Question
								3: ${form.map.question3}</label></td>
						<td><form:input path="map['answer3']" name="question3"
								data-parsley-required="true" data-parsley-pattern="[a-z A-Z]+"
								data-parsley-length="[1, 20]" /></td>
					</tr>
					<tr></tr>
				</table>
				<form:input type="hidden" path="map['question1']"
					value="${form.map.question1}" />
				<form:input type="hidden" path="map['question2']"
					value="${form.map.question2}" />
				<form:input type="hidden" path="map['question3']"
					value="${form.map.question3}" />
				<form:input type="hidden" path="map['username']"
					value="${form.map.username}" />
				<form:input type="hidden" path="map['email']"
					value="${form.map.email}" />
				<input type="submit" class="btn" id="submit" value="Submit" /> <input
					type="submit" class="btn" id="cancel" value="Cancel" />
				<div>
					<h2>${successfulMessage}</h2>
				</div>
			</center>
		</form:form>

	</center>
	<script src="web_resources/theme/js/jquery.min.js"></script>
	<script src="web_resources/theme/js/parsley.min.js"></script>

	<script type="text/javascript">
		$('#submit')
				.click(
						function() {
							$('#securityQuestionsForm').parsley().validate();
							if (true == $('#securityQuestionsForm').parsley()
									.isValid()) {
								$('#securityQuestionsForm').parsley().destroy();
								$('#securityQuestionsForm').attr("action",
										"validateSecurityQuestions");
							} else {
								return false;
							}
						});

		$('#cancel').click(function() {
			$('#securityQuestionsForm').parsley().destroy();
			$('#securityQuestionsForm').attr("action", "goBack");

		});
	</script>
</body>
</html>

