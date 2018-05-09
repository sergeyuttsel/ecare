package com.ecare.dao.api;

import com.ecare.dao.model.Option;
import java.util.List;

public interface OptionDao extends GenericDao<Option> {
    List<Option> findAll();
    void updateOption(Option option, List<Option> newRequiredOptions, List<Option> newIncompatibleOptions);
    void createOption(Option option, List<Option> newRequiredOptions, List<Option> newIncompatibleOptions);
    boolean uniqueNameOption(String name);
}
