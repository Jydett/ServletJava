package fr.polytech.jydet.td5.dao.book.impl;

import fr.polytech.jydet.td5.beans.Book;
import fr.polytech.jydet.td5.dao.book.BookDao;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collection;
import java.util.Optional;

public class HibernateBookDao implements BookDao {

    private final Session hibernateSession;

    public HibernateBookDao(Session hibernateSession) {
        this.hibernateSession = hibernateSession;
    }

    @Override
    public void save(Book b) {
        Transaction transaction = hibernateSession.beginTransaction();
        hibernateSession.save(b);
        transaction.commit();
    }

    @Override
    public Collection<Book> getAll() {
        return hibernateSession.createQuery("select b from Book b", Book.class).getResultList();
    }

    @Override
    public Optional<Book> findBookById(Long bookId) {
        return Optional.ofNullable(hibernateSession.get(Book.class, bookId));
    }
}
