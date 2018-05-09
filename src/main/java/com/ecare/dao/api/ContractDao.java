package com.ecare.dao.api;

import java.util.List;

import com.ecare.dao.model.Contract;

public interface ContractDao extends GenericDao<Contract> {
    List<Contract> findAll();
    void clearContractOptions(Contract contract);
    Contract findByPhoneNumber(String phoneNumber);
    void createContract(Contract contract);
}