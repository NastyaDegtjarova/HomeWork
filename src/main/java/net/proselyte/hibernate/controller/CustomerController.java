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
public class CustomerController extends AbstractController{
    private static final int CREATE_CUSTOMER = 1;
    private static final int VIEW_ALL_CUSTOMER = 2;
    private static final int UPDATE_CUSTOMER = 3;
    private static final int DELETE_CUSTOMER = 4;
    private static final int VIEW_ID_CUSTOMER = 5;
    private static final int PREVIOUS_MENU = 0;
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
            System.out.println("1 - Create new CUSTOMER");
            System.out.println("2 - View all CUSTOMER");
            System.out.println("3 - Update CUSTOMER");
            System.out.println("4 - Delete CUSTOMER");
            System.out.println("5 - View CUSTOMER");
            System.out.println("0 - Previous menu");

            if (getScan().hasNextInt()) {
                int choise = getScan().nextInt();
                switch (choise) {
                    case CREATE_CUSTOMER:
                        createNewCustomer();
                        break;
                    case VIEW_ALL_CUSTOMER:
                        showAllCustom();
                        break;
                    case UPDATE_CUSTOMER:
                        changeCustomer();
                        break;
                    case DELETE_CUSTOMER:
                        deleteCustomer();
                        break;
                    case VIEW_ID_CUSTOMER:
                        showCustomById();
                        break;
                    case PREVIOUS_MENU:
                        break;
                    default:
                        break;
                }
            }

        }

    }

    private void showCustomById() {
        System.out.println("Input CUSTOMER id");
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
        System.out.println("Input CUSTOMER id");
        long customerId = scan.nextLong();
        try {
            customerDAO.delete(new Customer(customerId));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void changeCustomer() {
        System.out.println("Input CUSTOMER id");
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
        System.out.println("Input CUSTOMER id");
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
