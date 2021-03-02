<%@ page import="com.Item"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items Management</title>
</head>
<body>
<h1>Items Management</h1>

<form method="post" action="Items.jsp">
 Item code: <input name="ItemCode" type="text"><br> Item
 name: <input name="ItemName" type="text"><br> Item price:
 <input name="ItemPrice" type="text"><br> Item
 description: <input name="ItemDesc" type="text"><br> <input
 name="btnSubmit" type="submit" value="Save">
</form>
<%
 out.print(session.getAttribute("statusMsg"));
%>
<br>

</body>
</html>
