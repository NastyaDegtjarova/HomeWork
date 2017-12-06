package net.proselyte.hibernate.controller;

import net.proselyte.hibernate.dao.DeveloperDAO;
import net.proselyte.hibernate.dao.ProjectDAO;
import net.proselyte.hibernate.dao.SkillDAO;
import net.proselyte.hibernate.model.Developer;
import net.proselyte.hibernate.model.Project;
import net.proselyte.hibernate.model.Skill;
import org.hibernate.Hibernate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Nastya on 19.11.2017.
 */
public class DeveloperController extends AbstractController {
    private static final int CREATE_DEVELOPER = 1;
//    private static final int CREATE_DEVELOPER = 1;
//    private static final int CREATE_DEVELOPER = 1;
//    private static final int CREATE_DEVELOPER = 1;
//    private static final int CREATE_DEVELOPER = 1;
    private DeveloperDAO developerDAO;
    private ProjectDAO projectDAO;
    private SkillDAO skillDAO;

    public DeveloperController() {
    }

    public DeveloperController(DeveloperDAO developerDAO, ProjectDAO projectDAO, SkillDAO skillDAO) {
        this.developerDAO = developerDAO;
        this.projectDAO = projectDAO;
        this.skillDAO = skillDAO;
    }

    public void menu() {
        while(true) {
            System.out.println("1 - Create new developer");
            System.out.println("2 - View developers");
            System.out.println("3 - Update developer");
            System.out.println("4 - Delete developer");
            System.out.println("5 - View developer by id");
            System.out.println("0 - Previous menu");


            if (getScan().hasNextInt()) {
                int choise = getScan().nextInt();
                switch (choise) {
                    case CREATE_DEVELOPER:
                        createNewDeveloper();
                        break;
                    case 2:
                        showAllDevlopers();
                        break;
                    //
                }
                if (choise == CREATE_DEVELOPER) {
                    createNewDeveloper();
                } else if (choise == 2) {
                    showAllDevlopers();
                } else if (choise == 3) {
                    changeDeveloper();
                } else if (choise == 4) {
                    deleteDeveloper();
                } else if (choise == 5) {
                    showDevById();
                } else if (choise == 0) {
                    break;
                }  else {
                    System.out.println("Input incorrect");
                }
            }
        }
    }

    private void showDevById() {
        System.out.println("Input developer id");
        long devId = getScan().nextLong();
        try {
            Developer developer = developerDAO.getById(devId);
            Hibernate.initialize(developer.getProjects());
            Hibernate.initialize(developer.getSkills());
            System.out.println(developer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setDeps(Developer developer) {
        try {
            List<Project> projects = projectDAO.getByDevId(developer.getId());
            developer.setProjects(projects);
            List<Skill> skills = skillDAO.getByDevId(developer.getId());
            developer.setSkills(skills);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void deleteDeveloper() {
        System.out.println("Input developer id");
        long devId = getScan().nextLong();
        try {
            developerDAO.delete(new Developer(devId));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void changeDeveloper() {
        System.out.println("Input developer id");
        long devId = getScan().nextLong();
        System.out.println("Input developer first_name");
        getScan().nextLine();
        String devFirstName = getScan().nextLine();
        System.out.println("Input developer last_name");
        String devLastName = getScan().nextLine();
        System.out.println("Input developer salary");
        long devSalary = getScan().nextLong();
        try {
            developerDAO.update(new Developer(devId, devFirstName, devLastName, new BigDecimal(devSalary)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showAllDevlopers() {
        try {
            List<Developer> developers = developerDAO.getAll();
            for(int i = 0; i < developers.size(); i++){
                Developer developer = developers.get(i);

//                setDeps(developer);
                System.out.println(developer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createNewDeveloper() {
        System.out.println("Input developer id");
        long devId = getScan().nextLong();
        System.out.println("Input developer first_name");
        getScan().nextLine();
        String devFirstName = getScan().nextLine();
        System.out.println("Input developer last_name");
        String devLastName = getScan().nextLine();
        System.out.println("Input developer salary");
        long devSalary = getScan().nextLong();
        try {
            developerDAO.save(new Developer(devId, devFirstName, devLastName, new BigDecimal(devSalary)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
