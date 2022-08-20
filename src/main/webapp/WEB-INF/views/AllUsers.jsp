<%@page import="models.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All users</title>
	<style type="text/css">
		table, th, td {  
  			border: 2px solid black;  
  			border-collapse: collapse;  
		}
		th, td {  
  			padding: 10px;  
		}   
	</style>
</head>
<body>
	<%!public String url (int id){return "<a href='userProfile?id="+id+"'>Open</a>";}%>
	<% List<User> list = (List<User>) request.getAttribute("listUsers"); %>
	<table>
		<tr> 
			<th>Name</th>
			<th>Surname</th>
			<th>Option</th>  
		</tr>
	<% for(User user:list){ %>
		<tr>
			<td><%=user.getName()%></td>
			<td><%=user.getSurname()%></td>
			<td><%=url(user.getId())%></td> 
		</tr>
	<% } %>
	</table>
	
	<br>
	<a href="index.jsp">Welcome page</a>
</body>
</html>