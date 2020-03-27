<%@ page import="fr.polytech.jydet.td5.beans.Loan" %>
<%@ page import="java.time.Instant" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.ZoneId" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.format.FormatStyle" %>
<%@ page import="java.time.temporal.ChronoUnit" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mes emprunts</title>
</head>
<body style="width: 50%;margin: auto; padding-top: 100px">
    <jsp:include page="login.jsp"/>
    <a href="/libraryHome">Retour</a>
    <h2>Mes emprunts</h2>
    <div>


    <%
        Object loans = request.getAttribute("loans");
        if (loans != null && loans instanceof List) {
            if (((List<Loan>) loans).isEmpty()) {
                out.println("Pas de prêt en cours !");
            } else {
                Instant now = Instant.now();
                out.print("<form method='post'>");
                for (Loan l : (List<Loan>) loans) {
                    boolean late = now.isAfter(l.getStartingInstant().plus(l.getDuration(), ChronoUnit.DAYS));
                    if (late) {
                        out.print("<p style='color:red;'>[EN RETARD] ");
                    } else {
                        out.print("<p>");
                    }
                    out.print("<b>" + l.getBook().getTitle() + "</b> Loué depuis le " + LocalDateTime.ofInstant(l.getStartingInstant(), ZoneId.systemDefault()).toLocalDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)) + " pour ");
                    if (l.getDuration() == 1) {
                        out.print("1 jour");
                    } else {
                        out.print(l.getDuration() + " jours");
                    }
                    out.print(" <button formaction='/libraryLoan?action=deleteLoan&loanId=" + l.getId() + "'>Rendre</button><p>");
                }
                out.print("</form>");
            }
        }
    %>
    </div>
</body>
</html>
