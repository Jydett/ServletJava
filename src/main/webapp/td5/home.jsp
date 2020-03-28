<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Home</title>
</head>
<body style="width: 50%;margin: auto; padding-top: 100px">
    <jsp:include page="login.jsp"/>
    
    <c:if test="${sessionScope.connected != null}">
        <a href="/libraryLoan">Mes emprunts</a>
    </c:if>

    <jsp:include page="filters.jsp"/>

    <h2>Les livres de la bibliothèques :</h2>

    <c:if test="${requestScope.books != null}">
        <jsp:useBean id="books" type="java.util.List" scope="request"/>
        <c:choose>
            <c:when test="${empty books}">
                Pas de résultat !
            </c:when>
            <c:otherwise>
                <form action="/libraryLoan" method="post">
                    <c:forEach items="${books}" var="book">
                        <p><b>${book.genre}</b> ${book.title} <u>par ${book.author}</u> (${book.inventoryItemCount} ex. dispo)</b>
                            <c:if test="${sessionScope.connected != null}">
                                <c:choose>
                                    <c:when test="${book.inventoryItemCount > 0}">
                                        <button formaction='/libraryLoan?action=choose&bookId=${book.id}'>Emprunter ce livre</button>
                                    </c:when>
                                    <c:otherwise>
                                        <button disabled>Rupture de stock ! </button>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>
                        </p>
                    </c:forEach>
                </form>
            </c:otherwise>
        </c:choose>
    </c:if>
    <br/>
    <jsp:include page="timeShifter.jsp"/>

</body>
</html>
