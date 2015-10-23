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
</head>
<body>
	<center>
		<br /> <br />
		<div style="color: teal; font-size: 30px">Credit Amount</div>
		<br /> <br />
		<form:form name="creditForm" id="creditForm" method="post"
			modelAttribute="form" onsubmit="return OnSubmitForm();">
			<center>
				<table width="700px" height="150px" cellspacing="10">
					<tr></tr>
					<tr>
						<td style="white-space: nowrap"><form:label path="toAccount">To Account Number</form:label>
						</td>


						<td><form:select path="toAccount">
								<form:option value="NONE" label="--Select Account --" />
								<form:options items="${accounts}" />
							</form:select></td>
						<td><form:errors class="alert alert-danger" path="toAccount" /></td>


					</tr>
					<tr>
						<td style="white-space: nowrap"><form:label path="amount">Amount</form:label>
						</td>
						<td><form:input path="amount" /></td>
						<td><form:errors class="alert alert-danger" path="amount" /></td>
					</tr>
					<tr>
						<td style="white-space: nowrap"><form:label
								path="description">Transfer Description</form:label></td>
						<td><form:input path="description" /></td>
					</tr>
				</table>
				<form:input type="hidden" path="transactionType" value="credit" />
				<input class="btn" type="submit" onclick="document.pressed=this.value"
					value="Submit" />
					 <input class="btn" type="submit"
					onclick="document.pressed=this.value" value="Cancel" />
				<div>
					<h2>${successfulMessage}</h2>
				</div>
			</center>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form:form>

	</center>

	<script type="text/javascript">
		function OnSubmitForm() {
			if (document.pressed == 'Submit') {
				document.creditForm.action = "creditAmount";
			} else if (document.pressed == 'Cancel') {
				document.creditForm.action = "goBack";
			}
			return true;
		}
	</script>

</body>
</html>

