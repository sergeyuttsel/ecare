package com.ecare.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.ecare.dao.api.UserDao;
import com.ecare.dao.model.User;

public class JpaUserDao extends JpaGenericDao<User> implements UserDao {

    public JpaUserDao(EntityManager em) {
        super(em, User.class);
    }

    public List<User> findAll() {
        Query query = getEntityManager().createNamedQuery("findAllUsers", User.class);
        List<User> list = query.getResultList();
        return list;

    }
    
    public List<User> findAllClients() {
        Query query = getEntityManager().createNamedQuery("findAllClients", User.class);
        query.setParameter("role", User.Role.CLIENT);
        List<User> list = query.getResultList();
        return list;
    }
    
    public User findByEmail(String email) {
    	Query query = getEntityManager().createNamedQuery("findByEmail", User.class);
        query.setParameter("email", email);
        User user = (User) query.getSingleResult();
        return user;
    }
    
    public void updateUser(User user) {
        update(user);
    }

    public void createUser(User user) {
        create(user);
    }
}
