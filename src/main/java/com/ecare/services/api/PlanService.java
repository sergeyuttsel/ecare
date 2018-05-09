package com.ecare.services.api;

import java.util.List;

import com.ecare.dao.model.Option;
import com.ecare.dao.model.Plan;
import com.ecare.exception.DaoException;
import com.ecare.exception.InputException;

public interface PlanService {
    List<Plan> getAllPlans();
    Plan getPlan(int idPlan);
    void updatePlan(Plan plan, List<Option> newAvailableOptions) throws InputException, DaoException;
    void createPlan(Plan plan, List<Option> newAvailableOptions) throws InputException, DaoException;
    List<Plan> getNotArchival();
    public boolean isOptionsCompatible(List<Option> listOptions);
}
