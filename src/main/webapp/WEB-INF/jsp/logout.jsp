<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
    uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
    <div id="error">
        <spring:message code="message.logoutError">    
        </spring:message>
    </div>
</c:if>
<c:if test="${param.logSucc == true}">
    <div id="success">
    <spring:message code="message.logoutSucc">    
        </spring:message>
    </div>
</c:if>
<html>
<head>
<title>Logged Out</title>
</head>
<body>    
    <a href="login.html">Login</a>
</body>
</html>