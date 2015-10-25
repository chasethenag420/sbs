<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="messages" />
<%@ page session="true"%>
<fmt:message key="message.password" var="noPass" />
<fmt:message key="message.username" var="noUser" />
<c:if test="${param.error != null}">
	<c:choose>
		<c:when
			test="${SPRING_SECURITY_LAST_EXCEPTION.message == 'User is disabled'}">
			<div class="alert alert-danger">
				<spring:message code="auth.message.disabled"></spring:message>
			</div>
		</c:when>
		<c:when
			test="${SPRING_SECURITY_LAST_EXCEPTION.message == 'User account has expired'}">
			<div class="alert alert-danger">
				<spring:message code="auth.message.expired"></spring:message>
			</div>
		</c:when>
		<c:when test="${SPRING_SECURITY_LAST_EXCEPTION.message == 'blocked'}">
			<div class="alert alert-danger">
				<spring:message code="auth.message.blocked"></spring:message>
			</div>
		</c:when>
		<c:otherwise>
			<div class="alert alert-danger">
				<!-- <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/> -->
				<spring:message code="message.badCredentials"></spring:message>
			</div>
		</c:otherwise>
	</c:choose>
</c:if>
<html>
<head>
<link href="web_resources/theme/css/jquery-ui.min.css" rel="stylesheet">
<!-- keyboard widget css & script (required) -->
<link href="web_resources/theme/css/keyboard.css" rel="stylesheet">

<link href="web_resources/theme/css/bootstrap.css" rel="stylesheet">
<link href="web_resources/theme/css/bootstrap-responsive.css"
	rel="stylesheet">
<title><spring:message code="label.pages.home.title"></spring:message>
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

.parsley-errors-list {
	color: #B94A48;
	background-color: #F2DEDE;
	border: 1px solid #EED3D7;
	float: right;
}
</style>
<script src="web_resources/theme/js/jquery.min.js"></script>
<script src="web_resources/theme/js/jquery-ui.min.js"></script>
<script src="web_resources/theme/js/bootstrap.min.js"></script>


<script src="web_resources/theme/js/jquery.keyboard.js"></script>

<!-- keyboard extensions (optional) -->
<script src="web_resources/theme/js/jquery.mousewheel.js"></script>
<!-- keyboard extensions (optional) -->
<script src="web_resources/theme/js/parsley.min.js"></script>

<!-- initialize keyboard (required) -->
<script type="text/javascript">
	$(function() {
		$('#username').keyboard();
		$('#password').keyboard();
	});
	$('#submitbutton').on('click', function() {
		$('#f').parsley().validate();

		if (true != $('#f').parsley().isValid()) {
			$('#submitbutton').attr('disabled', 'disabled');
		}
	});

	//Created / Generates the captcha function    
	function DrawCaptcha() {
		document.getElementById("txtCaptcha").readOnly = false;
		document.getElementById("submitbutton").disabled = true;
		var a = Math.ceil(Math.random() * 10) + '';
		var b = Math.ceil(Math.random() * 10) + '';
		var c = Math.ceil(Math.random() * 10) + '';
		var d = Math.ceil(Math.random() * 10) + '';
		var e = Math.ceil(Math.random() * 10) + '';
		var f = Math.ceil(Math.random() * 10) + '';
		var g = Math.ceil(Math.random() * 10) + '';
		var code = a + ' ' + b + ' ' + ' ' + c + ' ' + d + ' ' + e + ' ' + f
				+ ' ' + g;
		document.getElementById("txtCaptcha").value = code;
		document.getElementById("txtCaptcha").readOnly = true;
		document.getElementById("txtInput").value = '';
	}

	// Validate the Entered input aganist the generated security code function   

	function ValidCaptcha() {
		var str1 = removeSpaces(document.getElementById('txtCaptcha').value);
		var str2 = removeSpaces(document.getElementById('txtInput').value);
		if (str1 == str2) {
			document.getElementById("submitbutton").disabled = false;
			document.getElementById("txtInput").value = '';
		} else {
			DrawCaptcha();
			document.getElementById("txtInput").value = '';
			document.getElementById("submitbutton").disabled = true;
		}
	}
	// Remove the spaces from the entered and generated code
	function removeSpaces(string) {
		return string.split(' ').join('');
	}
</script>
</head>




<body onload="DrawCaptcha();">
	<c:if test="${param.message != null}">
		<div class="alert alert-info">${param.message}</div>
	</c:if>
	<div class="container">
		<div class="row wrapper">
			<h1>
				<spring:message code="label.form.loginTitle"></spring:message>
			</h1>
			<br> <br>
			<form name="f" id="f" action="j_spring_security_check" method='POST'
				data-parsley-validate>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
				<table class="table table-striped " style="width: auto;">
					<tr>
						<td style="white-space: nowrap"><label for="username"
							class="col-sm-4"> <spring:message
									code="label.form.userName"></spring:message>
						</label></td>
						<td><span class="col-sm-8"> <input id="username"
								class="form-control" type='text' name='username' value=''
								required data-parsley-type="alphanum"
								data-parsley-length="[6, 15]"
								data-parsley-length-message="Username should be between 6 to 15 characters" />

						</span></td>
					<tr>
						<td><label for="password" class="col-sm-4"> <spring:message
									code="label.form.loginPass">
								</spring:message>
						</label></td>
						<td><span class="col-sm-8"> <input
								class="form-control" id="password" type='password'
								name='password' required data-parsley-type="alphanum"
								data-parsley-length="[6, 15]"
								data-parsley-length-message="Password should be between 6 to 15 characters" />
						</span></td>
					</tr>
					<tr>
						<td><b>Captcha</b></td>
						<td><input type="text" id="txtInput" /> &nbsp;&nbsp;<input
							type="text" id="txtCaptcha"
							style="background-image: url(/sbs/web_resources/img/1.jpg); text-align: center; border: none; font-weight: bold; font-family: Modern" />


						</td>

					</tr>

					<tr>
						<td>&nbsp;</td>
						<td><input id="Button1" class="btn btn-info btn-xs"
							type="button" value="Validate Captcha to enable Submit"
							onclick="ValidCaptcha();" /> &nbsp; &nbsp; <input type="button"
							class="btn btn-info btn-xs" id="btnrefresh" value="Refresh"
							onclick="DrawCaptcha();" /></td>
					</tr>
					<tr>
						
						<td><input id='submitbutton' class="btn btn-primary"
							type="submit"
							value=<spring:message code="label.form.submit"></spring:message> /></td>
							<td>&nbsp;</td>
					</tr>
					<tr>
						<td  colspan="2"><a class="btn btn-default"
							href="
        <c:url value="/signup.html" />
        "> <spring:message
									code="label.form.loginSignUp"></spring:message>
						</a></td>
					</tr>
					<tr>
						<td colspan="2"><a class="btn btn-default"
							href="
        <c:url value="/forgetPassword.html" />
        ">
								<spring:message code="label.form.forgetPassword"></spring:message>
						</a></td>
					</tr>
				</table>
				<br> <br>
			</form>

		</div>
	</div>
</body>
</html>