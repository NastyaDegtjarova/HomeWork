package controller;

import dao.DeveloperDAO;
import dao.jdbc.SkillDAO;
import model.Developer;
import model.Skill;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Nastya on 19.11.2017.
 */
public class SkillController {
    private SkillDAO skillDAO;
    private Scanner scan;

    public SkillController(SkillDAO skillDAO) {
        this.skillDAO = skillDAO;
        scan = new Scanner(System.in);
    }

    public void menu() {
        while(true) {
            System.out.println("1 - Create new skill");
            System.out.println("2 - View skill");
            System.out.println("3 - Update skill");
            System.out.println("4 - Delete skill");
            System.out.println("0 - Previous menu");

            int choise = 0;
            if (scan.hasNextInt()) {
                choise = scan.nextInt();
                if (choise == 1) {
                    createNewSkill();
                } else if (choise == 2) {
                    showSkilById();
                } else if (choise == 3) {
                    changeSkill();
                } else if (choise == 4) {
                    deleteSkill();
                } else if (choise == 0) {
                    break;
                }  else {
                    System.out.println("Input incorrect");
                }
            }
        }

    }

    private void showSkilById() {
        System.out.println("Input skill id");
        long skillId = scan.nextLong();
        try {
            Skill skill = skillDAO.getById(skillId);
            System.out.println(skill);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteSkill() {
        System.out.println("Input skill id");
        long skillId = scan.nextLong();
        try {
            skillDAO.delete(new Skill(skillId));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void changeSkill() {
        System.out.println("Input skill id");
        long skillId = scan.nextLong();
        System.out.println("Input skill specialty");
        scan.nextLine();
        String skillSpecialty = scan.nextLine();
        try {
            skillDAO.update(new Skill(skillId, skillSpecialty));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createNewSkill() {
        System.out.println("Input skill id");
        long skillId = scan.nextLong();
        System.out.println("Input skill specialty");
        scan.nextLine();
        String skillSpecialty = scan.nextLine();
        try {
            skillDAO.save(new Skill(skillId, skillSpecialty));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
