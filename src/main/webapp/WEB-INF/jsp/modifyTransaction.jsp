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

<title>Modify Transaction</title>

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
	<div class="container">

		<form class="form-horizontal" name="modifyTransactionForm" id="modifyTransactionForm" method="post">
				<fieldset>
		<legend>Modify Transaction Details</legend>
				<h2>${message}</h2>
				
				<table class="table table-striped" style="width: auto;">
					<tr>
						<td style="white-space: nowrap"><form:label for="amount"
								path="transaction.amount">Amount</form:label></td>
						<td><form:input path="transaction.amount" id="amount" name="amount"
								data-parsley-required="true" data-parsley-type="number"
								data-parsley-length="[1, 5]" step="0.01"
								data-parsley-length-message="Should be max 5 digits" /> <form:errors
								class="alert alert-danger" path="transaction.amount" /></td>
					</tr>
					<tr><td colspan="2"><input type="submit" id="update" class="btn btn-danger" value="Update">&nbsp; &nbsp;
					<input type="submit" id="cancel" class="btn btn-danger" value="Cancel"></td></tr>
				</table>

					
					<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
				
			</fieldset>
		</form>
	</div>
</center>
<!-- jQuery -->
	<script src="web_resources/theme/js/jquery.min.js"></script>
	<script src="web_resources/theme/js/parsley.min.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script src="web_resources/theme/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$('#update').click(function() {
			if (true == $('#modifyTransactionForm').parsley().isValid()) {
				$('#modifyTransactionForm').parsley().destroy();
				$('#modifyTransactionForm').attr("action", "modifyTransactionForm");
			} else {
				return false;
			}
		});
		$('#cancel').click(function() {
			$('#modifyTransactionForm').parsley().destroy();
			$('#modifyTransactionForm').attr("action", "goBack");
		});		

	</script>



</body>
</html>