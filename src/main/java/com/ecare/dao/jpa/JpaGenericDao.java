package com.ecare.dao.jpa;

import com.ecare.dao.api.GenericDao;
import com.ecare.dao.model.GenericEntity;
import com.ecare.dao.model.Plan;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public abstract class JpaGenericDao<T extends GenericEntity> implements GenericDao<T> {
    
    private EntityManager entityManager;
    
    private Class<T> persistentClass;
    
    JpaGenericDao(EntityManager em, Class<T> persistentClass){
        entityManager = em;
        this.persistentClass = persistentClass;
    }
    
    protected EntityManager getEntityManager() {
        return entityManager;
    }
    
    public T create(T entity) {
        getEntityManager().persist(entity);
        
        return entity;
    }
    public T update(T entity) {
        getEntityManager().merge(entity);
        return entity;
    }
    
    public void delete(T entity) {
        getEntityManager().remove(entity);
    }
    
    public T find(int id) {
        T entity = getEntityManager().find(this.persistentClass, id);
        return entity;
    }
    
    
}
