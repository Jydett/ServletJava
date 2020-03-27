package fr.polytech.jydet.td5.dao.book.impl;

import fr.polytech.jydet.td5.beans.Book;
import fr.polytech.jydet.td5.dao.book.BookDao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class HashMapBookDao implements BookDao {

    public final HashMap<Long, Book> bookTable = new HashMap<>();
    public final AtomicLong idCounter = new AtomicLong(0);

    @Override
    public void save(Book b) {
        if (b.getId() == null) {
            b.setId(idCounter.incrementAndGet());
        }
        bookTable.put(b.getId(), b);
    }

    @Override
    public Collection<Book> getAll() {
        return bookTable.values();
    }

    @Override
    public Optional<Book> findBookById(Long bookId) {
        return Optional.ofNullable(bookTable.get(bookId));
    }
}
