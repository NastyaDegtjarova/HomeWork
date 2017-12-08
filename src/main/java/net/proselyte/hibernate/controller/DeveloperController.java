package net.proselyte.hibernate.controller;

import net.proselyte.hibernate.dao.DeveloperDAO;
import net.proselyte.hibernate.dao.ProjectDAO;
import net.proselyte.hibernate.dao.SkillDAO;
import net.proselyte.hibernate.model.Developer;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;


/**
 * Created by Nastya on 19.11.2017.
 */
public class DeveloperController extends AbstractController {
    private static final int CREATE_DEVELOPER = 1;
    private static final int VIEW_ALL_DEVELOPER = 2;
    private static final int UPDATE_DEVELOPER = 3;
    private static final int DELETE_DEVELOPER = 4;
    private static final int VIEW_ID_DEVELOPER = 5;
    private static final int PREVIOUS_MENU = 0;
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
            System.out.println("1 - Create new DEVELOPER");
            System.out.println("2 - View developers");
            System.out.println("3 - Update DEVELOPER");
            System.out.println("4 - Delete DEVELOPER");
            System.out.println("5 - View DEVELOPER by id");
            System.out.println("0 - Previous menu");


            if (getScan().hasNextInt()) {
                int choise = getScan().nextInt();
                switch (choise) {
                    case CREATE_DEVELOPER:
                        createNewDeveloper();
                        break;
                    case VIEW_ALL_DEVELOPER:
                        showAllDevlopers();
                        break;
                    case UPDATE_DEVELOPER:
                        changeDeveloper();
                        break;
                    case DELETE_DEVELOPER:
                        deleteDeveloper();
                        break;
                    case VIEW_ID_DEVELOPER:
                        showDevById();
                        break;
                    case PREVIOUS_MENU:
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void showDevById() {
        System.out.println("Input DEVELOPER id");
        long devId = getScan().nextLong();
        try {
            Developer developer = developerDAO.getById(devId);
            System.out.println(developer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteDeveloper() {
        System.out.println("Input DEVELOPER id");
        long devId = getScan().nextLong();
        try {
            developerDAO.delete(new Developer(devId));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void changeDeveloper() {
        System.out.println("Input DEVELOPER id");
        long devId = getScan().nextLong();
        System.out.println("Input DEVELOPER FIRST_NAME");
        getScan().nextLine();
        String devFirstName = getScan().nextLine();
        System.out.println("Input DEVELOPER LAST_NAME");
        String devLastName = getScan().nextLine();
        System.out.println("Input DEVELOPER SALARY");
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
                System.out.println(developer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createNewDeveloper() {
        System.out.println("Input DEVELOPER id");
        long devId = getScan().nextLong();
        System.out.println("Input DEVELOPER FIRST_NAME");
        getScan().nextLine();
        String devFirstName = getScan().nextLine();
        System.out.println("Input DEVELOPER LAST_NAME");
        String devLastName = getScan().nextLine();
        System.out.println("Input DEVELOPER SALARY");
        long devSalary = getScan().nextLong();
        try {
            developerDAO.save(new Developer(devId, devFirstName, devLastName, new BigDecimal(devSalary)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
