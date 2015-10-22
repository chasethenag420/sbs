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


<!-- Keyboard Code start -->
<link href="web_resources/theme/css/jquery-ui.min.css" rel="stylesheet"> 
	<script src="web_resources/theme/js/jquery.min.js"></script>
	<script src="web_resources/theme/js/jquery-ui.min.js"></script>
	<script src="web_resources/theme/js/bootstrap.min.js"></script>

	<!-- keyboard widget css & script (required) -->
	<link href="web_resources/theme/css/keyboard.css" rel="stylesheet">
	<script src="web_resources/theme/js/jquery.keyboard.js"></script>

	<!-- keyboard extensions (optional) -->
	<script src="web_resources/theme/js/jquery.mousewheel.js"></script>
	
	<!-- initialize keyboard (required) -->
	<script>
		$(function(){
		$("input[id^='keyboard']").keyboard();
			<!-- $('#keyboard').keyboard(); -->
		});
	</script>
<!-- Keyboard Code end -->

<link href="web_resources/theme/css/bootstrap.css" rel="stylesheet">
<link href="web_resources/theme/css/bootstrap-responsive.css" rel="stylesheet">
<title>Sign Up Form</title>

<style type="text/css">
.wrapper {
	width: 500px;
	margin-left: auto;
	margin-right: auto
}

label {
	padding-left: 0 !important
}
</style>

<script type="text/javascript">

   //Created / Generates the captcha function    
    function DrawCaptcha()
    {
    	document.getElementById("txtCaptcha").readOnly = false;
    	document.getElementById("submitbutton").disabled = true;
        var a = Math.ceil(Math.random() * 10)+ '';
        var b = Math.ceil(Math.random() * 10)+ '';       
        var c = Math.ceil(Math.random() * 10)+ '';  
        var d = Math.ceil(Math.random() * 10)+ '';  
        var e = Math.ceil(Math.random() * 10)+ '';  
        var f = Math.ceil(Math.random() * 10)+ '';  
        var g = Math.ceil(Math.random() * 10)+ '';  
        var code = a + ' ' + b + ' ' + ' ' + c + ' ' + d + ' ' + e + ' '+ f + ' ' + g;
        document.getElementById("txtCaptcha").value = code;
        document.getElementById("txtCaptcha").readOnly = true;
        document.getElementById("txtInput").value = '';
    }

    // Validate the Entered input aganist the generated security code function   
    	
	function ValidCaptcha(){
        var str1 = removeSpaces(document.getElementById('txtCaptcha').value);
        var str2 = removeSpaces(document.getElementById('txtInput').value);
        if (str1 == str2)
        {	
        	document.getElementById("submitbutton").disabled = false;
        	document.getElementById("txtInput").value = '';
        }
        else
        {
			DrawCaptcha();
			document.getElementById("txtInput").value = '';
			document.getElementById("submitbutton").disabled = true;
		}
    }
    // Remove the spaces from the entered and generated code
    function removeSpaces(string)
    {
        return string.split(' ').join('');
    }
    
 
</script>

</head>
<body onload="DrawCaptcha();">

	<div class="container">
		
		<form class="form-horizontal" id="signUpForm" method="post" action="signUp">
		<fieldset>
		<legend>Registration Form</legend>
				
				<div class="control-group">

					<form:label class="control-label " path="user.userName">Username</form:label>
					<form:input id="keyboard" class="controls " path="user.userName" />
					<form:errors class="alert alert-danger" path="user.userName" />

				</div>

				<div class="control-group">
					<form:label  class="control-label" path="user.password">Password</form:label>
					<form:input id="keyboard"  class="controls" type="password" path="user.password" />
					<form:errors class="alert alert-danger" path="user.password" />
				</div>
				<div class="control-group">
					<form:label class="control-label" path="user.firstName">First Name</form:label>
					<form:input class="controls" path="user.firstName" />
					<form:errors class="alert alert-danger" path="user.firstName" />
				</div>
				<div class="control-group">
					<form:label class="control-label" path="user.middleName">Middle(M I)</form:label>
					<form:input  class="controls" path="user.middleName" />
				</div>
				<div class="control-group">
					<form:label class="control-label" path="user.lastName">Last Name</form:label>
					<form:input class="controls" path="user.lastName" />
					<form:errors class="alert alert-danger" path="user.lastName" />
				</div>
				<div class="control-group">
					<form:label class="control-label" path="user.gender">Gender</form:label>
					<form:select class="controls" path="user.gender">
						<form:option value="NONE" label="--- Select ---" />
						<form:option value="Male" label="Male" />
						<form:option value="Female" label="Female" />
					</form:select>
					<form:errors class="alert alert-danger" path="user.gender" />
				</div>
				<div class="control-group">
					<form:label class="control-label" path="userpii.ssn">SSN</form:label>
					<form:input type="number" class="controls" path="userpii.ssn" />
					<form:errors class="alert alert-danger" path="userpii.ssn" />

				</div>
				
				<div class="control-group">
					<form:label class="control-label" path="userpii.DateOfBirth">Date of Birth</form:label>
					<form:input  type="date" class="controls" path="userpii.DateOfBirth" />
					<form:errors class="alert alert-danger" path="userpii.DateOfBirth" />

				</div>
				<div class="control-group">
					<form:label class="control-label" path="user.address">Address</form:label>
					<form:textarea class="controls" rows="3" cols="30" path="user.address" />
					<form:errors class="alert alert-danger" path="user.address" />

				</div>
				
				<div class="control-group">
					<form:label class="control-label" path="user.city">City</form:label>
					<form:input class="controls" path="user.city" />
					<form:errors class="alert alert-danger" path="user.city" />
				</div>
				
				<div class="control-group">
					<form:label class="control-label" path="user.state">State</form:label>
					<form:input class="controls" path="user.state" />
					<form:errors class="alert alert-danger" path="user.state" />
				</div>
				
				<div class="control-group">
					<form:label class="control-label" path="user.country">Country</form:label>
					<form:input class="controls" path="user.country" />
					<form:errors class="alert alert-danger" path="user.country" />

				</div>
				<div class="control-group">
					<form:label class="control-label" path="user.zipcode">ZIP</form:label>
					<form:input class="controls" path="user.zipcode" />
					<form:errors class="alert alert-danger" path="user.zipcode" />

				</div>
				
				
				
				<div class="control-group">
					<form:label class="control-label" path="user.roleId">Role</form:label>
					<form:select class="controls" path="user.roleId">
						<form:option value="-1" label="--- Select ---" />
						<form:option value="1" label="Individual" />
						<form:option value="2" label="Organization/Merchant" />
					</form:select>
					<form:errors class="alert alert-danger" path="user.roleId" />
				</div>
				<div class="control-group">
					<form:label class="control-label" path="user.phoneNumber">Phone Number</form:label>
					<form:input class="controls" path="user.phoneNumber" />
					<form:errors class="alert alert-danger" path="user.phoneNumber" />
				</div>
				<div class="control-group">
					<form:label class="control-label" path="user.emailId">Email</form:label>
					<form:input class="controls" type="email" path="user.emailId" />
					<form:errors class="alert alert-danger" path="user.emailId" />
				</div>

				<table>
					<tr>
    					<td>Captcha<br /></td>
					</tr>
					
					<tr>
    					<td>
        					<input type="text" id="txtCaptcha" style="background-image: url(/sbs/web_resources/img/1.jpg); text-align:center; border:none;
            				font-weight:bold; font-family:Modern" />
        					<input type="button" id="btnrefresh" value="Refresh" onclick="DrawCaptcha();"/>
    					</td>
					</tr>
					
					<tr>
    					<td>
        					<input type="text" id="txtInput"/>    	
    					</td>
					</tr>
						
					<tr>
    					<td>
        					<input id="Button1" type="button" value="Validate Captcha to enable Submit" onclick="ValidCaptcha();"/>
    					</td>
					</tr>
				</table>	


				<div class="control-group">
					<div class="controls">
						<input id='submitbutton' class="btn  btn-primary " type="submit" value="Submit" />
						<a class="btn  btn-primary " href="sample" type="button"  >Cancel</a>
					</div>
				</div>



				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</fieldset>
		</form>
	</div>
</body>
</html>