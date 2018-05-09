package com.ecare.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.ecare.dao.api.PlanDao;
import com.ecare.dao.model.Option;
import com.ecare.dao.model.Plan;

public class JpaPlanDao extends JpaGenericDao<Plan> implements PlanDao {

    public JpaPlanDao(EntityManager em) {
        super(em, Plan.class);
    }

    public List<Plan> findAll() {
        Query query = getEntityManager().createNamedQuery("findAllPlans", Plan.class);
        List<Plan> list = query.getResultList();
        return list;

    }

    public List<Plan> findNotArchival() {
        Query query = getEntityManager().createNamedQuery("findNotArchival", Plan.class);
        List<Plan> list = query.getResultList();
        return list;

    }
    
    public void updatePlan(Plan plan, List<Option> newAvailableOptions) {
        plan.setAvailableOptions(newAvailableOptions);
        update(plan);
    }

    public void createPlan(Plan plan, List<Option> newAvailableOptions) {
        plan.setAvailableOptions(newAvailableOptions);
        create(plan);
    }

    public boolean uniqueNamePlan(String name) {
        Query query = getEntityManager().createNamedQuery("findPlanByUniqueParameters", Plan.class);
        query.setParameter("name", name);
        List<Plan> list = query.getResultList();
        boolean result = false;
        if (list.size() == 0)
            result = true;
        else
            result = false;
        return result;
    }
}
