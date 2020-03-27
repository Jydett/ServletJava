<%@ page import="java.util.Random" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Resultat</title>
</head>
<body>
    <div>
        <%
        if (request.getParameter("number") == null) {
            out.println("Vous devez choisir un nombre !");
        } else {
            try {
                int win = new Random().nextInt(9) + 1;
                if (Integer.parseInt(request.getParameter("number")) == win) {
                    out.println("Gagné !");
                } else {
                    out.println("Perdu ! Le bon numéro était: " + win);
                }
            } catch (NumberFormatException e) {
                out.println("Ce n'est pas un nombre !");
            }
        }
        %>
    </div>
</body>
</html>
