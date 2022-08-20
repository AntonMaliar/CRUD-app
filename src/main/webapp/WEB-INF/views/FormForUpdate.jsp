<%@page import="models.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update</title>
</head>
<body>
	<%User user = (User)request.getAttribute("user"); %>
	
	<form action="userProfile" method="post">
		<label for="name">name:</label>
		<input type="text" id="name" name="name" value="<%=user.getName()%>"><br>
		
		<label for="surname">surname:</label>
		<input type="text" id="surname" name="surname" value="<%=user.getSurname()%>"><br>
		
		<input type="hidden" name="id" value="<%=user.getId()%>">
		
		<input type="submit" value="update">
	</form>
</body>
</html>