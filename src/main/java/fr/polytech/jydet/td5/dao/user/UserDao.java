package fr.polytech.jydet.td5.dao.user;

import fr.polytech.jydet.td5.beans.User;

import java.util.Collection;
import java.util.Optional;

public interface UserDao {
    void save(User user);

    Optional<User> getUserByName(String username);

    Collection<User> getAll();
}
