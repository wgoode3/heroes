<!doctype html>
<html lang="en">
<head>
	<title>Heroes</title>
	<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
</head>
<body>
	<h1>Heroes</h1>
	
	<a href="/logout">Logout</a>
	
	<table border="1">
        <tr>
            <th>Alias</th>
            <th>Real Name</th>
            <th>Description</th>
            <th>Abilities</th>
            <th>Nicknames</th>
        </tr>
        <c:forEach items="${heroes}" var="hero">
        <tr>
            <td>${hero.alias}</td>
            <td>${hero.realName}</td>
            <td>${hero.description}</td>
            <td>
            <c:forEach items="${hero.abilities}" var="a">
            	${a.name}, 
            </c:forEach>
            </td>
            <td>
            <c:forEach items="${hero.nicknames}" var="a">
            	${a.nickname}, 
            </c:forEach>
            </td>
        </tr>
        </c:forEach>
	</table>
	
	<form:form action="/hero" method="post" modelAttribute="hero">
	    <p>
	        <form:label path="alias">Alias</form:label>
	        <form:input path="alias"/>
	    </p>
	    <p>
	        <form:label path="realName">Real Name</form:label>
	        <form:input path="realName"/>
	    </p>
	    <p>
	        <form:label path="description">Description</form:label>
	        <form:textarea path="description"/>
	    </p>  
	    <input type="submit" value="Submit"/>
	</form:form> 
	
	<hr>
	
	<h2>Abilities</h2>
	
	<table border="1">
        <tr>
            <th>Name</th>
            <th>Description</th>
        </tr>
        <c:forEach items="${abilities}" var="ability">
        <tr>
            <td>${ability.name}</td>
            <td>${ability.description}</td>
        </tr>
        </c:forEach>
	</table>
	
	<form:form action="/ability" method="post" modelAttribute="ability">
	    <p>
	        <form:label path="name">Name</form:label>
	        <form:input path="name"/>
	    </p>
	    <p>
	        <form:label path="description">Description</form:label>
	        <form:textarea path="description"/>
	    </p>  
	    <input type="submit" value="Submit"/>
	</form:form> 
	   
	<hr>
	
	<h2>Add an ability</h2>
	
	<form action="/assignAbility" method="post">
		<p>
			Hero:
			<select name="hero_id">
				<c:forEach items="${heroes}" var="hero">
					<option value="${hero.id}">${hero.alias}</option>
       			</c:forEach>
			</select>
		</p>
		<p>
			Super Ability:
			<select name="ability_id">
				<c:forEach items="${abilities}" var="ability">
					<option value="${ability.id}">${ability.name}</option>
       			</c:forEach>
			</select>
		</p>
		<input type="submit" value="Submit"/>
	</form>
	
	<hr>
	
	<h2>Add a nickname</h2>
	
	<form action="/assignNickname" method="post">
		<p>
			Hero:
			<select name="hero_id">
				<c:forEach items="${heroes}" var="hero">
					<option value="${hero.id}">${hero.alias}</option>
       			</c:forEach>
			</select>
		</p>
		<p>
			Nickname: <input type="text" name="nickname">
	    </p>
		<input type="submit" value="Submit"/>
	</form>
	   
</body>
</html>