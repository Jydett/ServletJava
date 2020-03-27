package fr.polytech.jydet.td5.dao.loan;

import fr.polytech.jydet.td5.beans.Loan;
import fr.polytech.jydet.td5.beans.User;

import java.util.Collection;
import java.util.Optional;

public interface LoanDao {
    Collection<Loan> getLoansByUser(User user);

    void save(Loan l);

    Optional<Loan> findLoanById(long loanId);

    void remove(long loanId);

    Collection<Loan> getAll();
}
