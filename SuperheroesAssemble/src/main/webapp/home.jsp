<%@page import="com.app.controllers.HomeController"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*" %>
<html>

<head>
<style>

 form{
    padding: 100px;
    text-align: center;
    margin: auto;
    display: table;
    font-size: 25px;
  }
  #characterSelectedByUser{
    width:150px;
  }

</style>
</head>
<body  style="background-color:#f1dfc7;">

	<%! List heroesList = new ArrayList(); %>
	<%  heroesList = new HomeController().getAllHeroNamesInAList(); %>
	
	<form action="inputCharacter" method="POST">
	Please Select any Character : <br/> <br/>
	<select name="characterSelectedByUser">
   <%  for(int i = 0; i < heroesList.size(); i++) {
           String option = (String) heroesList.get(i);
   %>
   <option value="<%= option %>"><%= option %></option>
   <% } %>
   
   </select>
   <br/> <br/>
   <input type="submit">
   
   </form>
   
</select>
</body>
</html>