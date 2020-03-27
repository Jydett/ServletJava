package fr.polytech.jydet.td5.service;

import fr.polytech.jydet.td5.beans.Book;
import fr.polytech.jydet.td5.beans.Loan;
import fr.polytech.jydet.td5.beans.User;
import fr.polytech.jydet.td5.dao.book.BookDao;
import fr.polytech.jydet.td5.dao.loan.LoanDao;
import fr.polytech.jydet.td5.exception.ServiceException;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
public class LoanService {

    private static final int MAX_LOAN = 5;
    private LoanDao loanDao;
    private BookDao bookDao;

    public Collection<Loan> findLoanByUser(User user) {
        return loanDao.getLoansByUser(user);
    }

    public int getLoanCountForUser(User user) {
        return findLoanByUser(user).size();
    }

    public void createLoan(Long bookId, User user, Integer duration) throws ServiceException {
        Optional<Book> optionalBook = bookDao.findBookById(bookId);
        if (! optionalBook.isPresent()) {
            throw new ServiceException("This book doesn't exist");
        }
        Book book = optionalBook.get();
        if (duration == null || duration < 0 || duration > 30) {
            throw new ServiceException("Invalid loan duration");
        }
        if (user == null) {
            throw new ServiceException("Invalid user");
        }
        if (getLoanCountForUser(user) >= MAX_LOAN) {
            throw new ServiceException("No more loan allowed");
        }
        Integer inventoryItemCount = book.getInventoryItemCount();
        if (inventoryItemCount < 1) {
            throw new ServiceException("There is no copy of this book available right now");
        }
        Loan l = new Loan(Instant.now(), duration, user, book);
        loanDao.save(l);
        book.setInventoryItemCount(inventoryItemCount - 1);
    }

    public void deleteLoan(User user, long loanId) throws ServiceException {
        Optional<Loan> optionalLoan = loanDao.findLoanById(loanId);
        if (! optionalLoan.isPresent()) {
            throw new ServiceException("This loan doesn't exist");
        }
        Loan l = optionalLoan.get();
        if (! Objects.equals(user, l.getUser())) {
            throw new ServiceException("You don't have any right on this loan");
        }
        loanDao.remove(loanId);
        l.getBook().setInventoryItemCount(l.getBook().getInventoryItemCount() + 1);
    }
}
