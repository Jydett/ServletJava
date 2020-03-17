package fr.polytech.jydet.td1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
    name = "DemoServlet",
    description = "This is my first annotated servlet",
    urlPatterns = "/hello"
)
public class MyServlet extends HttpServlet {

    public void service (HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        String prenom = req.getParameter("prenom");
        if (prenom == null) {
            out.println("<html><head></head><body>Hail 2 U!</body></html>");
        } else {
            out.println("<html><head></head><body>Hail 2 U " + prenom + " </body></html>");
        }
    }
}