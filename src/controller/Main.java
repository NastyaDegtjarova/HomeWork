package controller;

import dao.jdbc.*;

import java.util.Scanner;

/**
 * Created by Nastya on 19.11.2017.
 */
public class Main {
    public static void main(String[] args) {
        DeveloperController developerController = new DeveloperController(new JdbcDeveloperDAOImpl());
        SkillController skillController = new SkillController(new JdbcSkillDAOImpl());
        ProjectController projectController = new ProjectController(new JdbcProjectImpl());
        CustomerController customerController = new CustomerController(new JdbcCustomerImpl());
        CompanyController companyController = new CompanyController(new JdbcCompanieImpl());

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
