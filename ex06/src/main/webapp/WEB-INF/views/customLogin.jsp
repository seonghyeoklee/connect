<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>Custom Login Page</h2>
	<h3>${error }</h3>
	<h3>${logout }</h3>
	
	<form action="/login" method="post">
		<div>
			<input type="text" name="username" value="admin">
		</div>
		
		<div>
			<input type="password" name="password" value="admin">
		</div>
		
		<div>
			<input type="checkbox" name="remember-me">Remember Me
		</div>
		
		<div>
			<input type="submit">
		</div>
		<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
	</form>

</body>
</html>