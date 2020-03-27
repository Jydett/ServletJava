<%--
  Created by IntelliJ IDEA.
  User: Trombonesolo
  Date: 26/03/2020
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CreateLoan</title>
</head>
<body style="width: 50%;margin: auto; padding-top: 100px">
    <jsp:include page="login.jsp"/>
    <a href="/libraryHome">Retour</a>
    <%
        String error = (String) request.getAttribute("loanError");
        if (error != null) {
            out.println("<div style='color:red;'>" + error + "</div>");
        }
    %>

    <form method="post" action="/libraryLoan">
        <input type="hidden" name="bookId" value="<%out.print(request.getParameter("bookId"));%>">
        <input type="hidden" name="action" value="loan">
        <label for="loanTime">Combien de temps voulez vous louer ce livre ?</label><input name="loanTime" id="loanTime" type="number" min="0" max="30" value="1">
        <input type="submit" value="Louer !">
    </form>
</body>
</html>
