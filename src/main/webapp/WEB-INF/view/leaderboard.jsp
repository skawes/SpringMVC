<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<th><a href="/MyIPL1/aboutus">About us</a></th>
</tr>
</table>
</header>
<h1>Welcome to leaderboard ${player.getUserId()} age ${player.getAge()}</h1>

</body>
</html>