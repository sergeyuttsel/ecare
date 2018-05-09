package com.ecare.util;

import com.ecare.dao.api.ContractDao;
import com.ecare.dao.api.OptionDao;
import com.ecare.dao.api.PlanDao;
import com.ecare.dao.api.UserDao;
import com.ecare.dao.jpa.JpaContractDao;
import com.ecare.dao.jpa.JpaOptionDao;
import com.ecare.dao.jpa.JpaPlanDao;
import com.ecare.dao.jpa.JpaUserDao;
import com.ecare.services.api.ContractService;
import com.ecare.services.api.OptionService;
import com.ecare.services.api.PlanService;
import com.ecare.services.api.UserService;
import com.ecare.services.impl.ContractServiceImpl;
import com.ecare.services.impl.OptionServiceImpl;
import com.ecare.services.impl.PlanServiceImpl;
import com.ecare.services.impl.UserServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationContext implements ServletContextListener {
    public static ApplicationContext INSTANCE;

    private static EntityManagerFactory EMFactory;
    
    private static EntityManager entityManager;

    private static OptionDao optionDao;

    private static PlanDao planDao;
    
    private static ContractDao contractDao;
    
    private static UserDao userDao;

    private static OptionService optionService;

    private static PlanService planService;
    
    private static ContractService contractService;
    
    private static UserService userService;

    //@Override
    public void contextInitialized(ServletContextEvent event) {
        EMFactory = Persistence.createEntityManagerFactory("ecarejpa");
        entityManager = EMFactory.createEntityManager();
    }

    //@Override
    public void contextDestroyed(ServletContextEvent event) {
        entityManager.close();
        EMFactory.close();
    }
    
    public static ApplicationContext getApplicationContext() {
        if (INSTANCE!=null) {
            return INSTANCE;
        } else {
            INSTANCE = new ApplicationContext();
            return INSTANCE;
        }
    }

    /*public ApplicationContext() {
        EMFactory = Persistence.createEntityManagerFactory("ecarejpa");
        entityManager = EMFactory.createEntityManager();
    }*/

    private static EntityManager getEntityManager() {
        return entityManager;
    }

    public static OptionDao getOptionDao() {
        if (optionDao != null) {
            return optionDao;
        } else {
            optionDao = new JpaOptionDao(getEntityManager());
            return optionDao;
        }
    }

    public static OptionService getOptionService() {
        if (optionService != null) {
            return optionService;
        } else {
            optionService = new OptionServiceImpl(getOptionDao(), getEntityManager());
            return optionService;
        }
    }

    public static PlanDao getPlanDao() {
        if (planDao != null) {
            return planDao;
        } else {
            planDao = new JpaPlanDao(getEntityManager());
            return planDao;
        }
    }

    public static PlanService getPlanService() {
        if (planService != null) {
            return planService;
        } else {
            planService = new PlanServiceImpl(getPlanDao(), getEntityManager());
            return planService;
        }
    }
    
    public static ContractDao getContractDao() {
        if (contractDao != null) {
            return contractDao;
        } else {
            contractDao = new JpaContractDao(getEntityManager());
            return contractDao;
        }
    }

    public static ContractService getContractService() {
        if (contractService != null) {
            return contractService;
        } else {
            contractService = new ContractServiceImpl(getContractDao(), getEntityManager());
            return contractService;
        }
    }
    
    public static UserDao getUserDao() {
        if (userDao != null) {
            return userDao;
        } else {
            userDao = new JpaUserDao(getEntityManager());
            return userDao;
        }
    }

    public static UserService getUserService() {
        if (userService != null) {
            return userService;
        } else {
            userService = new UserServiceImpl(getUserDao(), getEntityManager());
            return userService;
        }
    }
}
