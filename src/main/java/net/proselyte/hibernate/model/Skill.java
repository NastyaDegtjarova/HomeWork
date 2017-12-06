package net.proselyte.hibernate.model;


import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nastya on 20.11.2017.
 */
@Entity
@Table(name = "skill")
public class Skill {
    @Id
    @Column(name = "idSkills")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSkill;

    @Column(name = "specialty")
    private String specialty;

    @ManyToMany
    @JoinTable(name = "developer_skill",
            joinColumns = { @JoinColumn(name = "id_skill") },
            inverseJoinColumns = { @JoinColumn(name = "id_developer") }
    )
    private List<Developer> developers;

    public Skill() {
    }

    public Skill(Long idSkill) {
        this.idSkill = idSkill;
    }

    public Skill(Long idSkill, String specialty) {
        this.idSkill = idSkill;
        this.specialty = specialty;

    }

    public Long getIdSkill() {
        return idSkill;
    }

    public void setIdSkill(Long id) {
        this.idSkill = idSkill;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Skill withIdSkill(Long idSkill){
        this.idSkill = idSkill;
        return this;
    }
    public Skill withSpecialty(String specialty){
        this.specialty = specialty;
        return this;
    }

    public List<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Developer> developers) {
        this.developers = developers;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "idSkill=" + idSkill +
                ", developers='" +  (
                developers == null
                        ? "[]"
                        :developers.stream().map(Developer::getLastName).collect(Collectors.toList())) + '\'' +
                ", specialty ='" + specialty + '\'' +

                '}';
    }

    public Skill withId(Long idSkill) {
        setIdSkill(idSkill);
        return this;
    }

    public Skill withFirstName(String specialty) {
        setSpecialty(specialty);
        return this;
    }
}
