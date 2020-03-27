package fr.polytech.jydet.td5;

import fr.polytech.jydet.td5.exception.ServiceException;
import fr.polytech.jydet.td5.initializer.ControllerInitializer;
import fr.polytech.jydet.td5.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "Login",
        urlPatterns = "/libraryLogin"
)
public class LoginController extends HttpServlet {

    private UserService userService;

    @Override
    public void init() {
        userService = ControllerInitializer.getUserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/td5/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("logout".equals(action)) {
            req.getSession().removeAttribute("connected");
        } else {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            try {
                req.getSession().setAttribute("connected", userService.authenticate(username, password));
                resp.sendRedirect("libraryLoan");
                return;
            } catch (ServiceException e) {
                req.setAttribute("loginError", e.getError());
            }
        }
        req.getRequestDispatcher("/td5/home.jsp").forward(req, resp);
    }
}
