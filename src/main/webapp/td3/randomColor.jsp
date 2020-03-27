<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Random" %>
<html>
<head>
    <title>Random Color</title>
</head>
<% Random r = new Random(); %>
<body style="background-color: rgb(<%= r.nextInt(256) %>, <%= r.nextInt(256) %>, <%= r.nextInt(256) %>)">

</body>
</html>