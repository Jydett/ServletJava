<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>CreateLoan</title>
</head>
<body style="width: 50%;margin: auto; padding-top: 100px">
    <jsp:include page="login.jsp"/>
    <a href="/libraryHome">Retour</a>

    <c:if test="${not empty paramValues.loanError}">
        <div style="color: red;">${requestScope.loanError[0]}</div>
    </c:if>

    <form method="post" action="/libraryLoan">
        <input type="hidden" name="bookId" value="${paramValues.bookId[0]}">
        <input type="hidden" name="action" value="loan">
        <label for="loanTime">Combien de temps voulez vous louer ce livre ? </label><input name="loanTime" id="loanTime" type="number" min="0" max="30" value="1">
        <input type="submit" value="Louer !">
    </form>
</body>
</html>
