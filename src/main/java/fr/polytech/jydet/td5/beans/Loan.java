package fr.polytech.jydet.td5.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;

@Data
@Entity
@NoArgsConstructor
public class Loan {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Instant startingInstant;
    private Integer duration;
    @ManyToOne
    private User user;
    @ManyToOne
    private Book book;

    public Loan(Instant startingInstant, Integer duration, User user, Book book) {
        this.startingInstant = startingInstant;
        this.duration = duration;
        this.user = user;
        this.book = book;
    }

    public boolean isLate() {
        return startingInstant.plus(duration, ChronoUnit.DAYS).isBefore(Instant.now());
    }

    public String getFormattedDate() {
        return LocalDateTime.ofInstant(startingInstant, ZoneId.systemDefault()).toLocalDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
    }
}
