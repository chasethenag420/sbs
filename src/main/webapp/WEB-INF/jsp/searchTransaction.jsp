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
<body>
	<center>
		<br>
		<div style="color: teal; font-size: 30px">Search Transactions</div>
		<br>
		
		<h2>${message}</h2>

		<form class="form-horizontal" name="searchTransaction"
			id="searchTransaction" method="post">

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

			<table class="table table-striped" style="width: auto;">
				<tr>
					<td style="white-space: nowrap"><form:label
							path="account.accountNumber" for="toAccount">Account Number</form:label></td>
					<td><form:input path="account.accountNumber" name="toAccount"
							data-parsley-length="[1,10]" data-parsley-required="true"
							data-parsley-type="digits" /> <form:errors
							class="alert alert-danger" path="account.accountNumber" /></td>
				</tr>
			</table>

			<input class="btn" type="submit" id="search" value="Submit" /> <input
				class="btn " type="submit" id="cancel" value="Cancel" />
		</form>
	</center>

	<!-- jQuery -->
	<script src="web_resources/theme/js/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="web_resources/theme/js/bootstrap.min.js"></script>
	<script src="web_resources/theme/js/parsley.min.js"></script>
	<script type="text/javascript">
		$('#search').click(
				function() {
					$('#searchTransaction').parsley().validate();
					if (true == $('#searchTransaction').parsley().isValid()) {
						$('#searchTransaction').parsley().destroy();
						$('#searchTransaction').attr("action",
								"searchTransactionform");
					} else {
						return false;
					}
				});

		$('#cancel').click(function() {
			$('#searchTransaction').parsley().destroy();
			$('#searchTransaction').attr("action", "goBack");

		});
	</script>

</body>
</html>
