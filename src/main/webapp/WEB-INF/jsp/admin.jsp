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
					<li id="viewProfile"><a href="#"><span
							class="glyphicon glyphicon-chevron-right"></span> View Profile</a></li>
					<li id="createEmployee"><a href="#"><span
							class="glyphicon glyphicon-chevron-right"></span> Create Employee</a></li>
					<li id="modifyEmployee"><a  href="#"><span
							class="glyphicon glyphicon-chevron-right"></span> Modify Employee</a></li>
					<li id="deleteEmployee"><a href="#"><span
							class="glyphicon glyphicon-chevron-right"></span> Delete Employee</a></li>
					<li id="systemLog"><a href="#"><span
							class="glyphicon glyphicon-chevron-right"></span> System Log</a></li>
					<li id="piiInformation"><a href="#"><span
							class="glyphicon glyphicon-chevron-right"></span> Get PII</a></li>
					<li id="notifications"><a href="#"><span
							class="glyphicon glyphicon-chevron-right"></span> Notifications</a></li>
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
			if (menuValue == "viewProfile")
				$('#content').load('credit');
			else if (menuValue == "createEmployee")
				$('#content').load('createEmployee');
			else if (menuValue == "modifyEmployee")
				$('#content').load('transfer');
			else if (menuValue == "deleteEmployee")
				$('#content').load('transfer');
			else if (menuValue == "systemLog")
				$('#content').load('transfer');
			else if (menuValue == "piiInformation")
				$('#content').load('transfer');
			else if (menuValue == "addRequest")
				$('#content').load('notifications');
		}
	/* 	$(function(){
			alert($(location).attr('hash'));
		}) */
		<%-- alert("<%=request.getSession(false).getAttribute("tab")%>");
		$(document).on("load", function(event){
			<% String tabId=(String)request.getSession(false).getAttribute("tab");%>
			tabId
		}) --%>
	</script>
</body>
</html>