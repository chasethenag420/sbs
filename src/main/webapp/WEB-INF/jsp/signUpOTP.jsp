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
</head>
<body>
 <center>
  <br/><br/>
  <div style="color: teal; font-size: 30px">Enter OTP</div>
  <br/><br/>
  <form:form name = "signUpOTP" id="signUpOTP"  modelAttribute="form" method="post" onsubmit="return OnSubmitForm();">
   <center>
   <div style="white-space: nowrap" ><b>An OTP is sent to your email : ${form.map.email}</b></div><br/><br/>
   <table  width="700px" height="150px" cellspacing="10">
   <tr></tr>
   
    <tr></tr>
    <tr>
     <td style="white-space: nowrap"><form:label path="map['OTP']">OTP</form:label>
     </td>
     <td><form:input path="map['OTP']" />
     </td>
     <td><div class="alert alert-danger">${errorMessage}</div></td> 
    </tr>
   
    <tr> 
     <form:input type="hidden" path="map['username']" value="${form.map.username}"/>
     <td><input type="submit" onclick="document.pressed=this.value" value="Submit" /></td>
     </tr>
     <tr></tr>
     <tr></tr>
     <tr></tr>
      <tr> 
     <td><input type="submit" onclick="document.pressed=this.value" value="Create New OTP" /></td>
     </tr>
   </table>
   
   </center>
  </form:form>
  
 </center>
 
 <script type="text/javascript">
function OnSubmitForm()
{
  if(document.pressed == 'Submit')
  {
   document.signUpOTP.action ="enterSignupOTP";
  }
  else
  if(document.pressed == 'Create New OTP')
  {
    document.signUpOTP.action ="sendOTPAgain";
  }
  return true;
}
</script>

</body>
</html>

