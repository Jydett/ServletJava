package fr.polytech.jydet.td5;

import fr.polytech.jydet.td5.beans.User;
import fr.polytech.jydet.td5.exception.ServiceException;
import fr.polytech.jydet.td5.initializer.ControllerInitializer;
import fr.polytech.jydet.td5.service.LoanService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "Loan",
        urlPatterns = "/libraryLoan"
)
public class LoanController extends HttpServlet {

    private LoanService loanService;

    @Override
    public void init() {
        loanService = ControllerInitializer.getLoanService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User connected = (User) req.getSession().getAttribute("connected");
        req.setAttribute("loans", loanService.findLoanByUser(connected));
        req.getRequestDispatcher("/td5/loan.jsp").forward(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String loanIdStr = req.getParameter("loanId");
        try {
            long loanId = Long.parseLong(loanIdStr);
            User user = (User) req.getSession().getAttribute("connected");
            loanService.deleteLoan(user, loanId);
        } catch (NumberFormatException e) {
            //TODO faire qq chose
        } catch (ServiceException e) {
            //TODO faire qq chose
        }
        resp.sendRedirect("/libraryLoan");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("choose".equals(action)) {
            req.getRequestDispatcher("/td5/createLoan.jsp").forward(req, resp);
            return;
        }
        if ("deleteLoan".equals(action)) {
            doDelete(req, resp);
            return;
        }
        if ("loan".equals(action)) {
            String bookIdStr = req.getParameter("bookId");
            String loanTimeStr = req.getParameter("loanTime");
            //TODO verifier ca
            int loanTime;
            long bookId;
            try {
                bookId = Long.parseLong(bookIdStr);
            } catch (NumberFormatException e) {
                req.setAttribute("loanError", "Incorrect book id");
                req.getRequestDispatcher("/td5/createLoan.jsp").forward(req, resp);
                return;
            }
            try {
                loanTime = Integer.parseInt(loanTimeStr);
            } catch (NumberFormatException e) {
                req.setAttribute("loanError", "Incorrect loan time");
                req.getRequestDispatcher("/td5/createLoan.jsp").forward(req, resp);
                return;
            }
            User user = (User) req.getSession().getAttribute("connected");
            try {
                loanService.createLoan(bookId, user, loanTime);
            } catch (ServiceException e) {
                req.setAttribute("loanError", e.getError());
                req.getRequestDispatcher("/td5/createLoan.jsp").forward(req, resp);
                return;
            }
            req.getRequestDispatcher("/libraryHome").forward(req, resp);
            return;
        }
        getServletContext().getRequestDispatcher("/libraryHome").forward(req, resp);
    }
}
