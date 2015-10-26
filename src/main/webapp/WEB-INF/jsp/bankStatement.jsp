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
		<div style="color: teal; font-size: 30px">Bank Account Statement</div>
		<br /> <br />
		<form:form name="bankStatement" id="bankStatement" method="post"
			 modelAttribute="form"
			>
			<table class="table table-striped" style="width: auto;">
				<tr></tr>
				<tr>
					<td style="white-space: nowrap"><form:label for="toAccount"
							path="toAccount">Account Number</form:label></td>


					<td><form:select path="toAccount" name="toAccount"
							data-parsley-required="true">
							<form:option value="" label="--Select Account --" />
							<form:options items="${accounts}" />
						</form:select></td>
					<td><form:errors class="alert alert-danger" path="toAccount" /></td>


				</tr>
				<tr>
					<td style="white-space: nowrap"><form:label for="fromdate"
							path="fromDate">From Date</form:label></td>
					<td><form:input type="text" path="fromDate" name="fromdate"
							data-parsley-trigger="change" placeholder="MM/DD/YYYY"
							data-date-format="MM/DD/YYYY" data-date-minDate="01/01/1900"
							data-parsley-mindate="01/01/1900" data-parsley-required="true" /></td>
					<td><form:errors class="alert alert-danger" path="fromDate" /></td>
				</tr>
				<tr>
					<td style="white-space: nowrap"><form:label for="todate"
							path="toDate">To Date</form:label></td>
					<td><form:input type="text" path="toDate" name="todate"
							data-parsley-trigger="change" placeholder="MM/DD/YYYY"
							data-date-format="MM/DD/YYYY" data-date-minDate="01/01/1900"
							data-parsley-mindate="01/01/1900" data-parsley-required="true" /></td>
					<td><form:errors class="alert alert-danger" path="toDate" /></td>
				</tr>
			</table>
			<form:input type="hidden" path="transactionType"
				value="bankStatement" /> 
			<input type="submit" class="btn" id="createPDF"
				 value="Create PDF" />
			<input type="submit" class="btn" id="cancel"
				value="Cancel" />

		</form:form>

	</center>
	<script src="web_resources/theme/js/jquery.min.js"></script>
	<script src="web_resources/theme/js/jquery-ui.min.js"></script>
	<script src="web_resources/theme/js/bootstrap.min.js"></script>
	<script src="web_resources/theme/js/parsley.min.js"></script>
	<script type="text/javascript">
		$('#createPDF').click(function() {
			$('#bankStatement').parsley().validate();
			if (true == $('#bankStatement').parsley().isValid()) {
				$('#bankStatement').parsley().destroy();
				$('#bankStatement').attr("action", "downloadPDF");
			} else {
				return false;
			}
		});

		$('#cancel').click(function() {
			$('#bankStatement').parsley().destroy();
			$('#bankStatement').attr("action", "goBack");

		});
		window.Parsley.addValidator(
				'mindate',
				function(value, requirement) {
					// is valid date?
					var timestamp = Date.parse(value), minTs = Date
							.parse(requirement);

					return isNaN(timestamp) ? false : timestamp > minTs;
				}, 32).addMessage('en', 'mindate',
				'This date should be greater than %s');
	</script>

</body>
</html>

