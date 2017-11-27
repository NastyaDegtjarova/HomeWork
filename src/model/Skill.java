package model;


/**
 * Created by Nastya on 20.11.2017.
 */
public class Skill {
    private Long id_skill;
    private String specialty;

    public Skill() {
    }

    public Skill(Long id_skill) {
        this.id_skill = id_skill;
    }

    public Skill(Long id_skill, String specialty) {
        this.id_skill = id_skill;
        this.specialty = specialty;

    }

    public Long getId_skill() {
        return id_skill;
    }

    public void setId_skill(Long id) {
        this.id_skill = id_skill;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Skill withId_skill(Long id_skill){
        this.id_skill = id_skill;
        return this;
    }
    public Skill withSpecialty(String specialty){
        this.specialty = specialty;
        return this;
    }


    @Override
    public String toString() {
        return "Skill{" +
                "id_skill=" + id_skill +
                ", specialty ='" + specialty + '\'' +

                '}';
    }
}
