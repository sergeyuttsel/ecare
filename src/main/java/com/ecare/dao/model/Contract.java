package com.ecare.dao.model;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "contracts")
@NamedQueries({ @NamedQuery(name = "findAllContracts", query = "SELECT c FROM Contract c"),
		@NamedQuery(name = "findByPhoneNumber", query = "SELECT c FROM Contract c WHERE c.phoneNumber = :phoneNumber") })
public class Contract extends GenericEntity {

	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;

	@ManyToOne(targetEntity = Plan.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_plan")
	private Plan plan;

	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user")
	private User user;

	@Column(name = "client_block", nullable = false)
	private boolean clientBlock;

	@Column(name = "company_block", nullable = false)
	private boolean companyBlock;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "contract_options", joinColumns = @JoinColumn(name = "id_contract", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_added_option", referencedColumnName = "id"))
	private List<Option> contractOptions;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isClientBlock() {
		return clientBlock;
	}

	public void setClientBlock(boolean clientBlock) {
		this.clientBlock = clientBlock;
	}

	public boolean isCompanyBlock() {
		return companyBlock;
	}

	public void setEmployeeBlock(boolean companyBlock) {
		this.companyBlock = companyBlock;
	}

	public List<Option> getContractOptions() {
		return contractOptions;
	}

	public void setContractOptions(List<Option> contractOptions) {
		this.contractOptions = contractOptions;
	}

	@Override
	public String toString() {
		return "Contract [phoneNumber=" + phoneNumber + "]";
	}

	@Override
	public int hashCode() {
		return getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Contract)) {
			return false;
		}
		Contract other = (Contract) obj;
		if (getId() != other.getId()) {
			return false;
		}
		return true;
	}

}
