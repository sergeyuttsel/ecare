package com.ecare.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.NoResultException;

import com.ecare.dao.api.ContractDao;
import com.ecare.dao.model.Contract;
import com.ecare.dao.model.User;

public class JpaContractDao extends JpaGenericDao<Contract> implements ContractDao {

    public JpaContractDao(EntityManager em) {
        super(em, Contract.class);
    }

    public List<Contract> findAll() {
        Query query = getEntityManager().createNamedQuery("findAllContracts", Contract.class);
        List<Contract> list = query.getResultList();
        return list;

    }
    
    public Contract findByPhoneNumber(String phoneNumber) {
    	Query query = getEntityManager().createNamedQuery("findByPhoneNumber", Contract.class);
        query.setParameter("phoneNumber", phoneNumber);
        Contract contract;
        try {
        contract = (Contract) query.getSingleResult();
        }
        catch (NoResultException ex) {
        	contract = null;
        }
        //Object ob = query.getSingleResult();
        //if (ob.equals(null) == true) return null;
        //else return (Contract) ob;
        
        return contract;
    }

	public void clearContractOptions(Contract contract) {
		Contract contractWoOptions = find(contract.getId());
		contractWoOptions.getContractOptions().clear();
		update(contractWoOptions);
		
	}
	
	public void createContract(Contract contract) {
        create(contract);
        EntityManager em = getEntityManager();
        em.refresh(contract.getUser());
    }
    
}
