package net.proselyte.hibernate.model;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity

@Table(name = "project")
public class Project {
    @Id
    @Column(name = "id_projects")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_projects")
    private String name;

    @Column(name = "cost")
    private int cost;

    @ManyToMany
    @JoinTable(name = "project_developer",
            joinColumns = { @JoinColumn(name = "id_project") },
            inverseJoinColumns = { @JoinColumn(name = "id_developer") }
    )
    private List<Developer> developers;

    @ManyToMany
    @JoinTable(name = "customer_project",
            joinColumns = { @JoinColumn(name = "id_project") },
            inverseJoinColumns = { @JoinColumn(name = "id_customer") }
    )
    private List<Customer> customers;

    @ManyToMany
    @JoinTable(name = "compani_project",
            joinColumns = { @JoinColumn(name = "id_project") },
            inverseJoinColumns = { @JoinColumn(name = "id_compani") }
    )
    private List<Companie> companies;

    public Project() {
    }

    public Project(Long id) {
        this.id = id;
    }

    public Project(Long id, String name, int cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public List<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Developer> developers) {
        this.developers = developers;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Companie> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Companie> companies) {
        this.companies = companies;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", developers='" +  (
                developers == null
                        ? "[]"
                        :developers.stream().map(Developer::getFirstName).collect(Collectors.toList())) + '\'' +
                ", companies='" +  (
                companies == null
                        ? "[]"
                        :companies.stream().map(Companie::getNameComp).collect(Collectors.toList())) + '\'' +
                ", customers='" +  (
                customers == null
                        ? "[]"
                        : customers.stream().map(Customer::getFirstNameCust).collect(Collectors.toList())) + '\'' +
                '}';
    }

    public Project withIdProj(Long projId) {
        setId(projId);
        return this;
    }

    public Project withNameProj(String name_projects) {
        setName(name_projects);
        return this;
    }

    public Project withCost(Integer cost) {
        setCost(cost);
        return this;
    }
}
