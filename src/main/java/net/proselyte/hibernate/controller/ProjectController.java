package net.proselyte.hibernate.controller;

import net.proselyte.hibernate.dao.CompanieDAO;
import net.proselyte.hibernate.dao.CustomerDAO;
import net.proselyte.hibernate.dao.DeveloperDAO;
import net.proselyte.hibernate.dao.ProjectDAO;
import net.proselyte.hibernate.model.Companie;
import net.proselyte.hibernate.model.Customer;
import net.proselyte.hibernate.model.Developer;
import net.proselyte.hibernate.model.Project;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Nastya on 20.11.2017.
 */
public class ProjectController {
    private ProjectDAO projectDAO;
    private DeveloperDAO developerDAO;
    private CustomerDAO customerDAO;
    private CompanieDAO companieDAO;
    private Scanner scan;


    public ProjectController(ProjectDAO projectDAO, DeveloperDAO developerDAO, CustomerDAO customerDAO, CompanieDAO companieDAO) {
        this.projectDAO = projectDAO;
        this.developerDAO = developerDAO;
        this.customerDAO = customerDAO;
        this.companieDAO = companieDAO;
        scan = new Scanner(System.in);
    }


    public void menu() {
        while(true) {
            System.out.println("1 - Create new project");
            System.out.println("2 - View project");
            System.out.println("3 - Update project");
            System.out.println("4 - Delete project");
            System.out.println("5 - View all project");
            System.out.println("0 - Previous menu");

            int choise = 0;
            if (scan.hasNextInt()) {
                choise = scan.nextInt();
                if (choise == 1) {
                    createNewProject();
                } else if (choise == 2) {
                    showProjById();
                } else if (choise == 3) {
                    changeProject();
                } else if (choise == 4) {
                    deleteProject();
                } else if (choise == 5) {
                    showAll();
                } else if (choise == 0) {
                    break;
                }  else {
                    System.out.println("Input incorrect");
                }
            }
        }

    }

    private void showAll() {

        try {
            List<Project> projects = projectDAO.getAll();
            for(int i = 0; i < projects.size(); i++){
                Project project = projects.get(i);
                setDeps(project);
                System.out.println(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showProjById() {
        System.out.println("Input project id");
        long projId = scan.nextLong();
        try {
            Project project = projectDAO.getById(projId);
            setDeps(project);
            System.out.println(project);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setDeps(Project project) {
        try {
            List<Developer> developers = developerDAO.getByProjId(project.getId());
            project.setDevelopers(developers);
            List<Companie> companies = companieDAO.getByProjId(project.getId());
            project.setCompanies(companies);
            List<Customer> customers = customerDAO.getByProjId(project.getId());
            project.setCustomers(customers);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteProject() {
        System.out.println("Input project id");
        long projectId = scan.nextLong();
        try {
            projectDAO.delete(new Project(projectId));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void changeProject() {
        System.out.println("Input project id");
        long projectId = scan.nextLong();
        System.out.println("Input name_project");
        scan.nextLine();
        String projName = scan.nextLine();
        System.out.println("Input project cost");
        int projCost = scan.nextInt();
        try {
            projectDAO.update(new Project(projectId, projName, projCost));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createNewProject() {
        System.out.println("Input project id");
        long projectId = scan.nextLong();
        System.out.println("Input project name");
        scan.nextLine();
        String projectName = scan.nextLine();
        System.out.println("Input project cost");
        int projCost = scan.nextInt();
        try {
            projectDAO.save(new Project(projectId, projectName, projCost));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

