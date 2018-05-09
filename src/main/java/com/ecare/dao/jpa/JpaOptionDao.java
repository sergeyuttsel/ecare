package com.ecare.dao.jpa;

import com.ecare.dao.api.OptionDao;
import com.ecare.dao.model.Option;
import com.ecare.dao.model.Plan;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class JpaOptionDao extends JpaGenericDao<Option> implements OptionDao {

    public JpaOptionDao(EntityManager em) {
        super(em, Option.class);
    }

    public List<Option> findAll() {
        Query query = getEntityManager().createNamedQuery("findAllOptions", Option.class);
        List<Option> list = query.getResultList();
        return list;
    }

    public void updateOption(Option option, List<Option> newRequiredOptions, List<Option> newIncompatibleOptions) {
        for (Option iOption : findAll()) {
            if (iOption.equals(option)) {
                continue;
            }
            List<Option> incompatibleOptions = iOption.getIncompatibleOptions();
            incompatibleOptions.remove(option);
            update(iOption);
        }
        option.setIncompatibleOptions(newIncompatibleOptions);
        option.setRequiredOptions(newRequiredOptions);
        for (Option iOption : option.getIncompatibleOptions()) {
            if (iOption.equals(option)) {
                continue;
            }
            iOption.getIncompatibleOptions().add(option);
            update(iOption);
        }
        update(option);
    }
    
    public void createOption(Option option, List<Option> newRequiredOptions, List<Option> newIncompatibleOptions) {
        option.setIncompatibleOptions(newIncompatibleOptions);
        option.setRequiredOptions(newRequiredOptions);
        create(option);
        for (Option iOption : option.getIncompatibleOptions()) {
            if (iOption.equals(option)) {
                continue;
            }
            iOption.getIncompatibleOptions().add(option);
            update(iOption);
        }
    }

    public boolean uniqueNameOption(String name) {
        Query query = getEntityManager().createNamedQuery("findOptionByUniqueParameters", Option.class);
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
