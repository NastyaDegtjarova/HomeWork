package net.proselyte.hibernate.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Nastya on 20.11.2017.
 */
@Entity
@Table(name = "companie")
public class Companie {
    @Id
    @Column(name = "id_companies")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_comp;

    @Column(name = "name_companies")
    private String name_comp;

    @ManyToMany
    List<Project> projects;

    public Companie() {
    }

    public Companie(Long id_comp, String name_comp) {
        this.id_comp = id_comp;
        this.name_comp = name_comp;
    }

    public Companie(Long id_comp) {
        this.id_comp = id_comp;
    }

    public Long getId_comp() {
        return id_comp;
    }

    public void setId_comp(Long id_comp) {
        this.id_comp = id_comp;
    }

    public String getName_comp() {
        return name_comp;
    }

    public void setName_comp(String name_comp) {
        this.name_comp = name_comp;
    }


    public Companie withId_comp(Long id_comp){
        this.id_comp = id_comp;
        return this;
    }
    public Companie withNameComp(String name_comp){
        this.name_comp = name_comp;
        return this;
    }


    @Override
    public String toString() {
        return "Companie{" +
                "id_comp=" + id_comp +
                ", nameComp ='" + name_comp + '\'' +
                '}';
    }
}
