<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-Frame-Options" content="allow">
<title>Secure Bank System | Credit</title>
</head>
<body>
 <center>

  <div style="color: teal; font-size: 30px">Secure Bank System |
   Credit</div>
  <form:form id="creditForm"  method="post" modelAttribute="form"
   action="creditAmount">
   <table width="400px" height="150px">
    <tr>
     <td><form:label path="map['toAccountNumber']">To Account Number</form:label>
     </td>
     <td><form:input path="map['toAccountNumber']" />
     </td>
    </tr>
    <tr>
     <td><form:label path="map['amount']">Amount</form:label>
     </td>
     <td><form:input path="map['amount']" />
     </td>
    </tr>
    <tr>
     <td><form:label path="map['transferDescription']">Transfer Description</form:label>
     </td>
     <td><form:input path="map['transferDescription']" />
     </td>
    </tr>
    <tr> 
     <td><input type="submit" value="Submit" /></td>
     <td><input type="button" value="Cancel" /></td>
    </tr>
   </table>
  </form:form>
  
 </center>
</body>
</html>

