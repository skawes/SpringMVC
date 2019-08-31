<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
</head>
<body>
<h1 align="center">Welcome to Registration Page</h1>
<form:form action="registerPlayer" method="Post" >
<table align="center"  >
<tr>
<td>Username:</td>
<td><input type="text" name="userId"></td>
</tr>
<tr>
<td>Password:</td>
<td><input type="text" name="userPassword"></td>
</tr>
<tr>
<td>Email-Id:</td>
<td><input type="text" name="emailId"></td>
</tr>
<tr>
<td>Age:</td>
<td><input type="text" name="age"></td>
</tr>
<tr>
<td>GroupName:</td>
<td><input type="text" name="groupName"></td>
</tr>
<tr><td colspan="2"><h2 align="center"><input type="submit" value="submit"></h2></td></tr>
</table>


</form:form>
</body>
</html>