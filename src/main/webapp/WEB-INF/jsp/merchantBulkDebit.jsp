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
		<div style="color: teal; font-size: 30px">Multiple Debit</div>
		<br /> <br />

		<c:if test="${!empty errorMessage}">
			<div class="alert alert-danger">${errorMessage}</div>
		</c:if>
		<form:form name="bulkDebitForm" id="bulkDebitForm" method="post"
			modelAttribute="form">
			<table class="table table-striped" style="width: auto;">
				<tr></tr>
				<tr>
					<td style="white-space: nowrap"><form:label for="map['fromAccount']"
							path="map['fromAccount']">From Account Number</form:label></td>
					<td><form:select path="map['fromAccount']"
							data-parsley-required="true" name="map['fromAccount']">
							<form:option value="" label="--Select Account --" />
							<form:options items="${accounts}" />
						</form:select></td>
					<td><form:errors class="alert alert-danger" path="map['fromAccount']" /></td>
				</tr>



				<tr>
					<td style="white-space: nowrap"><form:label for="map['toAccount1']"
							path="map['toAccount1']">1. To Account Number</form:label></td>
					<td><form:input path="map['toAccount1']" /></td>
					<td><form:errors class="alert alert-danger" path="map['toAccount1']"
							data-parsley-required="true" name="map['toAccount1']" /></td>
				</tr>
				<tr>
					<td style="white-space: nowrap"><form:label for="map['amount1']"
							path="map['amount1']">Amount</form:label></td>
					<td><form:input path="map['amount1']" data-parsley-required="true"
							data-parsley-type="digits" data-parsley-length="[1, 5]"
							data-parsley-length-message="Should be max 5 digits"
							name="map['amount1']" /></td>
					<td><form:errors class="alert alert-danger" path="map['amount1']" /></td>
				</tr>
				<tr>
					<td style="white-space: nowrap"><form:label for="map['description1']"
							path="map['description1']">Transfer Description</form:label></td>
					<td><form:input path="map['description1']" name="map['description1']"
							data-parsley-required="true" data-parsley-pattern="[a-z A-Z]+"
							data-parsley-length="[1, 50]" /></td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				
				<tr>
					<td style="white-space: nowrap"><form:label for="map['toAccount2']"
							path="map['toAccount2']">2. To Account Number</form:label></td>
					<td><form:input path="map['toAccount2']" /></td>
					<td><form:errors class="alert alert-danger" path="map['toAccount2']"
							 name="map['toAccount2']" /></td>
				</tr>
				<tr>
					<td style="white-space: nowrap"><form:label for="map['amount2']"
							path="map['amount2']">Amount</form:label></td>
					<td><form:input path="map['amount2']" 
							data-parsley-type="digits" data-parsley-length="[1, 5]"
							data-parsley-length-message="Should be max 5 digits"
							name="map['amount2']" /></td>
					<td><form:errors class="alert alert-danger" path="map['amount2']" /></td>
				</tr>
				<tr>
					<td style="white-space: nowrap"><form:label for="map['description2']"
							path="map['description2']">Transfer Description</form:label></td>
					<td><form:input path="map['description2']" name="map['description2']"
							data-parsley-pattern="[a-z A-Z]+"
							data-parsley-length="[1, 50]" /></td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				
				<tr>
					<td style="white-space: nowrap"><form:label for="map['toAccount3']"
							path="map['toAccount3']">3. To Account Number</form:label></td>
					<td><form:input path="map['toAccount3']" /></td>
					<td><form:errors class="alert alert-danger" path="map['toAccount3']"
							 name="map['toAccount3']" /></td>
				</tr>
				<tr>
					<td style="white-space: nowrap"><form:label for="map['amount3']"
							path="map['amount3']">Amount</form:label></td>
					<td><form:input path="map['amount3']" 
							data-parsley-type="digits" data-parsley-length="[1, 5]"
							data-parsley-length-message="Should be max 5 digits"
							name="map['amount3']" /></td>
					<td><form:errors class="alert alert-danger" path="map['amount3']" /></td>
				</tr>
				<tr>
					<td style="white-space: nowrap"><form:label for="map['description3']"
							path="map['description3']">Transfer Description</form:label></td>
					<td><form:input path="map['description3']" name="map['description3']"
							data-parsley-pattern="[a-z A-Z]+"
							data-parsley-length="[1, 50]" /></td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				
				<tr>
					<td style="white-space: nowrap"><form:label for="map['toAccount4']"
							path="map['toAccount4']">4. To Account Number</form:label></td>
					<td><form:input path="map['toAccount4']" /></td>
					<td><form:errors class="alert alert-danger" path="map['toAccount4']"
							 name="map['toAccount4']" /></td>
				</tr>
				<tr>
					<td style="white-space: nowrap"><form:label for="map['amount4']"
							path="map['amount4']">Amount</form:label></td>
					<td><form:input path="map['amount4']" 
							data-parsley-type="digits" data-parsley-length="[1, 5]"
							data-parsley-length-message="Should be max 5 digits"
							name="map['amount4']" /></td>
					<td><form:errors class="alert alert-danger" path="map['amount4']" /></td>
				</tr>
				<tr>
					<td style="white-space: nowrap"><form:label for="map['description4']"
							path="map['description4']">Transfer Description</form:label></td>
					<td><form:input path="map['description4']" name="map['description4']"
							 data-parsley-pattern="[a-z A-Z]+"
							data-parsley-length="[1, 50]" /></td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				
				<tr>
					<td style="white-space: nowrap"><form:label for="map['toAccount5']"
							path="map['toAccount5']">5. To Account Number</form:label></td>
					<td><form:input path="map['toAccount5']" /></td>
					<td><form:errors class="alert alert-danger" path="map['toAccount5']"
							 name="map['toAccount5']" /></td>
				</tr>
				<tr>
					<td style="white-space: nowrap"><form:label for="map['amount5']"
							path="map['amount5']">Amount</form:label></td>
					<td><form:input path="map['amount5']" 
							data-parsley-type="digits" data-parsley-length="[1, 5]"
							data-parsley-length-message="Should be max 5 digits"
							name="map['amount5']" /></td>
					<td><form:errors class="alert alert-danger" path="map['amount5']" /></td>
				</tr>
				<tr>
					<td style="white-space: nowrap"><form:label for="map['description5']"
							path="map['description5']">Transfer Description</form:label></td>
					<td><form:input path="map['description5']" name="map['description5']"
							 data-parsley-pattern="[a-z A-Z]+"
							data-parsley-length="[1, 50]" /></td>
				</tr>
				<tr><td>&nbsp;</td></tr>
			</table>
			<%-- <form:input type="hidden" path="map['transactionType']" value="Transfer" /> --%>

			<input type="submit" class="btn" id="submit" value="Submit" />
			<input type="submit" class="btn" id="cancel" value="Cancel" />
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
			$('#bulkDebitForm').parsley().validate();
			if (true == $('#bulkDebitForm').parsley().isValid()) {
				$('#bulkDebitForm').parsley().destroy();
				$('#bulkDebitForm').attr("action", "bulkDebitAmount");
			} else {
				return false;
			}
		});

		$('#cancel').click(function() {
			$('#bulkDebitForm').parsley().destroy();
			$('#bulkDebitForm').attr("action", "goBack");

		});
	</script>
</body>
</html>

