<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tableau 1</title>
</head>
<body>
    <table>
        <thead>
            <tr>
                <td>N° ordre</td>
                <td>Désignation</td>
            </tr>
        </thead>
        <tbody>
            <% for (int i=0; i<20; i++) { %>
            <tr><td><%= i %></td><td>bonjour</td></tr>
            <% }%>
        </tbody>
    </table>
</body>
</html>