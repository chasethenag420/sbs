<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="X-Frame-Options" content="allow">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="web_resources/theme/css/main.css" rel="stylesheet">
<link href="web_resources/theme/css/bootstrap.min.css" rel="stylesheet">


<title>Welcome User</title>
</head>
<body>
	<div class="container">

		<div class="jumbotron">
			<h1>Regular Employee</h1>
			<p>Welcome to Bank of Tempe!</p>
		</div>
		<br>
		<center>
			<h2>${message}</h2>
		</center>
		<form:form class="form-horizontal" id='regularForm' method='POST'>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<div class="row">
				<div class="col-md-3 column margintop20">
					<ul class="nav nav-pills nav-stacked">
						<li id="home" class="active"><a , href="#"><span
								class="glyphicon glyphicon-chevron-right"></span> Home</a></li>
						<li id="userProfile"><a href="profile"><span
								class="glyphicon glyphicon-chevron-right"></span> User Profile</a></li>
						<li id="transactions"><a href="searchTransaction"><span
								class="glyphicon glyphicon-chevron-right"></span> Transactions</a></li>
						<li id="notifications"><a href="notifications"><span
								class="glyphicon glyphicon-chevron-right"></span> Notifications</a></li>
						<li id="searchuser"><a href="searchuser"><span
								class="glyphicon glyphicon-chevron-right"></span> Search User</a></li>

						<li id="logout"><a href="#"><span
								class="glyphicon glyphicon-chevron-right"></span> Logout</a></li>
					</ul>
				</div>
				<div id="content" class="col-md-9 column margintop20">
					<%-- <jsp:include page="credit.jsp"/> --%>
				</div>
			</div>

		</form:form>

	</div>

	<!-- jQuery -->
	<script src="web_resources/theme/js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="web_resources/theme/js/bootstrap.min.js"></script>

	<!-- Menu Toggle Script -->
	<script type="text/javascript">
		$('#logout').click(function() {
			$('#regularForm').attr("action", "logout").submit()
		});
	</script>


</body>
</html>