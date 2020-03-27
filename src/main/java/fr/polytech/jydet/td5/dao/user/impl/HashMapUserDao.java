package fr.polytech.jydet.td5.dao.user.impl;

import fr.polytech.jydet.td5.beans.User;
import fr.polytech.jydet.td5.dao.user.UserDao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

public class HashMapUserDao implements UserDao {

    public final HashMap<String, User> userTable = new HashMap<>();

    @Override
    public void save(User user) {
        userTable.put(user.getUsername(), user);
    }

    @Override
    public Optional<User> getUserByName(String username) {
       return Optional.ofNullable(userTable.get(username));
    }

    @Override
    public Collection<User> getAll() {
        return userTable.values();
    }
}
