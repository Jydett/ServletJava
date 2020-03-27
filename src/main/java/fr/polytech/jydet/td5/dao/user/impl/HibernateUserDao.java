package fr.polytech.jydet.td5.dao.user.impl;

import fr.polytech.jydet.td5.beans.User;
import fr.polytech.jydet.td5.dao.user.UserDao;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collection;
import java.util.Optional;

public class HibernateUserDao implements UserDao {

    private final Session hibernateSession;

    public HibernateUserDao(Session hibernateSession) {
        this.hibernateSession = hibernateSession;
    }

    @Override
    public void save(User user) {
        Transaction transaction = hibernateSession.beginTransaction();
        hibernateSession.save(user);
        transaction.commit();
    }

    @Override
    public Optional<User> getUserByName(String username) {
        return hibernateSession.createQuery("select u from User u where u.username = :username", User.class)
                .setParameter("username", username)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public Collection<User> getAll() {
        return hibernateSession.createQuery("select u from User u", User.class).getResultList();
    }
}
