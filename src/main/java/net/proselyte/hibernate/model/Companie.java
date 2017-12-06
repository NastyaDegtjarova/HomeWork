package net.proselyte.hibernate.model;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nastya on 20.11.2017.
 */
@Entity
@Table(name = "companie")
public class Companie {
    @Id
    @Column(name = "idCompanies")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComp;

    @Column(name = "nameCompanies")
    private String nameComp;

    @ManyToMany
    @JoinTable(name = "compani_project",
            joinColumns = { @JoinColumn(name = "id_companie") },
            inverseJoinColumns = { @JoinColumn(name = "id_project") }
    )
    private List<Project> projects;

    public Companie() {
    }

    public Companie(Long idComp, String nameComp) {
        this.idComp = idComp;
        this.nameComp = nameComp;
    }

    public Companie(Long idComp) {
        this.idComp = idComp;
    }

    public Long getIdComp() {
        return idComp;
    }

    public void setIdComp(Long idComp) {
        this.idComp = idComp;
    }

    public String getNameComp() {
        return nameComp;
    }

    public void setNameComp(String nameComp) {
        this.nameComp = nameComp;
    }

    @Override
    public String toString() {
        return "Companie{" +
                "idComp=" + idComp +
                ", projects='" + (
                        projects == null
                        ? "[]"
                        : projects.stream().map(Project::getName).collect(Collectors.toList())) + '\'' +
                ", nameComp ='" + nameComp + '\'' +
                '}';
    }

    public Companie withId_comp(Long idComp){
        this.idComp = idComp;
        return this;
    }
    public Companie withNameComp(String nameComp){
        this.nameComp = nameComp;
        return this;
    }

    public void setProjects(List<Project> projects) {

    }
}
