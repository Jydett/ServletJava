package fr.polytech.jydet.td5.service;

import fr.polytech.jydet.td5.beans.User;
import fr.polytech.jydet.td5.dao.user.UserDao;
import fr.polytech.jydet.td5.exception.ServiceException;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class UserService {
    public UserDao userDao;

    public User authenticate(String login, String password) throws ServiceException {
        if (login == null || password == null) {
            throw new ServiceException("Invalid form");
        }
        Optional<User> optionalUser = userDao.getUserByName(login);
        if (! optionalUser.isPresent()) {
            throw new ServiceException("Unknown user");
        }
        User u = optionalUser.get();
        if (! password.equals(u.getPassword())) {
            throw new ServiceException("Incorrect password");
        }
        return u;
    }
}
