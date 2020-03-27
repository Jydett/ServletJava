<%--
  Created by IntelliJ IDEA.
  User: Trombonesolo
  Date: 22/03/2020
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Game</title>
</head>
<body>
    <form action="/gameResult" method="get">
        <input type="number" min="1" max="10" name="number"/>
        <input type="submit" value="Jouer !"/>
    </form>
</body>
</html>
