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
		<br>
		<div style="color: teal; font-size: 30px">Search Transactions</div>
		<br>

		<form class="form-horizontal" id="searchTransaction" method="post"
			action="">

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

			<table width="700px" height="150px" cellspacing="10">
				<tr>
					<td style="white-space: nowrap"><form:label
							path="user.firstName">First Name</form:label></td>
					<td><form:input path="user.firstName" /> <form:errors
							class="alert alert-danger" path="user.firstName" /></td>
				</tr>
				<tr>
					<td style="white-space: nowrap"><form:label
							path="account.accountNumber">Account Number</form:label></td>
					<td><form:input path="account.accountNumber" /> <form:errors
							class="alert alert-danger" path="account.accountNumber" /></td>
				</tr>
			</table>

			<input class="btn" type="submit" id="search" value="Submit" /> <input
				class="btn " type="submit" id="cancel" value="Cancel" />
		</form>
	</center>

	<!-- jQuery -->
	<script src="web_resources/theme/js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="web_resources/theme/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$('#search').click(function() {
			$('#searchTransaction').attr("action", "searchTransactionform");
		});
		$('#cancel').click(function() {
			$('#searchTransaction').attr("action", "goBack");
		});
	</script>

</body>
</html>
