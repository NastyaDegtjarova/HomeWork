package controller;

import dao.DeveloperDAO;
import dao.jdbc.JdbcDeveloperDAOImpl;
import model.Developer;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Nastya on 19.11.2017.
 */
public class DeveloperController {
    private DeveloperDAO developerDAO;
    private Scanner scan;

    public DeveloperController(DeveloperDAO developerDAO) {
        this.developerDAO = developerDAO;
        scan = new Scanner(System.in);
    }

    public void menu() {
        while(true) {
            System.out.println("1 - Create new developer");
            System.out.println("2 - View developers");
            System.out.println("3 - Update developer");
            System.out.println("4 - Delete developer");
            System.out.println("5 - View developer by id");
            System.out.println("0 - Previous menu");


            if (scan.hasNextInt()) {
                int choise = scan.nextInt();
                if (choise == 1) {
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
        long devId = scan.nextLong();
        try {
            Developer developer = developerDAO.getById(devId);
            System.out.println(developer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteDeveloper() {
        System.out.println("Input developer id");
        long devId = scan.nextLong();
        try {
            developerDAO.delete(new Developer(devId));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void changeDeveloper() {
        System.out.println("Input developer id");
        long devId = scan.nextLong();
        System.out.println("Input developer first_name");
        scan.nextLine();
        String devFirstName = scan.nextLine();
        System.out.println("Input developer last_name");
        String devLastName = scan.nextLine();
        System.out.println("Input developer salary");
        long devSalary = scan.nextLong();
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
                System.out.println(developers.get(i));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createNewDeveloper() {
        System.out.println("Input developer id");
        long devId = scan.nextLong();
        System.out.println("Input developer first_name");
        scan.nextLine();
        String devFirstName = scan.nextLine();
        System.out.println("Input developer last_name");
        String devLastName = scan.nextLine();
        System.out.println("Input developer salary");
        long devSalary = scan.nextLong();
        try {
            developerDAO.save(new Developer(devId, devFirstName, devLastName, new BigDecimal(devSalary)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
