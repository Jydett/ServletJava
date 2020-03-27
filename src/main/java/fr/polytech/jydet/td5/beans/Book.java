package fr.polytech.jydet.td5.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Embedded
    private BookGenre genre;
    private String author;
    private String title;
    private Integer inventoryItemCount;

    public Book(BookGenre genre, String author, String title, Integer inventoryItemCount) {
        this.genre = genre;
        this.author = author;
        this.title = title;
        this.inventoryItemCount = inventoryItemCount;
    }
}
