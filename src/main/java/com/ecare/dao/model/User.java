package com.ecare.dao.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NamedQueries({ @NamedQuery(name = "findAllUsers", query = "SELECT u FROM User u"),
        @NamedQuery(name = "findAllClients", query = "SELECT u FROM User u WHERE u.role = :role"),
        @NamedQuery(name = "findByEmail", query = "SELECT u FROM User u WHERE u.email = :email")})
public class User extends GenericEntity {

    @Column(name = "first_name", nullable = false)
    String firstName;

    @Column(name = "last_name", nullable = false)
    String lastName;

    @Column(nullable = false)
    String passport;

    @Column(nullable = false)
    String adress;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    Calendar birthday;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    // SQL CHAR(64) for SHA-256 hash
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        EMPLOYEE, CLIENT
    };

    @OneToMany(targetEntity = Contract.class, mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Contract> contracts;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    @Override
    public String toString() {
        return "User [firstName=" + firstName + ", lastName=" + lastName + "]";
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
        if (!(obj instanceof User)) {
            return false;
        }
        User other = (User) obj;
        if (getId() != other.getId()) {
            return false;
        }
        return true;
    }

}
