package com.ecare.services.impl;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.EntityManager;

import com.ecare.dao.api.ContractDao;
import com.ecare.dao.api.UserDao;
import com.ecare.dao.model.Contract;
import com.ecare.dao.model.Option;
import com.ecare.dao.model.Plan;
import com.ecare.dao.model.User;
import com.ecare.exception.DaoException;
import com.ecare.exception.InputException;
import com.ecare.services.api.ContractService;

public class ContractServiceImpl implements ContractService {

	private ContractDao contractDao;
	private EntityManager entityManager;

	public ContractServiceImpl(ContractDao jpacd, EntityManager em) {
		contractDao = jpacd;
		entityManager = em;
	}

	public List<Contract> getAllContracts() {
		return contractDao.findAll();
	}

	public Contract getContract(int idContract) {
		return contractDao.find(idContract);
	}
	
	public Contract getByPhoneNumber(String phoneNumber) {
        return contractDao.findByPhoneNumber(phoneNumber);
    }

	public List<Option> getAvailableOptions(Contract contract) {
		List<Option> availableContractOptions = contract.getPlan().getAvailableOptions();
		
		for (Option i : contract.getContractOptions()) {
			if (availableContractOptions.contains(i) == false) {
				availableContractOptions.add(i);
			}
		}
		
		return availableContractOptions;

	}
	
	public void clearContractOptions(Contract contract) {
		contractDao.clearContractOptions(contract);
	}
	
	public void updateContract(Contract contract) throws DaoException, InputException {
        try {
            entityManager.getTransaction().begin();
            
            contractDao.update(contract);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new DaoException();
        }
    }
	
	public void createContract(Contract contract)
            throws DaoException, InputException {
        try {
            entityManager.getTransaction().begin();
            contractDao.createContract(contract);
            
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new DaoException();
        }
    }
	
	public void removeContract(Contract contract)
            throws DaoException, InputException {
        try {
            entityManager.getTransaction().begin();
            User user = contract.getUser();
            user.getContracts().remove(contract);
            contractDao.delete(contract);
            
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new DaoException();
        }
    }
	
}
