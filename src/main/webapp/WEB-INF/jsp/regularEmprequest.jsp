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
</head>
<body>
<br>
	<center>

	<div style="color: teal; font-size: 30px">Raise Request</div>
  <br>
		<form class="form-horizontal" id="regularEmprequest" method="post">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<center>
				<div class="control-group">
					<form:label class="control-label" path="user.userName">Username</form:label>
					<form:input class="controls" path="user.userName" />
					<form:errors class="alert alert-danger" path="user.userName" />
				</div>

				<div class="control-group">
					<form:label class="control-label" path="authorization.requestType">RequestType</form:label>
					<form:input class="controls" path="authorization.requestType" />
				</div>
				<div class="control-group">
					<form:label class="control-label"
						path="authorization.requestDescription">Request Description</form:label>
					<form:input class="controls"
						path="authorization.requestDescription" />
				</div>

				<div class="control-group">
					<div class="controls">
						<input class="btn  btn-primary " id="submit" type="submit" value="Submit" />
						<input class="btn" type="submit" id="cancel" value="Cancel" />
					</div>
				</div>
			</center>

		</form>
	</center>
	<!-- jQuery -->
	<script src="web_resources/theme/js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="web_resources/theme/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$('#submit').click(function() {
			$('#regularEmprequest').attr("action", "regularrequest");
		});
		$('#cancel').click(function() {
			$('#regularEmprequest').attr("action", "goBack");
		});
	</script>
</body>
</html>
