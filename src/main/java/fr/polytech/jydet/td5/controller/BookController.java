package fr.polytech.jydet.td5.controller;

import fr.polytech.jydet.td5.beans.Book;
import fr.polytech.jydet.td5.beans.BookGenre;
import fr.polytech.jydet.td5.exception.ServiceException;
import fr.polytech.jydet.td5.initializer.ControllerInitializer;
import fr.polytech.jydet.td5.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@WebServlet(
        name = "BookController",
        urlPatterns = "/libraryHome"
)
public class BookController extends HttpServlet {

    public BookService bookService;

    @Override
    public void init() {
        bookService = ControllerInitializer.getBookService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String[] genres = req.getParameterValues("genre");
            Set<BookGenre> selectedGenre = null;
            if (genres != null && genres.length != 0) {
                selectedGenre = new HashSet<>();
                for (String genre : genres) {
                    BookGenre bookGenre;
                    try {
                        bookGenre = BookGenre.valueOf(genre);
                    } catch (IllegalArgumentException | NullPointerException e) {
                        req.setAttribute("error", "Invalid genre");
                        getServletContext().getRequestDispatcher("/td5/home.jsp").forward(req, resp);
                        return;
                    }
                    selectedGenre.add(bookGenre);
                }
            }
            boolean onlyAvailable = "on".equals(req.getParameter("onlyAvailable"));
            Collection<Book> publicBooks = bookService.getPublicBooks(req.getParameter("author"), req.getParameter("title"), selectedGenre, onlyAvailable);
            req.setAttribute("books", new ArrayList<>(publicBooks));
        } catch (ServiceException e) {
            req.setAttribute("error", e.getError());
        }
        getServletContext().getRequestDispatcher("/td5/home.jsp").forward(req, resp);
    }
}
