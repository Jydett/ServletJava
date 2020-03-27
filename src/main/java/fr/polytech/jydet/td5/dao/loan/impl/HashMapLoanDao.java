package fr.polytech.jydet.td5.dao.loan.impl;

import fr.polytech.jydet.td5.beans.Loan;
import fr.polytech.jydet.td5.beans.User;
import fr.polytech.jydet.td5.dao.loan.LoanDao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class HashMapLoanDao implements LoanDao {

    private final AtomicLong loanCounter = new AtomicLong(0L);
    public final HashMap<Long, Loan> loanTable = new HashMap<>();

    @Override
    public Collection<Loan> getLoansByUser(User user) {
        return loanTable.values().stream().filter(l -> l.getUser().equals(user)).collect(Collectors.toList());
    }

    @Override
    public void save(Loan l) {
        if (l.getId() == null) {
            l.setId(loanCounter.incrementAndGet());
        }
        loanTable.put(l.getId(), l);
    }

    @Override
    public Optional<Loan> findLoanById(long loanId) {
        return Optional.ofNullable(loanTable.get(loanId));
    }

    @Override
    public void remove(long loanId) {
        loanTable.remove(loanId);
    }

    @Override
    public Collection<Loan> getAll() {
        return loanTable.values();
    }
}
