package net.proselyte.hibernate.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "developer")
public class Developer {

    @Id
    @Column(name = "id_developer")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "salary")
    private BigDecimal salary;

    @ManyToMany
    private List<Project> project;

    @ManyToMany
    private List<Project> skill;

    public Developer() {
    }

    public Developer(Long id) {
        this.id = id;
    }

    public Developer(Long id, String firstName, String lastName, BigDecimal salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                '}';
    }

    public Developer withId(Long developerId) {
        setId(developerId);
        return this;
    }

    public Developer withFirstName(String firstName) {
        setFirstName(firstName);
        return this;
    }

    public Developer withLastName(String lastName) {
        setLastName(lastName);
        return this;
    }

    public Developer withSalary(BigDecimal salary) {
        setSalary(salary);
        return this;
    }
}