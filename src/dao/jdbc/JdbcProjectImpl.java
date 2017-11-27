package dao.jdbc;

import dao.ConnectionUtil;
import model.Project;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nastya on 20.11.2017.
 */
public class JdbcProjectImpl implements ProjectDAO {
    private Statement statement;


    @Override
    public Project getById(Long id) throws SQLException {
        String sql = "SELECT * FROM projects WHERE id_projects = " + id;
        Statement statement = ConnectionUtil.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        Project project = new Project();

        while (resultSet.next()) {
            Long projId = resultSet.getLong("id_projects");
            String name_projects = resultSet.getString("name_projects");
            Integer cost = resultSet.getInt("cost");

            project.withIdProj(projId)
                    .withNameProj(name_projects)
                    .withCost(cost);
        }

        return project;
    }

    @Override
    public List<Project> getAll() throws SQLException {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT * FROM projects";
        Statement statement = ConnectionUtil.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Project project = new Project();
            Long projId = resultSet.getLong("id_projects");
            String name_projects = resultSet.getString("projects");
            Integer cost = resultSet.getInt("cost");

            project.withIdProj(projId)
                    .withNameProj(name_projects)
                    .withCost(cost);

            projects.add(project);
        }

        return projects;
    }

    @Override
    public void save(Project project) throws SQLException {
        String sql = "INSERT INTO projects (id_projects, name_projects, cost) VALUES " +
                "("+project.getIdProj()+ ",'" + project.getNameProject()+"',"+project.getCost()+")";
        System.out.println(sql);
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        statement.close();
        connection.close();
    }

    @Override
    public void update(Project project) throws SQLException {
        String sql = "UPDATE projects SET id_projects = "+project.getIdProj()+", name_project='"+project.getNameProject()+
                "', cost = "+ project.getCost() +" WHERE id_project = " + project.getIdProj();
        System.out.println(sql);
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        statement.close();
        connection.close();
    }

    @Override
    public void delete(Project project) throws SQLException {
        String sqlDelDevRefProj = "DELETE FROM project_developer WHERE id_project = " + project.getIdProj();
        String sqlDelCustRefProj = "DELETE FROM customer_project WHERE id_project = " + project.getIdProj();
        String sqlDelCompRefProj = "DELETE FROM companies_project WHERE id_project = " + project.getIdProj();
        String sqlDelProj = "DELETE FROM projects WHERE id_projects = " + project.getIdProj();
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sqlDelDevRefProj);
        statement.executeUpdate(sqlDelCustRefProj);
        statement.executeUpdate(sqlDelCompRefProj);
        statement.executeUpdate(sqlDelProj);
        statement.close();
        connection.close();
    }
}
