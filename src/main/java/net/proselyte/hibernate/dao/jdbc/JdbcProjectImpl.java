package net.proselyte.hibernate.dao.jdbc;


import net.proselyte.hibernate.dao.ConnectionUtil;
import net.proselyte.hibernate.dao.ProjectDAO;
import net.proselyte.hibernate.model.Project;


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
        String sql = "SELECT * FROM project WHERE id_projects = " + id;
        Statement statement = ConnectionUtil.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);



        if (resultSet.next()) {
            Project project = new Project();
            Long projId = resultSet.getLong("id_projects");
            String name_projects = resultSet.getString("name_projects");
            Integer cost = resultSet.getInt("cost");

            project.withIdProj(projId)
                    .withNameProj(name_projects)
                    .withCost(cost);
            return project;
        }else {
                System.out.println("No project with this ID!!!");
            }

        return null;
    }

    @Override
    public List<Project> getAll() throws SQLException {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT * FROM project";
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
        String sql = "INSERT INTO project (id_projects, name_projects, cost) VALUES " +
                "("+project.getId()+ ",'" + project.getName()+"',"+project.getCost()+")";
        System.out.println(sql);
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        statement.close();
        connection.close();
    }

    @Override
    public void update(Project project) throws SQLException {
        String sql = "UPDATE project SET id_projects = "+project.getId()+", name_projects ='"+project.getName()+
                "', cost = "+ project.getCost() +" WHERE id_projects = " + project.getId();
        System.out.println(sql);
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        statement.close();
        connection.close();
    }

    @Override
    public void delete(Project project) throws SQLException {
        String sqlDelDevRefProj = "DELETE FROM project_developer WHERE id_project = " + project.getId();
        String sqlDelCustRefProj = "DELETE FROM customers_project WHERE id_project = " + project.getId();
        String sqlDelCompRefProj = "DELETE FROM companies_project WHERE id_project = " + project.getId();
        String sqlDelProj = "DELETE FROM project WHERE id_projects = " + project.getId();
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
