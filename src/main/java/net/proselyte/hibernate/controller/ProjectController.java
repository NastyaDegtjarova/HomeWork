package net.proselyte.hibernate.controller;

import net.proselyte.hibernate.dao.ProjectDAO;
import net.proselyte.hibernate.model.Project;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by Nastya on 20.11.2017.
 */
public class ProjectController {
    private ProjectDAO projectDAO;
    private Scanner scan;

    public ProjectController(ProjectDAO projectDAO) {
        this.projectDAO = projectDAO;
        scan = new Scanner(System.in);
    }

    public void menu() {
        while(true) {
            System.out.println("1 - Create new project");
            System.out.println("2 - View project");
            System.out.println("3 - Update project");
            System.out.println("4 - Delete project");
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
                } else if (choise == 0) {
                    break;
                }  else {
                    System.out.println("Input incorrect");
                }
            }
        }

    }

    private void showProjById() {
        System.out.println("Input project id");
        long projId = scan.nextLong();
        try {
            Project project = projectDAO.getById(projId);
            System.out.println(project);
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

