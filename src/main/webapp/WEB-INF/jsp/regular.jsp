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
<!--<link href="web_resources/theme/css/bootstrap.min.css" rel="stylesheet"> -->
<link
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">
<!-- jQuery library -->


<title>Welcome User</title>
</head>
<body>
	<div class="container">

		<div class="jumbotron">
			<h1>Regular Employee</h1>
			<p>Welcome to Bank of Tempe!</p>
		</div>

		<form:form class="form-horizontal" id='individualForm' method='POST'>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<div class="row">
				<div class="col-md-3 column margintop20">
					<ul class="nav nav-pills nav-stacked">
						<li id="home" class="active"><a , href="#"><span
								class="glyphicon glyphicon-chevron-right"></span> Home</a></li>
						<li id="profile"><a href="#"><span
								class="glyphicon glyphicon-chevron-right"></span> profile</a></li>

						<li id="transactions"><a href="#"><span
								class="glyphicon glyphicon-chevron-right"></span> Transactions</a></li>
						<li id="notifications"><a href="#"><span
								class="glyphicon glyphicon-chevron-right"></span> Notifications</a></li>
						<li id="createRequest"><a href="#"><span
								class="glyphicon glyphicon-chevron-right"></span> Create Request</a></li>



						<li id="modifyUser"><a href="#"><span
								class="glyphicon glyphicon-chevron-right"></span> ModifyUser</a></li>
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
	<script>
		$(".nav li").on("click", function() {
			$(".nav li").removeClass("active");
			$(this).addClass("active");
			changeContent();
		});

		function changeContent() {
			var menuValue = $(".active").attr('id');
			if (menuValue == "profile")
				$('#content').load('profile');

			else if (menuValue == "transactions")
				$('#content').load('searchTransaction');
			else if (menuValue == "notifications")
				$('#content').load('notifications');
			else if (menuValue == "createRequest")
				$('#content').load('regularEmprequest');
		}
	</script>
	<script type="text/javascript">

		$('#logout').click(function() {
			$('#merchantForm').attr("action", "logout").submit()
		});
	</script>


</body>
</html>