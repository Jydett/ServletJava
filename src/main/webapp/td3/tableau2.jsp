<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tableau 2</title>
</head>
<body>
    <table>
        <tbody>
            <% for (int i = 0; i < 10; i++) { %>
            <tr><td><%= i %></td><td><%= i*i %></td></tr>
            <% }%>
        </tbody>
    </table>
</body>
</html>