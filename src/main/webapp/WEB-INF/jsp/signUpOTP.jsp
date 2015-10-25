<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="messages" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="X-Frame-Options" content="allow">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="web_resources/theme/css/bootstrap.css" rel="stylesheet">
<link href="web_resources/theme/css/bootstrap-responsive.css" rel="stylesheet">
<title>
<spring:message code="title.credit"></spring:message>
</title>
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
 <center>
  <br/><br/>
  <div style="color: teal; font-size: 30px">Enter OTP</div>
  <br/><br/>
  <form:form name = "signUpOTP" id="signUpOTP"  modelAttribute="form" method="post" >
   <center>
   <div style="white-space: nowrap" ><b>An OTP is sent to your email : ${form.map.email}</b></div><br/><br/>
   <table  class="table table-striped" style="width: auto;">
   <tr></tr>
   
    <tr></tr>
    <tr>
     <td style="white-space: nowrap"><form:label path="map['OTP']" for="otp">OTP</form:label>
     </td>
     <td><form:input path="map['OTP']" data-parsley-required="true" name="otp"
								data-parsley-type="alphanum" data-parsley-length="[6, 15]"/>
     </td>
     <td><c:if test="${!empty errorMessage}">
					<div class="alert alert-danger">${errorMessage}</div>
				</c:if></td> 
    </tr>
   
    <tr> 
     <form:input type="hidden" path="map['username']" value="${form.map.username}"/>
     <td><input type="submit" id="submit" value="Submit" /></td>
     </tr>
     <tr></tr>
     <tr></tr>
     <tr></tr>
      <tr> 
     <td><input type="submit" id="createNewOTP" value="Create New OTP" /></td>
     </tr>
   </table>
   
   </center>
  </form:form>
  
 </center>
 
 <!-- jQuery -->
	<script src="web_resources/theme/js/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="web_resources/theme/js/bootstrap.min.js"></script>
	<script src="web_resources/theme/js/parsley.min.js"></script>
 <script type="text/javascript">

$('#submit').click(
		function() {
			$('#signUpOTP').parsley().validate();
			if (true == $('#signUpOTP').parsley().isValid()) {
				$('#signUpOTP').parsley().destroy();
				$('#signUpOTP').attr("action",
						"enterSignupOTP");
			} else {
				return false;
			}
		});

$('#createNewOTP').click(function() {
	$('#signUpOTP').parsley().destroy();
	$('#signUpOTP').attr("action", "sendOTPAgain");

});
</script>

</body>
</html>

