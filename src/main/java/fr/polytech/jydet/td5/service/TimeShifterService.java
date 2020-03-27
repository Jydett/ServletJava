package fr.polytech.jydet.td5.service;

import fr.polytech.jydet.td5.dao.loan.LoanDao;
import lombok.AllArgsConstructor;

import java.time.temporal.ChronoUnit;

@AllArgsConstructor
public class TimeShifterService {

    public LoanDao loanDao;

    public void shiftTime(int dayPassed) {
        loanDao.getAll().forEach(l -> l.setStartingInstant(
            l.getStartingInstant().minus(dayPassed, ChronoUnit.DAYS)
        ));
    }
}
