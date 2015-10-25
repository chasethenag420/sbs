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
		<div style="color: teal; font-size: 30px">Credit Amount</div>
		<br /> <br />
		<form:form name="creditForm" id="creditForm" method="post"
			modelAttribute="form">
			<center>
				<c:if test="${!empty errorMessage}">
					<div class="alert alert-danger">${errorMessage}</div>
				</c:if>
				<table class="table table-striped" style="width: auto;">
					<tr></tr>
					<tr>
						<td style="white-space: nowrap"><form:label path="toAccount"
								for="toAccount">To Account Number</form:label></td>


						<td><form:select path="toAccount"
								data-parsley-required="true" name="toAccount">
								<form:option value="" label="--Select Account --" />
								<form:options items="${accounts}" />
							</form:select></td>
						<td><form:errors class="alert alert-danger" path="toAccount" /></td>


					</tr>
					<tr>
						<td style="white-space: nowrap"><form:label path="amount"
								for="amount">Amount</form:label></td>
						<td><form:input path="amount" data-parsley-required="true"
								data-parsley-type="digits" data-parsley-length="[1, 5]"
								data-parsley-length-message="Should be max 5 digits"
								name="amount" /></td>
						<td><form:errors class="alert alert-danger" path="amount" /></td>
					</tr>
					<tr>
						<td style="white-space: nowrap"><form:label
								path="description" for="description">Transfer Description</form:label></td>
						<td><form:input path="description" name="description"
								data-parsley-required="true" data-parsley-pattern="[a-z A-Z]+"
								data-parsley-length="[1, 50]" /></td>
					</tr>
				</table>
				<form:input type="hidden" path="transactionType" value="credit" />
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
			$('#creditForm').parsley().validate();
			if (true == $('#creditForm').parsley().isValid()) {
				$('#creditForm').attr("action", "creditAmount");
			} else {
				return false;
			}
		});

		$('#cancel').click(function() {
			$('#creditForm').parsley().destroy();
			$('#creditForm').attr("action", "goBack");

		});
	</script>

</body>
</html>

