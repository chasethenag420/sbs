<%@page import = "java.io.*"%>
<%
String a = System.getProperty("file.encoding");
String b = new java.io.OutputStreamWriter(new java.io.ByteArrayOutputStream()).getEncoding();
%>
Encoding 1 : "<%=a.toString()%>" <BR>
Encoding 2 : "<%=b.toString()%>" <BR>

