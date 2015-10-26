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

<title>Display Transactions</title>
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


		<form class="form-horizontal" name="searchTransaction"
			id="searchTransaction" method="post">

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" /> <label for="transactionrecord"><b>Transaction
					List</b></label>
			<c:if test="${!empty transactions}">
				<table class="table table-striped" style="width: auto;">
					<tr>
						<th>&nbsp;</th>
						<th>AccountNumber</th>
						<th>AccountCreation</th>
						<th>TransactionType</th>
						<th>Amount</th>
					</tr>
					<c:forEach items="${transactions}" var="item">
						<tr>
							<td><form:radiobutton path="form.map['transactionId']"
									name="transactionrecord" data-parsley-required="true"
									value="${item.transactionId}" /></td>
							<td><c:out value="${item.accountNumber}" /></td>

							<td><c:out value="${item.creationTimestamp}" /></td>

							<td><c:out value="${item.transactionType}" /></td>

							<td><c:out value="${item.amount}" /></td>
						</tr>
					</c:forEach>

				</table>
				<input id="view" type="submit" class="btn" value="View" />
				<input id="modify" type="submit" class="btn" value="Modify" />
				<input id="delete" type="submit" class="btn btn-danger"
					value="Delete" />
			</c:if>

			<input id="cancel" type="submit" class="btn" value="Cancel" />
		</form>
	</center>


	<!-- jQuery -->
	<script src="web_resources/theme/js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="web_resources/theme/js/bootstrap.min.js"></script>

	<script src="web_resources/theme/js/parsley.min.js"></script>
	<script type="text/javascript">
		$('#delete').click(function() {
			$('#searchTransaction').parsley().validate();
			if (true == $('#searchTransaction').parsley().isValid()) {
				$('#searchTransaction').parsley().destroy();
				$('#searchTransaction').attr("action", "deleteTransaction");
			} else {
				return false;
			}
		});
		$('#modify').click(function() {
			$('#searchTransaction').parsley().validate();
			if (true == $('#searchTransaction').parsley().isValid()) {
				$('#searchTransaction').parsley().destroy();
				$('#searchTransaction').attr("action", "modifyTransaction");
			} else {
				return false;
			}
		});

		$('#view').click(function() {
			$('#searchTransaction').parsley().validate();
			if (true == $('#searchTransaction').parsley().isValid()) {
				$('#searchTransaction').parsley().destroy();
				$('#searchTransaction').attr("action", "viewTransaction");
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
