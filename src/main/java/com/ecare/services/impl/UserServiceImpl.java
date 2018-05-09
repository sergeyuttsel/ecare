package com.ecare.services.impl;

import java.util.List;

import javax.persistence.EntityManager;

import com.ecare.dao.api.UserDao;
import com.ecare.dao.model.Option;
import com.ecare.dao.model.Plan;
import com.ecare.dao.model.User;
import com.ecare.exception.DaoException;
import com.ecare.exception.InputException;
import com.ecare.services.api.UserService;

public class UserServiceImpl implements UserService {
    
    private UserDao userDao;
    private EntityManager entityManager;
    
    public UserServiceImpl(UserDao jpaud, EntityManager em) {
        userDao = jpaud;
        entityManager = em;
    }
    
    public User getUser(int idUser) {
        return userDao.find(idUser);
    }
    
    public User getByEmail(String email) {
        return userDao.findByEmail(email);
    }
    
    public List<User> getAllUsers() {
        return userDao.findAll();
         
    }
    
    public List<User> getAllClients() {
        return userDao.findAllClients();
    }
    
    public boolean checkPassword(User user, String password) {
        return user.getPassword().equals(password);
    }
    
    public void updateUser(User user) throws DaoException, InputException {
        try {
            entityManager.getTransaction().begin();
            
            userDao.updateUser(user);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new DaoException();
        }
    }

    public void createUser(User user)
            throws DaoException, InputException {
        try {
            entityManager.getTransaction().begin();
            userDao.createUser(user);
            
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new DaoException();
        }
    }
    
    
    
    
}
