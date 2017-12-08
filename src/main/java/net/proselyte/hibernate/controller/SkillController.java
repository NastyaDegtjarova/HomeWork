package net.proselyte.hibernate.controller;

import net.proselyte.hibernate.dao.DeveloperDAO;
import net.proselyte.hibernate.dao.SkillDAO;
import net.proselyte.hibernate.model.Developer;
import net.proselyte.hibernate.model.Skill;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Nastya on 19.11.2017.
 */
public class SkillController extends AbstractController{
    private static final int CREATE_SKILL = 1;
    private static final int VIEW_ALL_SKILL = 2;
    private static final int UPDATE_SKILL = 3;
    private static final int DELETE_SKILL = 4;
    private static final int VIEW_ID_SKILL = 5;
    private static final int PREVIOUS_MENU = 0;
    private SkillDAO skillDAO;
    private DeveloperDAO developerDAO;
    private Scanner scan;

    public SkillController(SkillDAO skillDAO, DeveloperDAO developerDAO) {
        this.skillDAO = skillDAO;
        this.developerDAO = developerDAO;
        scan = new Scanner(System.in);
    }

    public void menu() {
        while(true) {
            System.out.println("1 - Create new SKILL");
            System.out.println("2 - View all SKILL");
            System.out.println("3 - Update SKILL");
            System.out.println("4 - Delete SKILL");
            System.out.println("5 - View SKILL");
            System.out.println("0 - Previous menu");

            if (getScan().hasNextInt()) {
                int choise = getScan().nextInt();
                switch (choise) {
                    case CREATE_SKILL:
                        createNewSkill();
                        break;
                    case VIEW_ALL_SKILL:
                        showAllSkills();
                        break;
                    case UPDATE_SKILL:
                        changeSkill();
                        break;
                    case DELETE_SKILL:
                        deleteSkill();
                        break;
                    case VIEW_ID_SKILL:
                        showSkilById();
                        break;
                    case PREVIOUS_MENU:
                        break;
                    default:
                        break;
                }

            }
        }

    }

    private void showSkilById() {
        System.out.println("Input SKILL id");
        long skillId = scan.nextLong();
        try {
            Skill skill = skillDAO.getById(skillId);
            setDeps(skill);
            System.out.println(skill);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showAllSkills() {
        try {
            List<Skill> skills = skillDAO.getAll();
            for(int i = 0; i < skills.size(); i++){
                Skill skill = skills.get(i);
                setDeps(skill);
                System.out.println(skill);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setDeps(Skill skill) {
        try {
            List<Developer> developers = developerDAO.getByProjId(skill.getIdSkill());
            skill.setDevelopers(developers);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteSkill() {
        System.out.println("Input SKILL id");
        long skillId = scan.nextLong();
        try {
            skillDAO.delete(new Skill(skillId));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void changeSkill() {
        System.out.println("Input SKILL id");
        long skillId = scan.nextLong();
        System.out.println("Input SKILL SPECIALTY");
        scan.nextLine();
        String skillSpecialty = scan.nextLine();
        try {
            skillDAO.update(new Skill(skillId, skillSpecialty));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createNewSkill() {
        System.out.println("Input SKILL id");
        long skillId = scan.nextLong();
        System.out.println("Input SKILL SPECIALTY");
        scan.nextLine();
        String skillSpecialty = scan.nextLine();
        try {
            skillDAO.save(new Skill(skillId, skillSpecialty));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
