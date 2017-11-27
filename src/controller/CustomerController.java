package controller;

import dao.jdbc.CustomerDAO;
import model.Customer;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by Nastya on 20.11.2017.
 */
public class CustomerController {
    private CustomerDAO customerDAO;
    private Scanner scan;

    public CustomerController(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
        scan = new Scanner(System.in);
    }

    public void menu() {
        while(true) {
            System.out.println("1 - Create new customer");
            System.out.println("2 - View customer");
            System.out.println("3 - Update customer");
            System.out.println("4 - Delete customer");
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
            System.out.println(customer);
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
