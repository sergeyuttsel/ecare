package com.ecare.dao.api;

import java.util.List;

import com.ecare.dao.model.Option;
import com.ecare.dao.model.Plan;

public interface PlanDao extends GenericDao<Plan> {
    List<Plan> findAll();
    List<Plan> findNotArchival();
    void updatePlan(Plan plan, List<Option> newAvailableOptions);
    void createPlan(Plan plan, List<Option> newAvailableOptions);
    boolean uniqueNamePlan(String name);
}