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

<title>Create Employee</title>

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

</head>
<script type="text/javascript">
      function signup_validate() {
    	// alert('validating');
    	  var whitePattern = "^[a-zA-Z0-9]*$";
    	  //var name = '#(username)'.val();
    	  var fname = document.forms["createEmployeeForm"]["fname"].value;
    	  var mname = document.forms["createEmployeeForm"]["mname"].value;
    	  var lname = document.forms["createEmployeeForm"]["lname"].value;
    	  var dob = document.forms["createEmployeeForm"]["dob"].value;
    	  var address = document.forms["createEmployeeForm"]["address"].value;
    	  var city = document.forms["createEmployeeForm"]["city"].value;
    	  var ssn = document.forms["createEmployeeForm"]["ssn"].value;
    	  var state = document.forms["createEmployeeForm"]["state"].value;
    	  var country = document.forms["createEmployeeForm"]["country"].value;
    	  var zip = document.forms["createEmployeeForm"]["zip"].value;
    	  var phno = document.forms["createEmployeeForm"]["phno"].value;
    	  var email = document.forms["createEmployeeForm"]["email"].value;
    	//  alert ('all params received');
      		if(!fname.match(/^[a-zA-Z0-9]*$/) || !mname.match(/^[a-zA-Z0-9]*$/) || !lname.match(/^[a-zA-Z0-9]*$/) || !ssn.match(/^[a-zA-Z0-9]*$/) || !address.match(/^[a-zA-Z0-9]*$/) || !city.match(/^[a-zA-Z0-9]*$/) || !state.match(/^[a-zA-Z0-9]*$/) || !country.match(/^[a-zA-Z0-9]*$/) || !zip.match(/^[a-zA-Z0-9]*$/) || !phno.match(/^[a-zA-Z0-9]*$/)){
    			alert('Error');
    			return false;
    		}
    	    	 
          if (document.forms["f"]["username"].value == ""
                  && document.forms["f"]["password"].value == "") {
              alert("${noUser} & ${noPass}");
              document.f.j_username.focus();
              return false;
          }
          if (document.forms["f"]["username"].value == "") {
              alert("${noUser}");
              document.f.j_username.focus();
              return false;
          }
          if (document.forms["f"]["password"].value == "") {
              alert("${noPass}");
              document.f.j_password.focus();
              return false;
          }
          
      }
    </script>
<body>

<center>
<c:if test="${!empty message}">
				<div class="alert alert-danger">${message}</div>
			</c:if>
		<div class="container">

			<form class="form-horizontal" id="createEmployeeForm" method="post">
				<legend>Register Employee</legend>
				<table width="700px" height="150px" cellspacing="10">
					<tr>
						<td style="white-space: nowrap"><form:label
								path="user.firstName" >First Name</form:label></td>
						<td><form:input path="user.firstName" id = "fname"/> <form:errors
								class="alert alert-danger" path="user.firstName" /></td>
					</tr>
					<tr>
						<td style="white-space: nowrap"><form:label
								path="user.middleName">Middle(M I)</form:label></td>
						<td><form:input path="user.middleName" id="mname"  /></td>
					</tr>
					<tr>
						<td style="white-space: nowrap"><form:label
								path="user.lastName">Last Name</form:label></td>
						<td><form:input path="user.lastName"  id="lname"/> <form:errors
								class="alert alert-danger" path="user.lastName" /></td>
					</tr>
					<tr>
						<td style="white-space: nowrap"><form:label
								path="user.gender">Gender</form:label></td>
						<td><form:select path="user.gender">
								<form:option value="NONE" label="--- Select ---" />
								<form:option value="Male" label="Male" />
								<form:option value="Female" label="Female" />
							</form:select> <form:errors class="alert alert-danger" path="user.gender" /></td>
					</tr>
					<tr>
						<td style="white-space: nowrap"><form:label
								path="userpii.ssn">SSN</form:label></td>
						<td><form:input type="number" path="userpii.ssn" id="ssn"/> <form:errors
								class="alert alert-danger" path="userpii.ssn" /></td>
					</tr>

					<tr>
						<td style="white-space: nowrap"><form:label
								path="userpii.DateOfBirth">Date of Birth</form:label></td>
						<td><form:input type="date" path="userpii.DateOfBirth" id="dob"/> <form:errors
								class="alert alert-danger" path="userpii.DateOfBirth" /></td>
					</tr>
					<tr>
						<td style="white-space: nowrap"><form:label
								path="user.address">Address</form:label></td>
						<td><form:textarea rows="3" cols="30" path="user.address" id="address" />
							<form:errors class="alert alert-danger" path="user.address" /></td>
					</tr>

					<tr>
						<td style="white-space: nowrap"><form:label path="user.city">City</form:label>
						</td>
						<td><form:input path="user.city" /> <form:errors
								class="alert alert-danger" path="user.city" id="city"/></td>
					</tr>

					<tr>
						<td style="white-space: nowrap"><form:label path="user.state">State</form:label></td>
						<td><form:input path="user.state" /> <form:errors
								class="alert alert-danger" path="user.state" id="state"/></td>
					</tr>

					<tr>
						<td style="white-space: nowrap"><form:label
								path="user.country">Country</form:label></td>
						<td><form:input path="user.country" /> <form:errors
								class="alert alert-danger" path="user.country" id="country"/></td>
					</tr>
					<tr>
						<td style="white-space: nowrap"><form:label
								path="user.zipcode">ZIP</form:label></td>
						<td><form:input path="user.zipcode" /> <form:errors
								class="alert alert-danger" path="user.zipcode" /></td>
					</tr>



					<tr>
						<td style="white-space: nowrap"><form:label
								path="user.roleId">Role</form:label></td>
						<td><form:select path="user.roleId">
								<form:option value="-1" label="--- Select ---" />
								<form:option value="3" label="Regular" />
								<form:option value="4" label="Manager" />
								<form:option value="5" label="Admin" />
							</form:select> <form:errors class="alert alert-danger" path="user.roleId" /></td>
					</tr>
					<tr>
						<td style="white-space: nowrap"><form:label
								path="user.phoneNumber">Phone Number</form:label></td>
						<td><form:input path="user.phoneNumber" id="phno"/> <form:errors
								class="alert alert-danger" path="user.phoneNumber" /></td>
					</tr>
					<tr>
						<td style="white-space: nowrap"><form:label
								path="user.emailId">Email</form:label></td>
						<td><form:input type="email" path="user.emailId"  id="email"/> <form:errors
								class="alert alert-danger" path="user.emailId" /></td>
					</tr>
				</table>
				<input class="btn " id="submit" type="submit" value="Submit" /> <input
					class="btn " id="cancel" type="submit" value="Cancel" /> <input
					type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

			</form>
		</div>
	</center>
	<!-- jQuery -->
	<script src="web_resources/theme/js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="web_resources/theme/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$('#submit').click(function() {
			$('#createEmployeeForm').attr("action", "createEmployeeUser");
		});
		$('#cancel').click(function() {
			$('#createEmployeeForm').attr("action", "goBack");
		});
	</script>

</body>
</html>