<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="fr.polytech.jydet.td5.beans.BookGenre" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<div>
    <c:if test="${requestScope.error != null}">
        <div style="color: red;">Erreur: ${requestScope.error}</div>
    </c:if>

    <form action="/libraryHome">
        <label for="genre-chooser">Choisisser un ou plusieurs genres :
            <span class="tooltip">?
                <span class="tooltiptext">
                    Ctrl + Clique Gauche pour séléctionner plusieurs genres
                    <br>
                    Le genre des résultats sera parmis ceux séléctionné
                </span>
            </span></label>
        <select name="genre" id="genre-chooser" multiple>
            <option name="none" disabled>-- Choisisez un ou plusieurs genre --</option>
            <c:set var="allGenre" value="<%= BookGenre.values() %>"/>
            <c:choose>
                <c:when test="${empty paramValues.genre}">
                    <c:forEach items="${allGenre}" var="value">
                        <option name="${value}"> ${value} </option>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:set var="oldGenre" value="${fn:join(paramValues.genre, '')}"/>
                    <c:forEach items="${allGenre}" var="value">
                        <option name="${value}" ${fn:contains(oldGenre, value.name) ? 'selected' : ''}> ${value} </option>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </select>
        <div>
            <label for="title-search">Titre (Expression régulière):
                <span class="tooltip">?
                <span class="tooltiptext">Art.* -> Arthur, Arthus</span>
            </span>
            </label>
            <input type="text" id="title-search" name="title"
                <c:if test="${not empty paramValues.title}">
                    value='${paramValues.title[0]}'
                </c:if>
            >
        </div>
        <div>
            <label for="author-search">Auteur (Expression régulière):
                <span class="tooltip">?
                <span class="tooltiptext">Art.* -> Arthur, Arthus</span>
            </span>
            </label>
            <input type="text" id="author-search" name="author"
                <c:if test="${not empty paramValues.author}">
                    value='${paramValues.author[0]}'
                </c:if>
            >
        </div>
        <div>
            <label for="onlyAvailable">Uniquement les livres disponible</label>
            <input type="checkbox" id="onlyAvailable" name="onlyAvailable"
                <c:if test="${not empty paramValues.onlyAvailable}">
                    <c:if test="${'on' == paramValues.onlyAvailable[0]}">
                        checked
                    </c:if>
                </c:if>
            >
        </div>
        <div><input type="submit" value="Search !"></div>
    </form>
</div>

<style>
    /* Credit : Tooltip code from https://www.w3schools.com/howto/howto_css_tooltip.asp */
    /* Tooltip container */
    .tooltip {
        position: relative;
        display: inline-block;
        border-bottom: 1px dotted black; /* If you want dots under the hoverable text */
    }

    /* Tooltip text */
    .tooltip .tooltiptext {
        visibility: hidden;
        width: 120px;
        background-color: #555;
        color: #fff;
        text-align: center;
        padding: 5px 0;
        border-radius: 6px;

        /* Position the tooltip text */
        position: absolute;
        z-index: 1;
        bottom: 125%;
        left: 50%;
        margin-left: -60px;

        /* Fade in tooltip */
        opacity: 0;
        transition: opacity 0.3s;
    }

    /* Tooltip arrow */
    .tooltip .tooltiptext::after {
        content: "";
        position: absolute;
        top: 100%;
        left: 50%;
        margin-left: -5px;
        border-width: 5px;
        border-style: solid;
        border-color: #555 transparent transparent transparent;
    }

    /* Show the tooltip text when you mouse over the tooltip container */
    .tooltip:hover .tooltiptext {
        visibility: visible;
        opacity: 1;
    }
</style>