<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
</head>

<body>
<header>
<table border="1" width="100%">
<tr>
<th><a href="/MyIPL1/home">Home</a></th>
<th><a href="/MyIPL1/leaderboard">Leaderboard</a></th>
<th><a href="/MyIPL1/fixtures">Fixtures</a></th>
<th><a href="/MyIPL1/logout">logout</a></th>
<th><a href="/MyIPL1/aboutus">About us</a></th>
</tr>
</table>
</header>
<br>
<br>
<%-- <h1>Welcome to myIpl ${player.getUserId()}</h1> --%>
<table border="1" align="center">
<tr>
<th>Team1</th>
<th>Team2</th>
<th>Date</th>
</tr>
<c:forEach items="${fixtures}" var="list">
<tr>
<td>${list.getTeam1()}</td>
<td>${list.getTeam2()}</td>
<td>${list.getDate()}</td>
</tr>
</c:forEach>

</table>


</body>
</html>