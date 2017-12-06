package net.proselyte.hibernate.model;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nastya on 20.11.2017.
 */
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "id_customer")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCustomer;

    @Column(name = "first_name_customers")
    private String firstNameCust;

    @Column(name = "last_name_customers")
    private String lastNameCust;

    @ManyToMany
    @JoinTable(name = "customer_project",
            joinColumns = { @JoinColumn(name = "id_customer") },
            inverseJoinColumns = { @JoinColumn(name = "id_project") }
    )
    private List<Project> projects;

    public Customer() {
    }

    public Customer(Long idCustomer, String firstNameCust, String lastNameCust) {
        this.idCustomer = idCustomer;
        this.firstNameCust = firstNameCust;
        this.lastNameCust = lastNameCust;

    }

    public Customer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getFirstNameCust() {
        return firstNameCust;
    }

    public void setFirstNameCust(String firstNameCust) {
        this.firstNameCust = firstNameCust;
    }

    public String getLastNameCust() {
        return lastNameCust;
    }

    public void setLastNameCust(String lastNameCust) {
        this.lastNameCust = lastNameCust;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "idCustomer =" + idCustomer +
                ", firstNameCust ='" + firstNameCust + '\'' +
                ", lastNameCust ='" + lastNameCust + '\'' +
                ", projects='" +  (
                projects == null
                        ? "[]"
                        : projects.stream().map(Project::getName).collect(Collectors.toList())) + '\'' +
                '}';
    }

    public Customer withIdCust(Long idCustomer){
        this.idCustomer = idCustomer;
        return this;
    }

    public Customer withFirstNameCust(String firstNameCust){
        this.firstNameCust = firstNameCust;
        return this;
    }

    public Customer withLastNameCust(String lastNameCust){
        this.lastNameCust = lastNameCust;
        return this;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
