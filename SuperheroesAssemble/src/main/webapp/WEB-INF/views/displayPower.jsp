<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%@page isELIgnored="false"%>
<style>
p {
	padding: 100px;
	text-align: center;
	margin: auto;
	font-size: 25px;
}
</style>
</head>
<body  style="background-color:#f1dfc7;">

	<p>
		Latest power level of
		<%=request.getAttribute("selectedHero")%>
		is
		<%=request.getAttribute("currentPowerOfHero")%>
	</p>

	<form action="home.jsp" align="center">
		<input type="submit" value="Back To Home Page">
	</form>

</body>
</html>