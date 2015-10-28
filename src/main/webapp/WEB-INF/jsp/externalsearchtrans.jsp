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
		<br /> <br />
		<div style="color: teal; font-size: 15px">
			Search Transactions
			<form:form name="externalsearchtrans" id="externalsearchtrans"
				modelAttribute="form" method="post">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
				<br />
				<br />
				<table class="table table-striped" style="width: auto;">
					<tr>
						<td style="white-space: nowrap"><form:label
								path="accountNumber" for="Account">To Account Number</form:label></td>


						<td><form:select path="accountNumber"
								data-parsley-required="true" name="Account">
								<form:option value="" label="--Select Account --" />
								<form:options items="${accounts}" />
							</form:select></td>

						<td><form:errors class="alert alert-danger"
								path="accountNumber" /></td>


					</tr>

					<!-- <tr>
					<td><form:label path="accountNumber">Account Number</form:label></td>
					<td><form:input path="accountNumber"
							data-parsley-required="true" data-parsley-type="digits"
							data-parsley-length="[1, 10]"
							data-parsley-length-message="Should be between 1 to 10 digits" />
						<form:errors class="alert alert-danger" path="accountNumber" /></td>
				</tr>
				-->

					<tr>
						<td><form:label path="fromDate" for="fromdate">From Date</form:label></td>
						<td><form:input path="fromDate" name="fromdate"
								data-parsley-trigger="change" placeholder="MM/DD/YYYY"
								data-date-format="MM/DD/YYYY" data-date-minDate="01/01/1900"
								data-parsley-mindate="01/01/1900" data-parsley-required="true" />
							<form:errors class="alert alert-danger" path="fromDate" /></td>
					</tr>
					<tr>
						<td><form:label path="toDate" for="todate">To Date</form:label></td>
						<td><form:input path="toDate" name="todate"
								data-parsley-trigger="change" placeholder="MM/DD/YYYY"
								data-date-format="MM/DD/YYYY" data-date-minDate="01/01/1900"
								data-parsley-mindate="01/01/1900" data-parsley-required="true" />
							<form:errors class="alert alert-danger" path="toDate" /></td>
					</tr>

					<tr>
						<td colspan="2"><input class="btn" type="submit" id="submit"
							value="Submit" /> <input class="btn" type="submit" id="cancel"
							value="Cancel" /></td>
					</tr>
				</table>


				<table class="table table-striped" style="width: auto;">

					<tr>
						<th>AccountNumber</th>
						<th>AccountCreation</th>
						<th>TransactionType</th>
						<th>Amount</th>
					</tr>
					<c:forEach items="${transactions}" var="item">
						<tr>
							<td><c:out value="${item.accountNumber}" /></td>

							<td><c:out value="${item.creationTimestamp}" /></td>

							<td><c:out value="${item.transactionType}" /></td>

							<td><c:out value="${item.amount}" /></td>
					</c:forEach>
				</table>

			</form:form>
		</div>
	</center>
	<script src="web_resources/theme/js/jquery.min.js"></script>
	<script src="web_resources/theme/js/jquery-ui.min.js"></script>
	<script src="web_resources/theme/js/bootstrap.min.js"></script>
	<script src="web_resources/theme/js/parsley.min.js"></script>
	<script type="text/javascript">
		$('#submit').click(
				function() {
					$('#externalsearchtrans').parsley().validate();
					if (true == $('#externalsearchtrans').parsley().isValid()) {
						$('#externalsearchtrans').parsley().destroy();
						$('#externalsearchtrans').attr("action",
								"externalsearchtransform");
					} else {
						return false;
					}
				});

		$('#cancel').click(function() {
			$('#externalsearchtrans').parsley().destroy();
			$('#externalsearchtrans').attr("action", "goBack");

		});
		window.Parsley.addValidator('mindate', function(value, requirement) {
			// is valid date?
			var timestamp = Date.parse(value), minTs = Date.parse(requirement);

			return isNaN(timestamp) ? false : timestamp > minTs;
		}, 32).addMessage('en', 'mindate',
				'This date should be greater than %s');
	</script>
</body>
</html>