package com.ecare.dao.model;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "options")
@NamedQueries(
        {
                @NamedQuery(name = "findAllOptions", query = "SELECT o FROM Option o"),
                @NamedQuery(name = "findOptionByUniqueParameters", query = "SELECT o FROM Option o WHERE o.name=:name")
        })
public class Option extends GenericEntity{

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private float price;

    @Column(name = "price_connect", nullable = false)
    private float priceConnect;

    @Column(nullable = false)
    private boolean archival;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "required_options", 
    joinColumns = @JoinColumn(name = "id_option"),
    inverseJoinColumns = @JoinColumn(name = "id_target_option"))
    private List<Option> requiredOptions;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "incompatible_options",
    joinColumns = @JoinColumn(name = "id_option"),
    inverseJoinColumns = @JoinColumn(name = "id_target_option"))
    private List<Option> incompatibleOptions;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "available_options",
            joinColumns = @JoinColumn(name = "id_option", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_plan", referencedColumnName = "id"))
    private List<Plan> availablePlans;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "contract_options",
            joinColumns = @JoinColumn(name = "id_added_option", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_contract", referencedColumnName = "id"))
    private List<Contract> contracts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPriceConnect() {
        return priceConnect;
    }

    public void setPriceConnect(float priceConnect) {
        this.priceConnect = priceConnect;
    }

    public boolean isArchival() {
        return archival;
    }

    public void setArchival(boolean archival) {
        this.archival = archival;
    }

    public List<Option> getRequiredOptions() {
        return requiredOptions;
    }

    public void setRequiredOptions(List<Option> requiredOptions) {
        this.requiredOptions = requiredOptions;
    }

    public List<Option> getIncompatibleOptions() {
        return incompatibleOptions;
    }

    public void setIncompatibleOptions(List<Option> incompatibleOptions) {
        this.incompatibleOptions = incompatibleOptions;
    }

    public List<Plan> getAvailablePlans() {
        return availablePlans;
    }

    public void setAvailablePlans(List<Plan> availablePlans) {
        this.availablePlans = availablePlans;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    @Override
    public String toString() {
        return "Option [name=" + name + "]";
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
        if (!(obj instanceof Option)) {
            return false;
        }
        Option other = (Option) obj;
        if (getId() != other.getId()) {
            return false;
        }
        return true;
    }

}
