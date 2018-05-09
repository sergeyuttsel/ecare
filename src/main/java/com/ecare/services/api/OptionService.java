package com.ecare.services.api;

import java.util.List;

import com.ecare.dao.model.Option;
import com.ecare.exception.DaoException;
import com.ecare.exception.InputException;

public interface OptionService {
    List<Option> getAllOptions();
    public Option getOption(int idOption);
    void createOption(Option option) throws DaoException;
    void updateOption(Option option, List<Option> newRequiredOptions, List<Option> newIncompatibleOptions) throws InputException, DaoException;
    void createOption(Option option, List<Option> newRequiredOptions, List<Option> newIncompatibleOptions) throws InputException, DaoException;
    
}
