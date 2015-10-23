<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="web_resources/theme/css/bootstrap.css" rel="stylesheet">
<link href="web_resources/theme/css/bootstrap-responsive.css"
	rel="stylesheet">

<title>Search Transactions</title>
<body>
	<center>
		<br /> <br />
		<div style="color: teal; font-size: 30px">Search Transactions</div>
		<form:form name="externalsearchtrans" id="externalsearchtrans"
			modelAttribute="form" method="post" onsubmit="return OnSubmitForm();">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<br />
			<br />

			<div class="control-group">
				<form:label class="control-label" path="accountNumber">Account Number</form:label>
				<form:input class="controls" path="accountNumber" />
				<form:errors class="alert alert-danger" path="accountNumber" />
			</div>

			<div class="control-group">
				<form:label class="control-label" path="toDate">To Date</form:label>
				<form:input class="controls" path="toDate" />
				<form:errors class="alert alert-danger" path="toDate" />
			</div>
			<div class="control-group">
				<form:label class="control-label" path="fromDate">From Date</form:label>
				<form:input class="controls" path="fromDate" />
				<form:errors class="alert alert-danger" path="fromDate" />
			</div>
			<div class="control-group">
				
					<input class="btn" type="submit" onclick="document.pressed=this.value"
					value="Submit" />
					<input class="btn" type="submit" onclick="document.pressed=this.value"
					value="Cancel" />
			</div>



			<table border="1"  bgcolor="black">
				<col width="230">
				<col width="230">
				<col width="230">
				<col width="230">
				<tr>
					<th>AccountNumber</th>
					<th>AccountCreation</th>
					<th>TransactionType</th>
					<th>Amount</th>
				</tr>
				<c:forEach items="${transactions}" var="item">
					<tr>


						<td width="30"><c:out value="${item.accountNumber}" /></td>

						<td width="80"><c:out value="${item.creationTimestamp}" /></td>

						<td width="30"><c:out value="${item.transactionType}" /></td>

						<td><c:out value="${item.amount}" /></td>
				</c:forEach>
			</table>

		</form:form>

	</center>
	<script type="text/javascript">
		function OnSubmitForm() {
			if (document.pressed == 'Submit') {
				document.externalsearchtrans.action = "externalsearchtransform";
			} else if (document.pressed == 'Cancel') {
				document.externalsearchtrans.action = "goBack";
			}
			return true;
		}
	</script>
</body>
</html>