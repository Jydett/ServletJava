<%@ page import="fr.polytech.jydet.td5.beans.Book" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body style="width: 50%;margin: auto; padding-top: 100px">
    <jsp:include page="login.jsp"/>
    <%
        boolean connected = session.getAttribute("connected") != null;
        if (connected) {
            out.print("<a href=\"/libraryLoan\">Mes emprunts</a>");
        }
    %>

<%--    <a href="/td5/login.jsp">Login</a>--%>

    <jsp:include page="filters.jsp"/>

    <h2>Les livres de la bibliothèques :</h2>
<%--    <jsp:include page="../debug/debugger.jsp"/>--%>


    <%
        Object books = request.getAttribute("books");
        if (books != null && books instanceof List) {
            if (((List<Book>) books).isEmpty()) {
                out.println("Pas de résultat !");
            } else {
                for (Book b : (List<Book>) books) {
                    out.println("<form action='/libraryLoan' method='post'>");
                    out.print("<p><b>" +b.getGenre().name() + "</b> " + b.getTitle() + " <u>par " + b.getAuthor() + "</u> ("+ b.getInventoryItemCount() + " ex. dispo)</b>");
                    if (connected) {
                        if (b.getInventoryItemCount() > 0) {
                            out.println(" <button formaction='/libraryLoan?action=choose&bookId=" + b.getId() + "'>Emprunter ce livre</button>");
                        } else {
                            out.println(" <button disabled>Rupture de stock ! </button>");
                        }
                    }
                    out.print("</p></form>");
                }
            }
        }
    %>

    <jsp:include page="timeShifter.jsp"/>

</body>
</html>
