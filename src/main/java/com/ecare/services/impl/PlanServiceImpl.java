package com.ecare.services.impl;

import java.util.List;

import javax.persistence.EntityManager;

import com.ecare.dao.api.PlanDao;
import com.ecare.dao.model.Option;
import com.ecare.dao.model.Plan;
import com.ecare.services.api.OptionService;
import com.ecare.services.api.PlanService;
import com.ecare.exception.DaoException;
import com.ecare.exception.InputException;
import com.ecare.util.ApplicationContext;
import com.ecare.exception.InputException;

public class PlanServiceImpl implements PlanService {

    private PlanDao planDao;
    private EntityManager entityManager;

    public PlanServiceImpl(PlanDao jpapd, EntityManager em) {
        planDao = jpapd;
        entityManager = em;
    }

    public List<Plan> getAllPlans() {
        return planDao.findAll();

    }

    public List<Plan> getNotArchival() {
        return planDao.findNotArchival();

    }
    
    public Plan getPlan(int idPlan) {
        return planDao.find(idPlan);
    }

    public void updatePlan(Plan plan, List<Option> newAvailableOptions) throws DaoException, InputException {
        try {
            entityManager.getTransaction().begin();
            /*for (Option iOption : newAvailableOptions) {
                List<Option> iOptionIncompatibleList = iOption.getIncompatibleOptions();
                for (Option iTargetOption : newAvailableOptions) {
                    if (iOptionIncompatibleList.contains(iTargetOption)) {
                        throw new InputException();
                    }
                }
            }*/
            if (isOptionsCompatible(newAvailableOptions) == false) throw new InputException();
            plan.setAvailableOptions(newAvailableOptions);
            planDao.update(plan);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new DaoException();
        }
    }

    public boolean isOptionsCompatible(List<Option> listOptions) {
    	for (Option iOption : listOptions) {
            List<Option> iOptionIncompatibleList = iOption.getIncompatibleOptions();
            for (Option iTargetOption : listOptions) {
                if (iOptionIncompatibleList.contains(iTargetOption)) {
                    return false;
                }
            }
        }
    	return true;
    }
    
    public void createPlan(Plan plan, List<Option> newAvailableOptions)
            throws DaoException, InputException {
        try {
            entityManager.getTransaction().begin();
            if (planDao.uniqueNamePlan(plan.getName()) == false)
                throw new InputException();
            /*for (Option iOption : newAvailableOptions) {
                List<Option> iOptionIncompatibleList = iOption.getIncompatibleOptions();
                for (Option iTargetOption : newAvailableOptions) {
                    if (iOptionIncompatibleList.contains(iTargetOption)) {
                        throw new InputException();
                    }
                }
            }*/
            if (isOptionsCompatible(newAvailableOptions) == false) throw new InputException();
            plan.setAvailableOptions(newAvailableOptions);
            planDao.create(plan);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new DaoException();
        }
    }
}
