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
<title><spring:message code="title.credit"></spring:message></title>
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
		<div style="color: teal; font-size: 30px">Demand PII Information</div>
		<br /> <br />
		<form:form name="piiForm" id="piiForm" method="post"
			modelAttribute="form">
			<center>
				<c:if test="${!empty errorMessage}">
					<div class="alert alert-danger">${errorMessage}</div>
				</c:if>
				<table class="table table-striped" style="width: auto;">
					<tr></tr>
					<tr>
					
					<td style="white-space: nowrap"><form:label for="ssn"
								path="map['ssn']">SSN</form:label></td>
						<td><form:input type="number" name="ssn" path="map['ssn']"
								id="ssn" data-parsley-required="true" data-parsley-type="digits"
								data-parsley-length="[9, 9]"
								data-parsley-length-message="Should be 9 digits" /> <form:errors
								class="alert alert-danger" path="map['ssn']" /></td>
				</table>
				<input class="btn" type="submit" id="submit" value="Submit" /> <input
					class="btn" type="submit" id="cancel" value="Cancel" />
				<div>
					<h2>${successfulMessage}</h2>
				</div>
			</center>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form:form>

	</center>
	<script src="web_resources/theme/js/jquery.min.js"></script>
	<script src="web_resources/theme/js/parsley.min.js"></script>

	<script type="text/javascript">
		$('#submit').click(function() {
			$('#piiForm').parsley().validate();
			if (true == $('#piiForm').parsley().isValid()) {
				$('#piiForm').parsley().destroy();
				$('#piiForm').attr("action", "createPIIInformationRequest");
			} else {
				return false;
			}
		});

		$('#cancel').click(function() {
			$('#piiForm').parsley().destroy();
			$('#piiForm').attr("action", "goBack");

		});
	</script>

</body>
</html>

