<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
	<div class="container">

		<div class="jumbotron">
			<h1>Individual User</h1>
			<p>Welcome to Bank of Tempe!</p>
		</div>

		<div class="row">
			<div class="col-md-3 column margintop20">
				<ul class="nav nav-pills nav-stacked">
					<li id="home" class="active"><a 
						, href="#"><span class="glyphicon glyphicon-chevron-right"></span>
							Home</a></li>
					<li id="credit"><a href="#"><span
							class="glyphicon glyphicon-chevron-right"></span> Credit</a></li>
					<li id="debit"><a  href="#"><span
							class="glyphicon glyphicon-chevron-right"></span> Debit</a></li>
					<li id="transfer"><a href="#"><span
							class="glyphicon glyphicon-chevron-right"></span> Transfer</a></li>
					<li id="addAccount"><a href="#"><span
							class="glyphicon glyphicon-chevron-right"></span> Add Account</a></li>
					<li id="transactions"><a href="#"><span
							class="glyphicon glyphicon-chevron-right"></span> Transactions</a></li>
					<li id="notifications"><a href="#"><span
							class="glyphicon glyphicon-chevron-right"></span> Notifications</a></li>
					<li id="createRequest"><a href="#"><span
							class="glyphicon glyphicon-chevron-right"></span> Create Request</a></li>
					<li id="logout"><a href="logout"><span
							class="glyphicon glyphicon-chevron-right"></span> Logout</a></li>
				</ul>
			</div>
			<div id="content" class="col-md-9 column margintop20">
				<%-- <jsp:include page="credit.jsp"/> --%>
			</div>
		</div>
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
			if (menuValue == "credit")
				$('#content').load('credit');
			else if (menuValue == "debit")
				$('#content').load('debit');
			else if (menuValue == "transfer")
				$('#content').load('transfer');
			else if (menuValue == "addAccount")
				$('#content').load('regular');
			else if (menuValue == "transactions")
				$('#content').load('searchTransaction');
			else if (menuValue == "notifications")
				$('#content').load('notifications');
			else if (menuValue == "addRequest")
				$('#content').load('transfer');
		}
		$(function(){
			alert($(location).attr('hash'));
		})
	</script>
</body>
</html>