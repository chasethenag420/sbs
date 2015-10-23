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
		
		<form class="form-horizontal" id="signUpForm" name="signUpForm" method="post" action="signUp" onsubmit="return signup_validate();">
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
					<form:input class="controls" path="user.firstName" id = "fname"/>
					<form:errors class="alert alert-danger" path="user.firstName" />
				</div>
				<div class="control-group">
					<form:label class="control-label" path="user.middleName">Middle(M I)</form:label>
					<form:input  class="controls" path="user.middleName" />

				</div>
				<div class="control-group">
					<form:label class="control-label" path="user.lastName">Last Name</form:label>
					<form:input class="controls" path="user.lastName" id="lname" />
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
					<form:input type="number" class="controls" path="userpii.ssn" id="ssn"/>
					<form:errors class="alert alert-danger" path="userpii.ssn" />

				</div>
				
				<div class="control-group">
					<form:label class="control-label" path="userpii.DateOfBirth">Date of Birth</form:label>
					<form:input  type="date" class="controls" path="userpii.DateOfBirth" />

					<form:errors class="alert alert-danger" path="userpii.DateOfBirth" />

				</div>
				<div class="control-group">
					<form:label class="control-label" path="user.address">Address</form:label>
					<form:textarea class="controls" rows="3" cols="30" path="user.address" id="address"/>
					<form:errors class="alert alert-danger" path="user.address" />

				</div>
				
				<div class="control-group">
					<form:label class="control-label" path="user.city">City</form:label>
					<form:input class="controls" path="user.city" id="city" />
					<form:errors class="alert alert-danger" path="user.city" />
				</div>
				
				<div class="control-group">
					<form:label class="control-label" path="user.state">State</form:label>
					<form:input class="controls" path="user.state" id="state"/>
					<form:errors class="alert alert-danger" path="user.state" />
				</div>
				
				<div class="control-group">
					<form:label class="control-label" path="user.country">Country</form:label>
					<form:input class="controls" path="user.country" id="country"/>
					<form:errors class="alert alert-danger" path="user.country" />

				</div>
				<div class="control-group">
					<form:label class="control-label" path="user.zipcode">ZIP</form:label>
					<form:input class="controls" path="user.zipcode" id="zip" />
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
					<form:input class="controls" path="user.phoneNumber" id="phno" />
					<form:errors class="alert alert-danger" path="user.phoneNumber" />
				</div>
				<div class="control-group">
					<form:label class="control-label" path="user.emailId">Email</form:label>
					<form:input class="controls" type="email" path="user.emailId" id="email"/>
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
	<script type="text/javascript">
      function signup_validate() {
    	 //alert('validating');
    	  var whitePattern = "^[a-zA-Z0-9]*$";
    	  //var name = '#(username)'.val();
    	  var uname = document.forms["signUpForm"]["username"].value;
    	  var pass = 	document.forms["signUpForm"]["password"].value;
    	  var fname = document.forms["signUpForm"]["fname"].value;
    	  var mname = document.forms["signUpForm"]["mname"].value;
    	  var lname = document.forms["signUpForm"]["lname"].value;
    	  var ssn = document.forms["signUpForm"]["ssn"].value;
    	  var dob = document.forms["signUpForm"]["dob"].value;
    	  var address = document.forms["signUpForm"]["address"].value;
    	  var city = document.forms["signUpForm"]["city"].value;
    	  var state = document.forms["signUpForm"]["state"].value;
    	  var country = document.forms["signUpForm"]["country"].value;
    	  var zip = document.forms["signUpForm"]["zip"].value;
    	  var phno = document.forms["signUpForm"]["phno"].value;
    	  var email = document.forms["signUpForm"]["email"].value;
    	//  alert ('all params received');
    	var flag=0;
    	if (uname == "" && pass == "") {
            alert("NO USERNAME AND PASSWORD");
            flag=1;
            return false;
        } 
    	  if(uname.length > 10){
    	  		alert('Length error');
    	  		flag=1;
    	  }
    	  if(!pass.match(/^(?=.*[a-z])(?=.*\\d)(?=.*[A-Z])$/)|| pass.length > 40){
    		  alert('Password does not comply to the rules');
    		  return false; 
    	  }
    		  
    		if(!uname.match(/^[a-zA-Z0-9]*$/) || !fname.match(/^[a-zA-Z0-9]*$/) || !mname.match(/^[a-zA-Z0-9]*$/) || !lname.match(/^[a-zA-Z0-9]*$/) || !ssn.match(/^[a-zA-Z0-9]*$/) || !address.match(/^[a-zA-Z0-9]*$/) || !city.match(/^[a-zA-Z0-9]*$/) || !state.match(/^[a-zA-Z0-9]*$/) || !country.match(/^[a-zA-Z0-9]*$/) || !zip.match(/^[a-zA-Z0-9]*$/) || !phno.match(/^[a-zA-Z0-9]*$/)){
    			alert('Error');
    			flag=1;
    		}
    	    	 
          
          if (uname == "") {
              alert("No username");
              flag=1;
              return false;
          }
          if (pass == "") {
              alert("No password");
              flag=1;
              return false; 
          }
          if(flag==0)
        	  return true;
          else
        	  return false;
      }
    </script>
</body>
</html>