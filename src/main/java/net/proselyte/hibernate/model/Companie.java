package net.proselyte.hibernate.model;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nastya on 20.11.2017.
 */

public class Companie {

    private Long idComp;
    private String nameComp;
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
