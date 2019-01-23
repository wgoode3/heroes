<!doctype html>
<html lang="en">
<head>
	<title>Heroes</title>
	<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
	<h1>Heroes</h1>
	
	<fieldset>
		<legend>Register</legend>
		<form action="/register" method="post">
			<p>
				Username 
				<input type="text" name="username">
				<span>${errors.username}</span>
			</p>
			<p>Email <input type="text" name="email"></p>
			<p>Password<input type="password" name="password"></p>
			<p>Confirm <input type="password" name="confirm"></p>
			<input type="submit" value="Register">
		</form>
	</fieldset>
	
	<fieldset>
		<legend>Login</legend>
		<form action="/login" method="post">
			<p>Email <input type="text" name="email"></p>
			<p>Password<input type="password" name="password"></p>
			<input type="submit" value="Login">
		</form>
	</fieldset>
	
	<p>${errors}</p>
	
</body>
</html>