package net.proselyte.hibernate.controller;



import net.proselyte.hibernate.dao.CompanieDAO;
import net.proselyte.hibernate.model.Companie;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by Nastya on 20.11.2017.
 */
public class CompanyController {
    private CompanieDAO companieDAO;
    private Scanner scan;

    public CompanyController(CompanieDAO companieDAO) {
        this.companieDAO = companieDAO;
        scan = new Scanner(System.in);
    }

    public void menu() {
        while(true) {
            System.out.println("1 - Create new companie");
            System.out.println("2 - View companie");
            System.out.println("3 - Update companie");
            System.out.println("4 - Delete companie");
            System.out.println("0 - Previous companie");

            int choise = 0;
            if (scan.hasNextInt()) {
                choise = scan.nextInt();
                if (choise == 1) {
                    createNewCompanie();
                } else if (choise == 2) {
                    showCompanById();
                } else if (choise == 3) {
                    changeCompanie();
                } else if (choise == 4) {
                    deleteCompanie();
                } else if (choise == 0) {
                    break;
                }  else {
                    System.out.println("Input incorrect");
                }
            }
        }

    }

    private void showCompanById() {
        System.out.println("Input customer id");
        long companieId = scan.nextLong();
        try {
            Companie companie = companieDAO.getById(companieId);
            System.out.println(companie);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteCompanie() {
        System.out.println("Input companie id");
        long comanieId = scan.nextLong();
        try {
            companieDAO.delete(new Companie(comanieId));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void changeCompanie() {
        System.out.println("Input companies id");
        long compId = scan.nextLong();
        System.out.println("Input name companie");
        scan.nextLine();
        String name_companie = scan.nextLine();
        try {
            companieDAO.update(new Companie(compId, name_companie));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createNewCompanie() {
        System.out.println("Input companies id");
        long compId = scan.nextLong();
        System.out.println("Input name companie");
        scan.nextLine();
        String name_companie = scan.nextLine();
        try {
            companieDAO.save(new Companie(compId, name_companie));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

