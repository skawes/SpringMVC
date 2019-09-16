<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<style type="text/css">
.error{
color: red;
}
</style>
</head>
<body>
	<h1 align="center">Welcome to Registration Page</h1>
	<form:form action="registerPlayer" method="Post"
		modelAttribute="player">
		<table align="center">
			<tr>
				<form:input path="id" type="hidden" />
				<td>Username:</td>
				<td><form:input type="text" path="userId" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><form:input type="password" path="userPassword" /></td>
			</tr>
			<tr>
				<td>Email-Id:</td>
				<td><form:input type="text" path="emailId" /></td>
			</tr>
			<tr>
				<td>Age:</td>
				<td><form:input type="text" path="age" /><form:errors path="age" class="error"></form:errors></td>
				
			</tr>
			<tr>
				<td>GroupName:</td>
				<td><form:input type="text" path="groupName" /></td>
			</tr>
			<tr>
				<td colspan="2"><h2 align="center">
						<input type="submit" value="submit">
					</h2></td>
			</tr>
		</table>


	</form:form>
</body>
</html>