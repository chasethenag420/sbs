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
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="web_resources/theme/css/bootstrap.css" rel="stylesheet">
<link href="web_resources/theme/css/bootstrap-responsive.css" rel="stylesheet">
<title>
<spring:message code="title.transfer"></spring:message>
</title>
</head>
<body>
 <center>
  <br/><br/>
  <div style="color: teal; font-size: 30px">Transfer Amount</div>
  <br/><br/>
  <form:form id="transferForm"  method="post" modelAttribute="form">
   <table width="700px" height="150px">
   <tr>
     <td style="white-space: nowrap"><form:label path="fromAccount">From Account Number</form:label>
     </td>
     <td><form:input path="fromAccount" />
     </td>
     <td><form:errors class="alert alert-danger" path="fromAccount" /></td>
    </tr>
    <tr>
     <td style="white-space: nowrap"><form:label path="toAccount">To Account Number</form:label>
     </td>
     <td><form:input path="toAccount" />
     </td>
     <td><form:errors class="alert alert-danger" path="toAccount" /></td>
    </tr>
    <tr>
     <td style="white-space: nowrap"><form:label path="amount">Amount</form:label>
     </td>
     <td><form:input path="amount" />
     </td>
     <td><form:errors class="alert alert-danger" path="amount" /></td>
    </tr>
    <tr>
     <td style="white-space: nowrap"><form:label path="description">Transfer Description</form:label>
     </td>
     <td><form:input path="description" />
     </td>
    </tr>
    <tr> 
     <form:input type="hidden" path="transactionType" value="transfer"/>
     <td><input type="submit" name ="transferAmount" value="Submit" /></td>
     <td><input type="submit" name ="goBack" value="Cancel" /></td>
    </tr>
   </table>
  </form:form>
 </center>
</body>
</html>

