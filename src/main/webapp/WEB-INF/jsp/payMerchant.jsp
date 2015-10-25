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
<title><spring:message code="title.transfer"></spring:message></title>
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
		<div style="color: teal; font-size: 30px">Submit Merchant Payment</div>
		<br /> <br />
		<form:form name="payMerchantForm" id="payMerchantForm" method="post"
			modelAttribute="form">
			<c:if test="${!empty errorMessage}">
				<div class="alert alert-danger">${errorMessage}</div>
			</c:if>
			<table class="table table-striped" style="width: auto;">
				<tr>
					<td style="white-space: nowrap"><form:label for="fromAccount" path="fromAccount">From Account Number</form:label>
					</td>
					<td><form:select path="fromAccount" name="fromAccount" data-parsley-required="true">
							<form:option value="" label="--Select Account --" />
							<form:options items="${accounts}" />
						</form:select></td>
					<td><form:errors class="alert alert-danger" path="fromAccount" /></td>
				</tr>
				<tr>
					<td style="white-space: nowrap"><form:label for="toAccount" path="toAccount">To Account Number</form:label>
					</td>
					<td><form:input path="toAccount" data-parsley-required="true" data-parsley-type="digits"/></td>
					<td><form:errors class="alert alert-danger" path="toAccount" /></td>
				</tr>
				<tr>
					<td style="white-space: nowrap"><form:label for="amount" path="amount">Amount</form:label>
					</td>
					<td><form:input path="amount" name="amount" data-parsley-required="true" data-parsley-type="digits"/></td>
					<td><form:errors class="alert alert-danger" path="amount" /></td>
				</tr>
				<tr>
					<td style="white-space: nowrap"><form:label for="description" path="description">Transfer Description</form:label>
					</td>
					<td><form:input path="description" data-parsley-required="true" data-parsley-pattern="[a-z A-Z]+"
								data-parsley-length="[1, 50]"/></td>
				</tr>
			</table>
			<form:input type="hidden" path="transactionType" value="payMerchant" />
			<input type="submit" class="btn"
				id="submit" value="Submit" />
			<input class="btn" type="submit"
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
			$('#payMerchantForm').parsley().validate();
			if (true == $('#payMerchantForm').parsley().isValid()) {
				$('#payMerchantForm').attr("action", "submitMerchantPayment");
			} else {
				return false;
			}
		});

		$('#cancel').click(function() {
			$('#payMerchantForm').parsley().destroy();
			$('#payMerchantForm').attr("action", "goBack");

		});
	</script>

</body>
</html>

