<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<div style="background-color: bisque; padding: 10px; -webkit-box-shadow: 10px 10px 5px 0px rgba(0,0,0,0.75);-moz-box-shadow: 10px 10px 5px 0px rgba(0,0,0,0.75);box-shadow: 10px 10px 5px 0px rgba(0,0,0,0.75);">

    <c:if test="${not empty paramValues.TSStatus}">
        <div style="color: green;">${paramValues.TSStatus[0]}</div>
    </c:if>

    <c:if test="${not empty paramValues.TSError}">
        <div style="color: red;">${paramValues.TSError[0]}</div>
    </c:if>

    <p>Pour simuler le temps qui passe, ce formulaire permet de faire reculer la date de tous les prêts de l'application du nombre de jour indiqués.</p>
    <form method="post" action="/libraryTimeShifter">
        <label for="dayPassed">Nombre de jour(s): </label><input type="number" id="dayPassed" name="dayPassed" value="0">
        <input type="submit" value="Faire reculer les prêts !">
    </form>
</div>