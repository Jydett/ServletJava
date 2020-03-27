package fr.polytech.jydet.td5.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

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
}
