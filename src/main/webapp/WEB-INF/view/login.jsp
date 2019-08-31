<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<body>
<h2 align="center">Welcome to MyIpl Game Login Page</h2>
<form:form action="validation" method="POST" >
<h4 align="center">LoginId:<input type="text" name="userId"></h4>
<h4 align="center">Password:<input type="text" name="userPassword"></h4>
<h4 align="center"><input type="submit" value="login"></h4>
<p align="center">New User?<a href="register">Register</a></p>

</form:form>
</body>
</html>