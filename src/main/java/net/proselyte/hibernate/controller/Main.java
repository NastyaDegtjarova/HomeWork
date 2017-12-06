package net.proselyte.hibernate.controller;

import net.proselyte.hibernate.dao.jdbc.*;

import java.util.Scanner;

/**
 * Created by Nastya on 19.11.2017.
 */
public class Main {
    public static void main(String[] args) {
        DeveloperController developerController = new DeveloperController(
                JdbcDeveloperDAOImpl.getInstance(),
               JdbcProjectDAOImpl.getInstance(),
                JdbcSkillDAOImpl.getInstance());
        SkillController skillController = new SkillController(
                JdbcSkillDAOImpl.getInstance(),
                JdbcDeveloperDAOImpl.getInstance()
        );
        ProjectController projectController = new ProjectController(
                JdbcProjectDAOImpl.getInstance(),
                JdbcDeveloperDAOImpl.getInstance(),
                JdbcCustomerDAOImpl.getInstance(),
                JdbcCompanieDAOImpl.getInstance()
        );
        CustomerController customerController = new CustomerController(
                JdbcCustomerDAOImpl.getInstance(),
                JdbcProjectDAOImpl.getInstance()
        );
        CompanyController companyController = new CompanyController(
                JdbcCompanieDAOImpl.getInstance(),
                JdbcProjectDAOImpl.getInstance()
        );

        while (true) {
            System.out.println("1 - Developers");
            System.out.println("2 - Skills");
            System.out.println("3 - Companies");
            System.out.println("4 - Customers");
            System.out.println("5 - Projects");
            System.out.println("0 - Exit");

            Scanner scan = new Scanner(System.in);
            if (scan.hasNextInt()) {
                int choise = scan.nextInt();
                if (choise == 1) {
                    developerController.menu();
                } else if (choise == 2) {
                    skillController.menu();
                } else if (choise == 3) {
                    companyController.menu();
                } else if (choise == 4) {
                    customerController.menu();
                } else if (choise == 5) {
                    projectController.menu();
                } else if (choise == 0) {
                    break;
                }
            }
        }
    }
}
