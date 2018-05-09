package com.ecare.services.api;

import java.util.List;

import com.ecare.dao.model.Contract;
import com.ecare.dao.model.Option;
import com.ecare.dao.model.User;
import com.ecare.exception.DaoException;
import com.ecare.exception.InputException;

public interface ContractService {
    List<Contract> getAllContracts();
    Contract getContract(int idContract);
    void clearContractOptions(Contract contract);
    void updateContract(Contract contract) throws InputException, DaoException;
    public List<Option> getAvailableOptions(Contract contract);
    Contract getByPhoneNumber(String phoneNumber);
    void createContract(Contract contract) throws DaoException, InputException;
    void removeContract(Contract contract) throws DaoException, InputException;
}
