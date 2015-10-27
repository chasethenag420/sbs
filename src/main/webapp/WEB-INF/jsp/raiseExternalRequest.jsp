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

<title>raise Request</title>
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
	<br>
	<center>

		<div style="color: teal; font-size: 30px">Raise Request</div>
		<br>
		<form class="form-horizontal" id="raiseExternalRequest"  name="raiseExternalRequest" method="post">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<center>
				<table class="table table-striped" style="width: auto;">
					<tr>
						<td style="white-space: nowrap"><form:label
								for="regrequestType" path="authorization.requestType">RequestType</form:label></td>
						<td><form:select name="regrequestType"
								path="authorization.requestType" data-parsley-required="true">
								<form:option value="" label="--- Select ---" />
								<form:option value="Modify Profile" label="Modify Profile" />
								<form:option value="View Transaction" label="Review Transaction(s)" />
							</form:select></td>
					</tr>
					<tr>
						<td style="white-space: nowrap"><form:label for="description"
								path="authorization.requestDescription">Request Description</form:label>
						</td>
						<td><form:input path="authorization.requestDescription"
								name="description" data-parsley-required="true"
								data-parsley-pattern="[a-z A-Z]+" data-parsley-length="[1, 50]" /></td>
					</tr>
				</table>
				<input class="btn" type="submit" id="submit" value="Submit" /> <input
					class="btn" type="submit" id="cancel" value="Cancel" />
			</center>

		</form>
	</center>
	<!-- jQuery -->
	<script src="web_resources/theme/js/jquery.min.js"></script>



	<!-- Bootstrap Core JavaScript -->
	<script src="web_resources/theme/js/bootstrap.min.js"></script>
	<script src="web_resources/theme/js/parsley.min.js"></script>
	<script type="text/javascript">
		
		$('#submit').click(function() {
			$('#raiseExternalRequest').parsley().validate();
			if (true == $('#raiseExternalRequest').parsley().isValid()) {
				$('#raiseExternalRequest').parsley().destroy();
				$('#raiseExternalRequest').attr("action", "externalrequest");
			} else {
				return false;
			}
		});

		$('#cancel').click(function() {
			$('#raiseExternalRequest').parsley().destroy();
			$('#raiseExternalRequest').attr("action", "goBack");

		});
	</script>
</body>
</html>
