package com.ecare.dao.api;

import java.util.List;

import com.ecare.dao.model.User;

public interface UserDao extends GenericDao<User> {
    List<User> findAll();
    List<User> findAllClients();
    User findByEmail(String email);
    void updateUser(User user);
    void createUser(User user);
}