package fr.polytech.jydet.td2;

import fr.polytech.jydet.utils.ServletUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Définie dans le web.xml
 */
public class ConnectionServlet extends HttpServlet {

    private String pwd;
    private String login;

    @Override
    public void init(ServletConfig config) throws ServletException {
        login = config.getInitParameter("login");
        pwd = config.getInitParameter("password");
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletUtils.simpleTextResponse(resp, getForm());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("password");
        String login = req.getParameter("login");
        if (login == null || password == null) {
            ServletUtils.simpleTextResponse(resp, getFormWithMessage("Invalid form !"));
        } else if (this.login.equals(login) && this.pwd.equals(password)) {
            ServletUtils.simpleTextResponse(resp, "You are logged as " + login);
        } else {
            ServletUtils.simpleTextResponse(resp, getFormWithMessage("Invalid login !"));
        }
    }

    private String getFormWithMessage(String message) {
        return "<h1 style='color: red'>" + message + "</h1>" + getForm();
    }

    private String getForm() {
        return "<form action='' method='post'>\n" +
                "    <div><label for='login'>Login:</label><input type='text' name='login' id='login' required></div>\n" +
                "    <div><label for='password'>Password:</label><input type='password' name='password' id='password' required></div>\n" +
                "    <div><input type='submit' value='Se connecter'></div>\n" +
                "</form>";
    }
}
