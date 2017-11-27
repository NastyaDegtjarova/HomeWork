package model;

/**
 * Created by Nastya on 20.11.2017.
 */
public class Project {
    private Long idProject;
    private String nameProject;
    private Integer cost;

    public Project() {
    }

    public Project(Long idProject, String nameProject, Integer cost) {
        this.idProject = idProject;
        this.nameProject = nameProject;
        this.cost = cost;
    }

    public Project(Long idProject) {
        this.idProject = idProject;
    }

    public Long getIdProj() {
        return idProject;
    }

    public void setIdProj(Long idProject) {
        this.idProject = idProject;
    }

    public String getNameProject() {
        return nameProject;
    }

    public void setNameProject(String nameProject) {
        this.nameProject = nameProject;
    }
    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Project withIdProj(Long idProject){
        this.idProject = idProject;
        return this;
    }

    public Project withNameProj(String nameProject){
        this.nameProject = nameProject;
        return this;
    }

    public Project withCost(Integer cost){
        this.cost = cost;
        return this;
    }

    @Override
    public String toString() {
        return "Project{" +
                "idProj=" + idProject +
                ", nameProj='" + nameProject + '\'' +
                ", cost=" + cost +
                '}';
    }
}
