package fr.polytech.jydet.td5.dao.loan.impl;

import fr.polytech.jydet.td5.beans.Loan;
import fr.polytech.jydet.td5.beans.User;
import fr.polytech.jydet.td5.dao.loan.LoanDao;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collection;
import java.util.Optional;

public class HibernateLoanDao implements LoanDao {

    private final Session hibernateSession;

    public HibernateLoanDao(Session hibernateSession) {
        this.hibernateSession = hibernateSession;
    }

    @Override
    public Collection<Loan> getLoansByUser(User user) {
        return hibernateSession.createQuery("select l from Loan l where l.user = :user", Loan.class)
            .setParameter("user", user)
            .getResultList();
    }

    @Override
    public void save(Loan l) {
        Transaction transaction = hibernateSession.beginTransaction();
        hibernateSession.save(l);
        transaction.commit();
    }

    @Override
    public Optional<Loan> findLoanById(long loanId) {
        return Optional.ofNullable(hibernateSession.get(Loan.class, loanId));
    }

    @Override
    public void remove(long loanId) {
        findLoanById(loanId).ifPresent(l -> {
            Transaction transaction = hibernateSession.beginTransaction();
            hibernateSession.remove(l);
            transaction.commit();
        });
    }

    @Override
    public Collection<Loan> getAll() {
        return hibernateSession.createQuery("select l from Loan l", Loan.class).getResultList();
    }
}
