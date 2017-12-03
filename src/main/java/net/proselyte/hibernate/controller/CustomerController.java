package net.proselyte.hibernate.controller;

import net.proselyte.hibernate.dao.CustomerDAO;
import net.proselyte.hibernate.dao.DeveloperDAO;
import net.proselyte.hibernate.dao.ProjectDAO;
import net.proselyte.hibernate.model.Customer;
import net.proselyte.hibernate.model.Project;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Nastya on 20.11.2017.
 */
public class CustomerController {
    private CustomerDAO customerDAO;
    private ProjectDAO projectDAO;
    private Scanner scan;

    public CustomerController(CustomerDAO customerDAO, ProjectDAO projectDAO) {
        this.customerDAO = customerDAO;
        this.projectDAO = projectDAO;
        scan = new Scanner(System.in);
    }

    public void menu() {
        while(true) {
            System.out.println("1 - Create new customer");
            System.out.println("2 - View customer");
            System.out.println("3 - Update customer");
            System.out.println("4 - Delete customer");
            System.out.println("5 - View all customer");
            System.out.println("0 - Previous menu");

            int choise = 0;
            if (scan.hasNextInt()) {
                choise = scan.nextInt();
                if (choise == 1) {
                    createNewCustomer();
                } else if (choise == 2) {
                    showCustomById();
                } else if (choise == 3) {
                    changeCustomer();
                } else if (choise == 4) {
                    deleteCustomer();
                } else if (choise == 5) {
                    showAllCustom();
                } else if (choise == 0) {
                    break;
                }  else {
                    System.out.println("Input incorrect");
                }
            }
        }

    }

    private void showCustomById() {
        System.out.println("Input customer id");
        long customId = scan.nextLong();
        try {
            Customer customer = customerDAO.getById(customId);
            setDeps(customer);
            System.out.println(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showAllCustom() {
        try {
            List<Customer> customers = customerDAO.getAll();
            for(int i = 0; i < customers.size(); i++){
                Customer customer = customers.get(i);
                setDeps(customer);
                System.out.println(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setDeps(Customer customer) {
        try {
            List<Project> projects = projectDAO.getByCustId(customer.getIdCustomer());
            customer.setProjects(projects);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteCustomer() {
        System.out.println("Input customer id");
        long customerId = scan.nextLong();
        try {
            customerDAO.delete(new Customer(customerId));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void changeCustomer() {
        System.out.println("Input customer id");
        long customerId = scan.nextLong();
        System.out.println("Input firstNameCustomer");
        scan.nextLine();
        String firstNameCustomer = scan.nextLine();
        System.out.println("Input lastNameCustomer");
        String lastNameCustomer = scan.nextLine();
        try {
            customerDAO.update(new Customer(customerId, firstNameCustomer, lastNameCustomer));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createNewCustomer() {
        System.out.println("Input customer id");
        long customerId = scan.nextLong();
        System.out.println("Input firstNameCustomer");
        scan.nextLine();
        String firstNameCustomer = scan.nextLine();
        System.out.println("Input lastNameCustomer");
        String lastNameCustomer = scan.nextLine();
        try {
            customerDAO.save(new Customer(customerId, firstNameCustomer, lastNameCustomer));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
