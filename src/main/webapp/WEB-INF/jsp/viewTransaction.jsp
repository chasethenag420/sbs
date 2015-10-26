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

<title>User Profile</title>

<style type="text/css">
.wrapper {
	width: 500px;
	margin-left: auto;
	margin-right: auto
}

label {
	padding-left: 0 !important
}
</style>

</head>

<body>


	<div class="container">
		<c:if test="${empty transaction}">
			<h2>${message}</h2>
		</c:if>
		<form class="form-horizontal" name="viewTransaction" id="viewTransaction"
			method="post" onsubmit="return OnSubmitForm();">
			<fieldset>
				<legend>Transaction Details</legend>
				<br /> <br /> <br />
				<center>
					<c:if test="${!empty transaction}">
						<table class="table table-striped" style="width: auto;">
							<tr>
								<td><label for="userId">UserId </label></td>
								<td><input type="text" id="userId" disabled
									value="${transaction.userId}"></td>
							</tr>
							<tr>
								<td><label for="transactionId">Transaction Id </label></td>
								<td><input type="text" id="transactionId"
									value="${transaction.transactionId}" disabled></td>
							</tr>
							<tr>
								<td><label for="transactionStatus">Transaction Status </label></td>
								<td><input type="text" id="transactionStatus"
									value="${transaction.transactionStatus}" disabled></td>
							</tr>
							<tr>
								<td><label for="accountNumber">Account Number </label></td>
								<td><input type="text" id="accountNumber"
									value="${transaction.accountNumber}" disabled></td>
							</tr>
							<tr>
								<td><label for="creationTimestamp">Creation Timestamp </label></td>
								<td><input type="text" id="creationTimestamp" value="${transaction.creationTimestamp} "
									disabled></td>
							</tr>

							<tr>
								<td><label for="transactionType">Transaction Type</label></td>
								<td><input type="text" id="transactionType" value="${transaction.transactionType}"
									disabled></td>
							</tr>
							<tr>
								<td><label for="modifiedByUserid">Modified By Userid</label></td>
								<td><input type="text" id="modifiedByUserid"
									value="${transaction.modifiedByUserid}" disabled></td>
							</tr>
							<tr>
								<td><label for="modifiedTimestamp">Modified Timestamp</label></td>
								<td><input type="text" id="modifiedTimestamp" value="${transaction.modifiedTimestamp}"
									disabled></td>
							</tr>
							<tr>
								<td><label for="amount">Amount</label></td>
								<td><input type="text" id="amount" value="${transaction.amount}"
									disabled></td>
							</tr>
							<tr>
								<td><label for="transferId">TransferId</label></td>
								<td><input type="text" id="transferId" value="${transaction.transferId}"
									disabled></td>
							</tr>
							<tr>
								<td><label for="severity">Severity</label></td>
								<td><input type="text" id="severity" value="${transaction.severity}"
									disabled></td>
							</tr>
							<tr>
								<td><label for="transactionDescription">Transfer Description</label></td>
								<td><input type="text" id="transactionDescription" value="${transaction.transactionDescription}"
									disabled></td>
							</tr>
							<tr>

						</table>
						
					</c:if>
					<input
						class="btn" type="submit" onclick="document.pressed=this.value"
						value="Back" />
					<br>  <input type="hidden"
						name="${_csrf.parameterName}" value="${_csrf.token}" /> 
				</center>
			</fieldset>
		</form>
	</div>

	<script type="text/javascript">
		function OnSubmitForm() {
			if (document.pressed == 'Back') {
				document.viewTransaction.action = "goBack";
			}
			
			return true;
		}
	</script>


</body>
</html>