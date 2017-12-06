package net.proselyte.hibernate.controller;

import net.proselyte.hibernate.dao.CompanieDAO;
import net.proselyte.hibernate.dao.ProjectDAO;
import net.proselyte.hibernate.model.Companie;
import net.proselyte.hibernate.model.Project;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Nastya on 20.11.2017.
 */
public class CompanyController {
    private CompanieDAO companieDAO;
    private ProjectDAO projectDAO;
    private Scanner scan;

    public CompanyController(CompanieDAO companieDAO, ProjectDAO projectDAO) {
        this.companieDAO = companieDAO;
        this.projectDAO = projectDAO;
        scan = new Scanner(System.in);
    }

    public void menu() {
        while(true) {
            System.out.println("1 - Create new companie");
            System.out.println("2 - View companie");
            System.out.println("3 - Update companie");
            System.out.println("4 - Delete companie");
            System.out.println("5 - View all companie");
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
                } else if (choise == 5) {
                    showAllComp();
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
            setDeps(companie);
            System.out.println(companie);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showAllComp() {
        try {
            List<Companie> companies = companieDAO.getAll();
            for(int i = 0; i < companies.size(); i++){
                Companie companie = companies.get(i);
                setDeps(companie);
                System.out.println(companie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setDeps(Companie companie) {
        try {
            List<Project> projects = projectDAO.getByCompId(companie.getIdComp());
            companie.setProjects(projects);
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

