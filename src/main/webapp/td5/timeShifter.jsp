<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div style="background-color: bisque; padding: 10px; -webkit-box-shadow: 10px 10px 5px 0px rgba(0,0,0,0.75);-moz-box-shadow: 10px 10px 5px 0px rgba(0,0,0,0.75);box-shadow: 10px 10px 5px 0px rgba(0,0,0,0.75);">
    <%
        String status = request.getParameter("TSStatus");
        if (status != null) {
            out.println("<div style='color:green;'>" + status + "</div>");
        }
    %>

    <%
        String error = request.getParameter("TSError");
        if (error != null) {
            out.println("<div style='color:red;'>" + error + "</div>");
        }
    %>

    <p>Pour simuler le temps qui passe, ce formulaire permet de faire reculer la date de tous les prêts de l'application du nombre de jour indiqués.</p>
    <form method="post" action="/libraryTimeShifter">
        <label for="dayPassed">Nombre de jour(s): </label><input type="number" id="dayPassed" name="dayPassed" value="0">
        <input type="submit" value="Faire reculer les prêts !">
    </form>
</div>