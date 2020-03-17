package fr.polytech.jydet.td2;

import fr.polytech.jydet.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "FormServlet",
        description = "A servlet to deliver a form",
        urlPatterns = "/form"
)
public class FormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html>\n" +
                "    " +
                "<head></head>\n" +
                "    " +
                "<body style='background-color: #ccff66'>\n" +
                "    " +
                "<form action=\"\" method=\"post\">\n" +
                "        <div\n" +
                "            <div><input type=\"radio\" name=\"article\" id=\"crayon\" value='crayon'><label for='crayon'>CRAYON</label></div>\n" +
                "            <div><input type=\"radio\" name=\"article\" id=\"gomme\" value='gomme'><label for='gomme'>GOMME</label></div>\n" +
                "            <div><input type=\"radio\" name=\"article\" id=\"cahier\" value='cahier'><label for='cahier'>CAHIER</label></div>\n" +
                "            <div><input type=\"radio\" name=\"article\" id=\"regle\" value='regle'><label for='regle'>REGLE</label></div>\n" +
                "        </div>\n" +
                "        <div>\n" +
                "            <input type=\"submit\" value=\"Choisir un article\">\n" +
                "        </div>\n" +
                "    " +
                "</form>" +
                "    \n" +
                "    " +
                "</body>\n" +
                "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String article = req.getParameter("article");
        if (article != null) {
            ServletUtils.simpleTextResponse(resp, "Vous avez choisis l'article: " + article);
        } else {
            ServletUtils.simpleTextResponse(resp, "Vous n'avez pas choisis d'article !");
        }
    }

    public void destroy() {
        super.destroy();
    }
}
