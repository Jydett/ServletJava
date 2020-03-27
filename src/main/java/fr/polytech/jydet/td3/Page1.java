package fr.polytech.jydet.td3;

import fr.polytech.jydet.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "page1",
        urlPatterns = "/page1"
)
public class Page1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addCookie(new Cookie("page1Visited", "true"));
        ServletUtils.simpleTextResponse(resp, "<div>Voici la page1</div><div><a href='/page2'>Vers la page 2</a></div>");
    }
}
