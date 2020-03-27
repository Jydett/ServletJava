<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fr.polytech.jydet.td5.beans.User" %>
<div>
    <%
        Object user = session.getAttribute("connected");
        if (user == null) {
    %>

    <%
        String error = (String) request.getAttribute("loginError");
        if (error != null) {
            out.println("<div style='color:red;'>" + error + "</div>");
        }
    %>
    <form method="post" action="/libraryLogin">
        <label for="username">Nom d'utilisateur: </label><input type="text" name="username" id="username"/>
        <label for="password">Mot de passe: </label><input type="password" name="password" id="password"/>
        <input type="submit" value="Se connecter !"/>
    </form>

    <%
        } else {
            String base = "<span> Connecté en tant qu";
            String username = ((User) user).getUsername();
            if ("aeiouhy".indexOf(username.charAt(0)) >= 0) {
                base = base + '\'';
            } else {
                base = base + "e ";
            }
            out.println(base + "<b>" + username + "</b>");
    %>
        <form style="display: inline" method="post" action="/libraryLogin">
            <input type="hidden" name="action" value="logout"/>
            <input type="submit" value="Se déconnecter"/>
        </form>
    </span>
    <%
        }
    %>
</div>