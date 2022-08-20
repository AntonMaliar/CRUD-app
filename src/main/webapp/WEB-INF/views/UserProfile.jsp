<%@page import="models.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Profile</title>
</head>
<body>
	<% User user = (User)request.getAttribute("user");%>
	
	<p>User name:<%=user.getName()%>
	</p>
	<p>User surname:<%=user.getSurname()%>
	</p>
	
	<a href="userProfile?action=update&id=<%=user.getId()%>">Update</a>
	<br>
	<a href="userProfile?action=delete&id=<%=user.getId()%>">Delete</a>
</body>
</html>
