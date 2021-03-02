<%@ page import="com.Item"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
 //Insert item----------------------------------
if (request.getParameter("ItemCode") != null)
 {
 Item itemObj = new Item();
 String stsMsg = itemObj.insertItem(request.getParameter("ItemCode"),
 request.getParameter("ItemName"),
 request.getParameter("ItemPrice"),
 request.getParameter("ItemDesc"));
 session.setAttribute("statusMsg", stsMsg);
 }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items Management</title>
</head>
<body>
<h1>Items Management</h1>
<form method="post" action="items.jsp">
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
<%
 Item itemObj = new Item();
 out.print(itemObj.readItems());
%>
</body>
</html>
