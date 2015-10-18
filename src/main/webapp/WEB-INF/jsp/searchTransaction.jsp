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


		<form class="form-horizontal" id="searchTransaction" method="post"
			action="searchTransaction">

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<div class="control-group">
				<form:label class="control-label" path="user.firstName">First Name</form:label>
				<form:input class="controls" path="user.firstName" />
				<form:errors class="alert alert-danger" path="user.firstName" />
			</div>

			<div class="control-group">
				<form:label class="control-label" path="account.accountNumber">AccountNumber</form:label>
				<form:input class="controls" path="account.accountNumber" />
			</div>

			<div class="control-group">
				<div class="controls">
					<input class="btn  btn-primary " type="submit" value="Submit" /> <a
						class="btn  btn-primary " href="sample" type="button">Cancel</a>
				</div>

				<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
				<table>
					<c:forEach items="${transactions}" var="item">
						<tr>
							<td><c:out value="${item}" /></td>
						</tr>
					</c:forEach>
				</table>
			</div>


		</form>
	</center>
</body>
</html>
