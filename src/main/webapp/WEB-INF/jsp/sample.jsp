<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="web_resources/theme/css/main.css" rel="stylesheet">
<!--<link href="web_resources/theme/css/bootstrap.min.css" rel="stylesheet"> -->
<link
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">
<!-- jQuery library -->


<title>Insert title here</title>
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
					<li class="active"><a href="#"><span
							class="glyphicon glyphicon-chevron-right"></span> Home</a></li>
					<li><a href="#"><span
							class="glyphicon glyphicon-chevron-right"></span> Credit</a></li>
					<li><a href="#" class="active2"><span
							class="glyphicon glyphicon-chevron-right"></span> Debit</a></li>
					<li><a href="#"><span
							class="glyphicon glyphicon-chevron-right"></span> Transfer</a></li>
					<li><a href="#"><span
							class="glyphicon glyphicon-chevron-right"></span> Notifications</a></li>
					<li><a href="#"><span
							class="glyphicon glyphicon-chevron-right"></span> Create Request</a></li>
				</ul>
			</div>
			<div class="col-md-9 column margintop20">
			<%-- <%@ include file="./credit.jsp" %> --%>
			<%-- <jsp:include page="./credit.jsp"/> --%>
			<iframe src="./credit.jsp" height="70" width="900" ></iframe>
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
		});
	</script>
</body>
</html>