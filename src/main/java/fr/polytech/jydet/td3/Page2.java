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
        name = "page2",
        urlPatterns = "/page2"
)
public class Page2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if ("page1Visited".equals(cookie.getName())) {
                ServletUtils.simpleTextResponse(resp, "<div>Voici la page2</div><div><a href='/page1'>Vers la page 1</a></div>");
                return;
            }
        }
        req.getRequestDispatcher("page1").forward(req, resp);
    }
}
