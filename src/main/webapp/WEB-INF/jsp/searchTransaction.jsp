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
<head>
<script>
document.getElementById("deletetransaction").disabled=true;
</script>
</head>
<body>
	<center>


		<form class="form-horizontal" id="searchTransaction" method="post"
			action="searchTransaction">

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
				
				
			<div class="control-group">
				<form:label class="control-label" type ="date" path="user.firstName">First Name</form:label>
				<form:input class="controls" path="user.firstName" />
				<form:errors class="alert alert-danger" path="user.firstName" />
			</div>
			<div class="control-group">
				<form:label class="control-label" type ="date" path="account.accountNumber">Account Number</form:label>
				<form:input class="controls" path="account.accountNumber" />
				<form:errors class="alert alert-danger" path="account.accountNumber" />
			</div>

			

			<div class="control-group">
				<div class="controls">
					<input class="btn  btn-primary " type="submit" value="Submit" /> <a
						class="btn  btn-primary " href="sample" type="button">Cancel</a>
				</div>

				<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
				<center>
				<table border="1" cellspacing="40" width="100">
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
						<td><button type="button" id="deletetransaction" disabled >Delete</button> </td>
						</tr>
					</c:forEach>
				</table>
				</center>
			</div>


		</form>
	</center>
</body>
</html>
