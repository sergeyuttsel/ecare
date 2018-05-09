package com.ecare.services.api;

import java.util.List;

import com.ecare.dao.model.User;
import com.ecare.exception.DaoException;
import com.ecare.exception.InputException;

public interface UserService {
	User getUser(int idUser);
    List<User> getAllUsers();
    List<User> getAllClients();
    public User getByEmail(String email);
    boolean checkPassword(User user, String password);
    void updateUser(User user) throws InputException, DaoException;
    void createUser(User user) throws InputException, DaoException;

}
