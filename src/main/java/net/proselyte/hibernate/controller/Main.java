package net.proselyte.hibernate.controller;


import net.proselyte.hibernate.dao.jdbc.*;

import java.util.Scanner;

/**
 * Created by Nastya on 19.11.2017.
 */
public class Main {
    public static void main(String[] args) {
        DeveloperController developerController = new DeveloperController(
                new JdbcDeveloperDAOImpl(),
                new JdbcProjectDAOImpl(),
                new JdbcSkillDAOImpl());
/*        DeveloperController developerController = new DeveloperController(
                new HibernateDeveloperDAOImpl(),
                new HibernateProjectDAOImpl(),
                new HibernateSkiiDAOImpl());*/
        SkillController skillController = new SkillController(
                new JdbcSkillDAOImpl(),
                new JdbcDeveloperDAOImpl()
        );
        ProjectController projectController = new ProjectController(
                new JdbcProjectDAOImpl(),
                new JdbcDeveloperDAOImpl(),
                new JdbcCustomerDAOImpl(),
                new JdbcCompanieDAOImpl()
        );
        CustomerController customerController = new CustomerController(
                new JdbcCustomerDAOImpl(),
                new JdbcProjectDAOImpl()
        );
        CompanyController companyController = new CompanyController(
                new JdbcCompanieDAOImpl(),
                new JdbcProjectDAOImpl()
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
