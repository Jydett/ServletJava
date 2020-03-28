<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Mes emprunts</title>
</head>
<body style="width: 50%;margin: auto; padding-top: 100px">
    <jsp:include page="login.jsp"/>
    <a href="/td5/home.jsp">Retour</a>
    <h2>Mes emprunts</h2>
    <div>

    <c:if test="${requestScope.loans != null}">
        <c:choose>
            <c:when test="${empty requestScope}">
                Pas de prêt en cours !
            </c:when>
            <c:otherwise>
                <jsp:useBean id="loans" type="java.util.List" scope="request"/>
                <form method='post'>
                    <c:forEach var="l" items="${loans}">
                        <c:choose>
                            <c:when test="${l.isLate()}">
                                <p style='color:red;'>[EN RETARD]
                            </c:when>
                            <c:otherwise>
                                <p>
                            </c:otherwise>
                        </c:choose>
                        <b>${l.book.title}</b> Loué depuis le ${l.formattedDate} pour
                        <c:choose>
                            <c:when test="${l.duration} == 1">
                                1 jour
                            </c:when>
                            <c:otherwise>
                                ${l.duration} jours
                            </c:otherwise>
                        </c:choose>
                        <button formaction='/libraryLoan?action=deleteLoan&loanId=${l.id}'>Rendre</button><p></p>
                    </c:forEach>
                </form>
            </c:otherwise>
        </c:choose>
    </c:if>
    </div>
</body>
</html>
