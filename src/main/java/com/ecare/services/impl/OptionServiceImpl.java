package com.ecare.services.impl;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import com.ecare.dao.jpa.JpaOptionDao;
import com.ecare.dao.api.OptionDao;
import com.ecare.dao.model.Option;
import com.ecare.services.api.OptionService;
import com.ecare.util.ApplicationContext;
import com.ecare.exception.DaoException;
import com.ecare.exception.InputException;

public class OptionServiceImpl implements OptionService {

    private OptionDao optionDao;
    private EntityManager entityManager;

    public OptionServiceImpl(OptionDao jpaod, EntityManager em) {
        optionDao = jpaod;
        entityManager = em;
    }

    public List<Option> getAllOptions() {
        return optionDao.findAll();
    }

    public Option getOption(int idOption) {
        return optionDao.find(idOption);
    }

    public void createOption(Option option) throws DaoException {
        try {
            entityManager.getTransaction().begin();
            optionDao.create(option);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new DaoException();
        }
    }

    public void updateOption(Option option, List<Option> newRequiredOptions, List<Option> newIncompatibleOptions)
            throws DaoException, InputException {
        try {
            entityManager.getTransaction().begin();
            for (Option iOption : newRequiredOptions) {
                boolean compatibility = newIncompatibleOptions.contains(iOption);
                if (compatibility == true) {
                    throw new InputException();
                }
            }
            optionDao.updateOption(option, newRequiredOptions, newIncompatibleOptions);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new DaoException();
        }
    }

    public void createOption(Option option, List<Option> newRequiredOptions, List<Option> newIncompatibleOptions)
            throws DaoException, InputException {
        try {
            entityManager.getTransaction().begin();
            if (optionDao.uniqueNameOption(option.getName()) == false)
                throw new InputException();
            for (Option iOption : newRequiredOptions) {
                boolean compatibility = newIncompatibleOptions.contains(iOption);
                if (compatibility == true) {
                    throw new InputException();
                }
            }
            optionDao.createOption(option, newRequiredOptions, newIncompatibleOptions);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new DaoException();
        }
    }
}
