<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!-- Hello world JSP ${name} -->
<form method="post" action="login">
	Name: <input name="name" type="text"/>
	Password: <input name="password" type="password"/>
	<input type="submit"/>
</form>
<c:if test="${error}!=null">
<p style="color:red">${error}</p>
</c:if>
</body>
</html>