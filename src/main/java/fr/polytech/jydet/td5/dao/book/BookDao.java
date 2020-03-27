package fr.polytech.jydet.td5.dao.book;

import fr.polytech.jydet.td5.beans.Book;

import java.util.Collection;
import java.util.Optional;

public interface BookDao {

    void save(Book b);

    Collection<Book> getAll();

    Optional<Book> findBookById(Long bookId);
}
