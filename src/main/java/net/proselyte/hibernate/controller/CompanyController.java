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
public class CompanyController extends AbstractController{
    private static final int CREATE_COMPANY = 1;
    private static final int VIEW_ALL_COMPANY = 2;
    private static final int UPDATE_COMPANY = 3;
    private static final int DELETE_COMPANY = 4;
    private static final int VIEW_ID_COMPANY = 5;
    private static final int PREVIOUS_MENU = 0;
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
            System.out.println("1 - Create new company");
            System.out.println("2 - View all company");
            System.out.println("3 - Update company");
            System.out.println("4 - Delete company");
            System.out.println("5 - View company");
            System.out.println("0 - Previous company");

            if (getScan().hasNextInt()) {
                int choise = getScan().nextInt();
                switch (choise) {
                    case CREATE_COMPANY:
                        createNewCompanie();
                        break;
                    case VIEW_ALL_COMPANY:
                        showAllComp();
                        break;
                    case UPDATE_COMPANY:
                        changeCompanie();
                        break;
                    case DELETE_COMPANY:
                        deleteCompanie();
                        break;
                    case VIEW_ID_COMPANY:
                        showCompanById();
                        break;
                    case PREVIOUS_MENU:
                        return;
                    default:
                        break;
                }
            }

        }

    }

    private void showCompanById() {
        System.out.println("Input CUSTOMER id");
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
        System.out.println("Input COMPANIE id");
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
        System.out.println("Input name COMPANIE");
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
        System.out.println("Input name COMPANIE");
        scan.nextLine();
        String name_companie = scan.nextLine();
        try {
            companieDAO.save(new Companie(compId, name_companie));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

