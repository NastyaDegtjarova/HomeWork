package net.proselyte.hibernate.dao.jdbc;


import net.proselyte.hibernate.dao.ConnectionUtil;
import net.proselyte.hibernate.dao.ProjectDAO;
import net.proselyte.hibernate.dao.TableNames;
import net.proselyte.hibernate.model.Project;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nastya on 20.11.2017.
 */
public class JdbcProjectDAOImpl implements ProjectDAO {
    private Statement statement;


    @Override
    public Project getById(Long id) throws SQLException {
        String sql = "SELECT * FROM "+ TableNames.project+" WHERE "+ProjectColumnName.id_projects+" = ?";
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();



        if (resultSet.next()) {
            Project project = new Project();
            Long projId = resultSet.getLong(ProjectColumnName.id_projects.name());
            String name_projects = resultSet.getString(ProjectColumnName.name_projects.name());
            Integer cost = resultSet.getInt(ProjectColumnName.cost.name());

            project.withIdProj(projId)
                    .withNameProj(name_projects)
                    .withCost(cost);
            return project;
        }else {
                System.out.println("No project with this ID!!!");
            }
        resultSet.close();
        statement.close();
        connection.close();
        return null;
    }

    @Override
    public List<Project> getByDevId(Long devId) throws SQLException {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT "+ProjectColumnName.id_projects+", "+ProjectColumnName.name_projects+", "+ProjectColumnName.cost
                +" FROM "+TableNames.project+" p, "+TableNames.project_developer+" pd WHERE p."+ProjectColumnName.id_projects
                +" = pd."+ProjectDeveloperColumnName.id_project+" AND pd."+ProjectDeveloperColumnName.id_developer+" = ?";

        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, devId);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Project project = new Project();
            Long projId = resultSet.getLong(ProjectColumnName.id_projects.name());
            String name_projects = resultSet.getString(ProjectColumnName.name_projects.name());
            Integer cost = resultSet.getInt(ProjectColumnName.cost.name());

            project.withIdProj(projId)
                    .withNameProj(name_projects)
                    .withCost(cost);

            projects.add(project);
        }
        resultSet.close();
        statement.close();
        connection.close();
        return projects;
    }

    @Override
    public List<Project> getByCustId(Long custId) throws SQLException {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT "+ProjectColumnName.id_projects+", "+ProjectColumnName.name_projects+", "+ProjectColumnName.cost
                +" FROM "+TableNames.project+" p, "+TableNames.customer_project+" cp WHERE p."+ProjectColumnName.id_projects
                +" = cp."+CustomerProjectColumnName.id_project+" AND cp."+CustomerProjectColumnName.id_customer+" = ?";

        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, custId);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Project project = new Project();
            Long projId = resultSet.getLong(ProjectColumnName.id_projects.name());
            String name_projects = resultSet.getString(ProjectColumnName.name_projects.name());
            Integer cost = resultSet.getInt(ProjectColumnName.cost.name());

            project.withIdProj(projId)
                    .withNameProj(name_projects)
                    .withCost(cost);

            projects.add(project);
        }
        resultSet.close();
        statement.close();
        connection.close();
        return projects;
    }

    @Override
    public List<Project> getByCompId(Long compId) throws SQLException {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT "+ProjectColumnName.id_projects+", "+ProjectColumnName.name_projects+", "+ProjectColumnName.cost
                +" FROM "+TableNames.project+" p, "+TableNames.compani_project+" cp WHERE p."+ProjectColumnName.id_projects
                +" = cp."+CompaniProjectColumnName.id_project+" AND cp."+CompaniProjectColumnName.id_compani+" = ?";

        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, compId);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Project project = new Project();
            Long projId = resultSet.getLong(ProjectColumnName.id_projects.name());
            String name_projects = resultSet.getString(ProjectColumnName.name_projects.name());
            Integer cost = resultSet.getInt(ProjectColumnName.cost.name());

            project.withIdProj(projId)
                    .withNameProj(name_projects)
                    .withCost(cost);

            projects.add(project);
        }
        resultSet.close();
        statement.close();
        connection.close();
        return projects;
    }

    @Override
    public List<Project> getAll() throws SQLException {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT * FROM "+TableNames.project+"";
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Project project = new Project();
            Long projId = resultSet.getLong(ProjectColumnName.id_projects.name());
            String name_projects = resultSet.getString(ProjectColumnName.name_projects.name());
            Integer cost = resultSet.getInt(ProjectColumnName.cost.name());

            project.withIdProj(projId)
                    .withNameProj(name_projects)
                    .withCost(cost);

            projects.add(project);
        }
        resultSet.close();
        statement.close();
        connection.close();
        return projects;
    }

    @Override
    public void save(Project project) throws SQLException {
        String sql = "INSERT INTO "+TableNames.project+" ("+ProjectColumnName.id_projects+", "+ProjectColumnName.name_projects+", "+ProjectColumnName.cost+") VALUES " +
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
        String sql = "UPDATE "+TableNames.project+" SET "+ProjectColumnName.id_projects+" = "+project.getId()+", "+ProjectColumnName.name_projects+" ='"+project.getName()+
                "', "+ProjectColumnName.cost+" = "+ project.getCost() +" WHERE "+ProjectColumnName.id_projects+" = " + project.getId();
        System.out.println(sql);
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        statement.close();
        connection.close();
    }

    @Override
    public void delete(Project project) throws SQLException {
        String sqlDelDevRefProj = "DELETE FROM "+TableNames.project_developer+" WHERE "+ProjectDeveloperColumnName.id_project+" = " + project.getId();
        String sqlDelCustRefProj = "DELETE FROM "+TableNames.customer_project+" WHERE "+CustomerProjectColumnName.id_project+" = " + project.getId();
        String sqlDelCompRefProj = "DELETE FROM "+TableNames.compani_project+" WHERE "+CompaniProjectColumnName.id_project+" = " + project.getId();
        String sqlDelProj = "DELETE FROM "+TableNames.project+" WHERE "+ProjectColumnName.id_projects+" = " + project.getId();
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
