package com.ecare.dao.model;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "plans")
@NamedQueries({ @NamedQuery(name = "findAllPlans", query = "SELECT p FROM Plan p"),
        @NamedQuery(name = "findPlanByUniqueParameters", query = "SELECT p FROM Plan p WHERE p.name=:name"),
        @NamedQuery(name = "findNotArchival", query = "SELECT p FROM Plan p WHERE p.archival = FALSE") })
public class Plan extends GenericEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "archival", nullable = false)
    private boolean archival;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "available_options", joinColumns = @JoinColumn(name = "id_plan", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_option", referencedColumnName = "id"))
    private List<Option> availableOptions;

    @OneToMany(targetEntity = Contract.class, mappedBy = "plan", fetch = FetchType.LAZY)
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

    public boolean isArchival() {
        return archival;
    }

    public void setArchival(boolean archival) {
        this.archival = archival;
    }

    public List<Option> getAvailableOptions() {
        return availableOptions;
    }

    public void setAvailableOptions(List<Option> availableOptions) {
        this.availableOptions = availableOptions;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    @Override
    public String toString() {
        return "Plan [name=" + name + "]";
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
        if (!(obj instanceof Plan)) {
            return false;
        }
        Plan other = (Plan) obj;
        if (getId() != other.getId()) {
            return false;
        }
        return true;
    }

}
