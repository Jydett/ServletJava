package fr.polytech.jydet.td3;

import fr.polytech.jydet.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "LoginServlet",
        urlPatterns = "/loginHeader"
)
public class LoginServlet extends HttpServlet {

    private static final String REALM = "BASIC realm=\"test\"";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (AuthentificationHelper.authorized(req.getHeader("Authorization"))) {
            ServletUtils.simpleTextResponse(resp, "<div>TOP SECRET</div>");
        } else {
            resp.setContentType("text/html");
            resp.setHeader("WWW-Authenticate", REALM);
            resp.sendError(resp.SC_UNAUTHORIZED);
        }
    }
}
