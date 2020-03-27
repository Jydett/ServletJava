<%@ page import="fr.polytech.jydet.td5.beans.BookGenre" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.Arrays" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <%
        Object error = request.getAttribute("error");
        if (error != null) {
            out.println("<div style:color=red;>Erreur: " + error + "</div>");
        }
    %>
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
            <%
                String[] oldParams = request.getParameterValues("genre");
                HashSet<String> selectedGenre;
                if (oldParams != null) {
                    selectedGenre = new HashSet<>(Arrays.asList(oldParams));
                } else {
                    selectedGenre = new HashSet<>();
                }
                for (BookGenre value : BookGenre.values()) {
                    out.println("<option name='"+ value.name() + "' " +(selectedGenre.contains(value.name()) ? "selected" : "") + ">" + value.name() + "</option>");
                }
            %>
        </select>
        <div>
            <label for="title-search">Titre (Expression régulière):
                <span class="tooltip">?
                <span class="tooltiptext">Art.* -> Arthur, Arthus</span>
            </span>
            </label>
            <input type="text" id="title-search" name="title"
                <%
                String title = request.getParameter("title");
                if (title != null && !title.isEmpty())
                    out.println("value='" + title + "'");
            %>
            >
        </div>
        <div>
            <label for="author-search">Auteur (Expression régulière):
                <span class="tooltip">?
                <span class="tooltiptext">Art.* -> Arthur, Arthus</span>
            </span>
            </label>
            <input type="text" id="author-search" name="author"
                <%
                    String author = request.getParameter("author");
                    if (author != null && !author.isEmpty())
                        out.println("value='" + author + "'");
                %>
            >
        </div>
        <div>
            <label for="onlyAvailable">Uniquement les livres disponible</label>
            <input type="checkbox" id="onlyAvailable" name="onlyAvailable"
                <%
                    if ("on".equals(request.getParameter("onlyAvailable")))
                        out.println("checked");
                %>
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