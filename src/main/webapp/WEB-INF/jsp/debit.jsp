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
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="web_resources/theme/css/bootstrap.css" rel="stylesheet">
<link href="web_resources/theme/css/bootstrap-responsive.css"
	rel="stylesheet">
<title><spring:message code="title.debit"></spring:message></title>
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
		<div style="color: teal; font-size: 30px">Debit Amount</div>
		<br /> <br />

		<c:if test="${!empty errorMessage}">
			<div class="alert alert-danger">${errorMessage}</div>
		</c:if>
		<form:form name="debitForm" id="debitForm" method="post"
			modelAttribute="form" >
			<table class="table table-striped" style="width: auto;">
				<tr></tr>
				<tr>
					<td style="white-space: nowrap"><form:label for="fromAccount" path="fromAccount">From Account Number</form:label>
					</td>
					<td><form:select path="fromAccount" data-parsley-required="true" name="fromAccount">
							<form:option value="" label="--Select Account --" />
							<form:options items="${accounts}" />
						</form:select></td>
					<td><form:errors class="alert alert-danger" path="fromAccount" /></td>
				</tr>
				<tr>
					<td style="white-space: nowrap"><form:label  for="amount" path="amount">Amount</form:label>
					</td>
					<td><form:input path="amount" data-parsley-required="true" data-parsley-type="digits"
								data-parsley-length="[1, 5]"
								data-parsley-length-message="Should be max 5 digits" 
								name="amount" /></td>
					<td><form:errors class="alert alert-danger" path="amount" /></td>
				</tr>
				<tr>
					<td style="white-space: nowrap"><form:label for="description" path="description">Transfer Description</form:label>
					</td>
					<td><form:input path="description" data-parsley-required="true" data-parsley-pattern="[a-z A-Z]+" data-parsley-length="[1, 50]"/></td>
				</tr>

			</table>
			<form:input type="hidden" path="transactionType" value="debit" />

			<input type="submit" class="btn"
				id="submit" value="Submit" />
			<input type="submit" class="btn"
				id="cancel" value="Cancel" />
			<div>
				<h2>${successfulMessage}</h2>
			</div>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form:form>
	</center>
<script src="web_resources/theme/js/jquery.min.js"></script>
	<script src="web_resources/theme/js/parsley.min.js"></script>
	<script type="text/javascript">
		
		$('#submit').click(function() {
			$('#debitForm').parsley().validate();
			if (true == $('#debitForm').parsley().isValid()) {
				$('#debitForm').parsley().destroy();
				$('#debitForm').attr("action", "debitAmount");
			} else {
				return false;
			}
		});

		$('#cancel').click(function() {
			$('#debitForm').parsley().destroy();
			$('#debitForm').attr("action", "goBack");

		});
	</script>
</body>
</html>

