package net.proselyte.hibernate.model;

import javax.persistence.*;
import java.util.List;

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
    List<Developer> developer;

    @ManyToMany
    List<Customer> customer;

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

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Project withIdProj(Long projId) {
        return this;
    }

    public Project withNameProj(String name_projects) {
        return this;
    }

    public Project withCost(Integer cost) {
        return this;
    }
}
