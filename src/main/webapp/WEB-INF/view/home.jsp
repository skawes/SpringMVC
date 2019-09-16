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
<th><a href="/MyIPL1/aboutus">About us</a></th>
</tr>
</table>
</header>
<h1>Welcome to myIpl ${player.getUserId()}</h1>
<br>
   <br>

<c:if test="${predict.size()==2}">
<c:if test="${pred.getMatch1()==null}">
  <a href="/MyIPL1/getPrediction1?val=${predict.get(0).getTeam1()}"> <input type="button" value="${predict.get(0).getTeam1()}"> </a> Vs <a href="/MyIPL1/getPrediction1?val=${predict.get(0).getTeam2()}"><input type="button" value="${predict.get(0).getTeam2()}"></a>
   </c:if>
   <c:if test="${pred.getMatch2()==null}">
   <a href="/MyIPL1/getPrediction2?val=${predict.get(1).getTeam1()}">  <input type="button" value="${predict.get(1).getTeam1()}"> </a> Vs <a href="/MyIPL1/getPrediction2?val=${predict.get(1).getTeam2()}"><input type="button" value="${predict.get(1).getTeam2()}"></a>
   </c:if>
</c:if>
<c:if test="${predict.size()==1}">
  <c:if test="${pred.getMatch1()==null}">
 <a href="/MyIPL1/getPrediction1?val=${predict.get(0).getTeam1()}">  <input type="button" value="${predict.get(0).getTeam1()}"> </a> Vs <a href="/MyIPL1/getPrediction1?val=${predict.get(0).getTeam2()}"><input type="button" value="${predict.get(0).getTeam2()}"></a>
</c:if>
</c:if>
   
   <%-- 
   <c:if test="${pred.getMatch1()==null}">
  <a>  <input type="button" value="${predict.get(0).getMatch1()}"> </a> Vs <input type="button" value="RCB">
   </c:if>
   <c:if test="${pred.getMatch2()==null}">
    <input type="button" value="${pred.getMatch1()}">  Vs <input type="button" value="RCB">
   </c:if> --%>
  
</body>
</html>