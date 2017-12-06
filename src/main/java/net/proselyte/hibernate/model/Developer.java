package net.proselyte.hibernate.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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
    @JoinTable(name = "project_developer",
            joinColumns = { @JoinColumn(name = "id_developer") },
            inverseJoinColumns = { @JoinColumn(name = "id_project") }
    )
    private List<Project> projects;

    @ManyToMany
    @JoinTable(name = "developer_skill",
            joinColumns = { @JoinColumn(name = "id_developer") },
            inverseJoinColumns = { @JoinColumn(name = "id_skill") }
    )
    private List<Skill> skills;

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

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", projects='" +  (
                projects == null
                        ? "[]"
                        :projects.stream().map(Project::getName).collect(Collectors.toList())) + '\'' +
                ", skills='" +  (
                skills == null
                        ? "[]"
                        :skills.stream().map(Skill::getSpecialty).collect(Collectors.toList())) + '\'' +
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