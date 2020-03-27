package fr.polytech.jydet.td5.initializer;

import fr.polytech.jydet.td5.beans.Book;
import fr.polytech.jydet.td5.beans.BookGenre;
import fr.polytech.jydet.td5.beans.Loan;
import fr.polytech.jydet.td5.beans.User;
import fr.polytech.jydet.td5.dao.book.BookDao;
import fr.polytech.jydet.td5.dao.book.impl.HashMapBookDao;
import fr.polytech.jydet.td5.dao.book.impl.HibernateBookDao;
import fr.polytech.jydet.td5.dao.loan.LoanDao;
import fr.polytech.jydet.td5.dao.loan.impl.HashMapLoanDao;
import fr.polytech.jydet.td5.dao.loan.impl.HibernateLoanDao;
import fr.polytech.jydet.td5.dao.user.UserDao;
import fr.polytech.jydet.td5.dao.user.impl.HashMapUserDao;
import fr.polytech.jydet.td5.dao.user.impl.HibernateUserDao;
import fr.polytech.jydet.td5.service.BookService;
import fr.polytech.jydet.td5.service.LoanService;
import fr.polytech.jydet.td5.service.TimeShifterService;
import fr.polytech.jydet.td5.service.UserService;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class ControllerInitializer {

    public enum DateBaseImpl {
        HASHMAP, MYSQL
    }

    private static final DateBaseImpl INITIALIZER_TYPE = DateBaseImpl.MYSQL;

    private static final BookDao bookDao;
    private static final UserDao userDao;
    private static final LoanDao loanDao;

    static {
        switch (INITIALIZER_TYPE) {
            case MYSQL: {
                Session hibernateSession = new Configuration().configure().buildSessionFactory().openSession();
                userDao = new HibernateUserDao(hibernateSession);
                bookDao = new HibernateBookDao(hibernateSession);
                loanDao = new HibernateLoanDao(hibernateSession);
                break;
            }
            case HASHMAP: {
                bookDao = new HashMapBookDao();
                userDao = new HashMapUserDao();
                loanDao = new HashMapLoanDao();
                break;
            }
            default: {
                bookDao = null;
                userDao = null;
                loanDao = null;
            }
        }
        fillTables();
    }

    private static void fillTables() {
        if (bookDao.getAll().isEmpty()) {
            bookDao.save(new Book(BookGenre.SF, "Arthur Pelloux-Prayer", "Journal du bord du confinement", 3));
            bookDao.save(new Book(BookGenre.POLICIER, "Jean Dujardin", "L'enquête de la bergerie", 0));
            bookDao.save(new Book(BookGenre.THRILLER, "Mano Soulat", "Le mangeur de sausig", 1));
            bookDao.save(new Book(BookGenre.AVENTURE, "Jobert Dutrois", "Le donjon maléfique (bouh)", 5));
        }
        if (userDao.getAll().isEmpty()) {
            userDao.save(new User("admin", "admin"));
        }
        if (loanDao.getAll().isEmpty()) {
            Book book = bookDao.getAll().stream().findFirst().get();
            User user = userDao.getAll().stream().findFirst().get();
            loanDao.save(new Loan(Instant.now().minus(7, ChronoUnit.DAYS), 5, user, book));
            loanDao.save(new Loan(Instant.now().minus(3, ChronoUnit.DAYS), 7, user, book));
        }
    }

    public static BookService getBookService() {
        return new BookService(bookDao);
    }

    public static UserService getUserService() {
        return new UserService(userDao);
    }

    public static LoanService getLoanService() {
        return new LoanService(loanDao, bookDao);
    }


    public static TimeShifterService getTimeShifterService() {
        return new TimeShifterService(loanDao);
    }
}
