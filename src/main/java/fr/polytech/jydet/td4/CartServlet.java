package fr.polytech.jydet.td4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "CartView",
        urlPatterns = "/cartView"
)
public class CartServlet extends HttpServlet {

    public CartServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { resp.sendRedirect("/ProductChooser_");
        String cart = req.getParameter("cart");
        if (cart == null || cart.isEmpty()) {
//            req.setAttribute("auteur", auteur);
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/bonjour.jsp").forward(req, resp);
    }
}
