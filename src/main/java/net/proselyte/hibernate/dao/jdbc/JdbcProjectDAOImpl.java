package net.proselyte.hibernate.dao.jdbc;


import net.proselyte.hibernate.dao.ConnectionUtil;
import net.proselyte.hibernate.dao.ProjectDAO;
import net.proselyte.hibernate.dao.constants.TableNames;
import net.proselyte.hibernate.dao.constants.CompaniProjectColumnName;
import net.proselyte.hibernate.dao.constants.CustomerProjectColumnName;
import net.proselyte.hibernate.dao.constants.ProjectColumnName;
import net.proselyte.hibernate.dao.constants.ProjectDeveloperColumnName;
import net.proselyte.hibernate.model.Project;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nastya on 20.11.2017.
 */
public class JdbcProjectDAOImpl implements ProjectDAO {
    private Statement statement;
    private static JdbcProjectDAOImpl instance = new JdbcProjectDAOImpl();

    private JdbcProjectDAOImpl() {
    }

    public static ProjectDAO getInstance() {
        return instance;
    }

    @Override
    public Project getById(Long id) throws SQLException {
        String sql = String.format("SELECT * FROM %s WHERE %s = ?",
                TableNames.PROJECT,
                ProjectColumnName.ID_PROJECTS );

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
        connection = ConnectionUtil.getConnection();
        statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Project project = new Project();
            Long projId = resultSet.getLong(ProjectColumnName.ID_PROJECTS.name());
            String name_projects = resultSet.getString(ProjectColumnName.NAME_PROJECTS.name());
            Integer cost = resultSet.getInt(ProjectColumnName.COST.name());

            project.withIdProj(projId)
                    .withNameProj(name_projects)
                    .withCost(cost);
            return project;
        }else {
                System.out.println("No PROJECT with this ID!!!");
            }
    } finally {
        JdbcUtils.closeResources(connection, statement, resultSet);
    }
        return null;
    }

    @Override
    public List<Project> getByDevId(Long devId) throws SQLException {
        List<Project> projects = new ArrayList<>();
//        String sql = "SELECT "+ProjectColumnName.ID_PROJECTS +", "+ProjectColumnName.NAME_PROJECTS +", "+ProjectColumnName.COST
//                +" FROM "+TableNames.PROJECT +" p, "+TableNames.PROJECT_DEVELOPER +" pd WHERE p."+ProjectColumnName.ID_PROJECTS
//                +" = pd."+ ProjectDeveloperColumnName.ID_PROJECT +" AND pd."+ProjectDeveloperColumnName.ID_DEVELOPER +" = ?";

        String sql = String.format("SELECT p.%s, %s, %s FROM %s p, %s pd WHERE p.%s = pd.%s AND pd.%s = ?",
                ProjectColumnName.ID_PROJECTS,
                ProjectColumnName.NAME_PROJECTS,
                ProjectColumnName.COST,
                TableNames.PROJECT,
                TableNames.PROJECT_DEVELOPER,
                ProjectColumnName.ID_PROJECTS,
                ProjectDeveloperColumnName.ID_PROJECT,
                ProjectDeveloperColumnName.ID_DEVELOPER);

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
        connection = ConnectionUtil.getConnection();
        statement = connection.prepareStatement(sql);
        statement.setLong(1, devId);
        resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Project project = new Project();
            Long projId = resultSet.getLong(ProjectColumnName.ID_PROJECTS.name());
            String name_projects = resultSet.getString(ProjectColumnName.NAME_PROJECTS.name());
            Integer cost = resultSet.getInt(ProjectColumnName.COST.name());

            project.withIdProj(projId)
                    .withNameProj(name_projects)
                    .withCost(cost);

            projects.add(project);
        }
    } finally {
        JdbcUtils.closeResources(connection, statement, resultSet);
        }
        return projects;
    }

    @Override
    public List<Project> getByCustId(Long custId) throws SQLException {
        List<Project> projects = new ArrayList<>();
//        String sql = "SELECT "+ProjectColumnName.ID_PROJECTS +", "+ProjectColumnName.NAME_PROJECTS +", "+ProjectColumnName.COST
//                +" FROM "+TableNames.PROJECT +" p, "+TableNames.CUSTOMER_PROJECT +" cp WHERE p."+ProjectColumnName.ID_PROJECTS
//                +" = cp."+ CustomerProjectColumnName.ID_PROJECT +" AND cp."+CustomerProjectColumnName.ID_CUSTOMER +" = ?";

        String sql = String.format("SELECT p.%s, %s, %s FROM %s p, %s cp WHERE p.%s = cp.%s AND cp.%s = ?",
                ProjectColumnName.ID_PROJECTS,
                ProjectColumnName.NAME_PROJECTS,
                ProjectColumnName.COST,
                TableNames.PROJECT,
                TableNames.CUSTOMER_PROJECT,
                ProjectColumnName.ID_PROJECTS,
                CustomerProjectColumnName.ID_PROJECT,
                CustomerProjectColumnName.ID_CUSTOMER);

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
        connection = ConnectionUtil.getConnection();
        statement = connection.prepareStatement(sql);
        statement.setLong(1, custId);
        resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Project project = new Project();
            Long projId = resultSet.getLong(ProjectColumnName.ID_PROJECTS.name());
            String name_projects = resultSet.getString(ProjectColumnName.NAME_PROJECTS.name());
            Integer cost = resultSet.getInt(ProjectColumnName.COST.name());

            project.withIdProj(projId)
                    .withNameProj(name_projects)
                    .withCost(cost);

            projects.add(project);
        }
        } finally {
        JdbcUtils.closeResources(connection, statement, resultSet);
        }
        return projects;
    }

    @Override
    public List<Project> getByCompId(Long compId) throws SQLException {
        List<Project> projects = new ArrayList<>();
//        String sql = "SELECT "+ProjectColumnName.ID_PROJECTS +", "+ProjectColumnName.NAME_PROJECTS +", "+ProjectColumnName.COST
//                +" FROM "+TableNames.PROJECT +" p, "+TableNames.COMPANI_PROJECT +" cp WHERE p."+ProjectColumnName.ID_PROJECTS
//                +" = cp."+ CompaniProjectColumnName.ID_PROJECT +" AND cp."+CompaniProjectColumnName.ID_COMPANI +" = ?";

        String sql = String.format("SELECT p.%s, %s, %s FROM %s p, %s cp WHERE p.%s = cp.%s AND cp.%s = ?",
                ProjectColumnName.ID_PROJECTS,
                ProjectColumnName.NAME_PROJECTS,
                ProjectColumnName.COST,
                TableNames.PROJECT,
                TableNames.COMPANI_PROJECT,
                ProjectColumnName.ID_PROJECTS,
                CompaniProjectColumnName.ID_PROJECT,
                CompaniProjectColumnName.ID_COMPANI);

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
        connection = ConnectionUtil.getConnection();
        statement = connection.prepareStatement(sql);
        statement.setLong(1, compId);
        resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Project project = new Project();
            Long projId = resultSet.getLong(ProjectColumnName.ID_PROJECTS.name());
            String name_projects = resultSet.getString(ProjectColumnName.NAME_PROJECTS.name());
            Integer cost = resultSet.getInt(ProjectColumnName.COST.name());

            project.withIdProj(projId)
                    .withNameProj(name_projects)
                    .withCost(cost);

            projects.add(project);
        }
        } finally {
        JdbcUtils.closeResources(connection, statement, resultSet);
        }
        return projects;
    }

    @Override
    public List<Project> getAll() throws SQLException {
        List<Project> projects = new ArrayList<>();
        String sql = String.format( "SELECT * FROM %s ",
                TableNames.PROJECT );

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
        connection = ConnectionUtil.getConnection();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Project project = new Project();
            Long projId = resultSet.getLong(ProjectColumnName.ID_PROJECTS.name());
            String name_projects = resultSet.getString(ProjectColumnName.NAME_PROJECTS.name());
            Integer cost = resultSet.getInt(ProjectColumnName.COST.name());

            project.withIdProj(projId)
                    .withNameProj(name_projects)
                    .withCost(cost);

            projects.add(project);
        }
        } finally {
        JdbcUtils.closeResources(connection, statement, resultSet);
        }
        return projects;
    }

    @Override
    public void save(Project project) throws SQLException {
//        String sql = "INSERT INTO "+TableNames.PROJECT +" ("+ProjectColumnName.ID_PROJECTS +", "
//          +ProjectColumnName.NAME_PROJECTS +", "+ProjectColumnName.COST +") VALUES " +
//                "("+project.getId()+ ",'" + project.getName()+"',"+project.getCost()+")";

        String sql = String.format("INSERT INTO %s (%s, %s, %s) VALUES (?, ?, ?)",
                TableNames.PROJECT,
                ProjectColumnName.ID_PROJECTS,
                ProjectColumnName.NAME_PROJECTS,
                ProjectColumnName.COST);
        System.out.println(sql);

        Connection connection = null;
        PreparedStatement statement = null;
        try {
        connection = ConnectionUtil.getConnection();
        statement = connection.prepareStatement(sql);
            statement.setLong(1, project.getId());
            statement.setString(2, project.getName());
            statement.setLong(3, project.getCost());
        statement.executeUpdate();
        } finally {
        JdbcUtils.closeResources(connection, statement);
        }

    }

    @Override
    public void update(Project project) throws SQLException {
//        String sql = "UPDATE "+TableNames.PROJECT +" SET "+ProjectColumnName.ID_PROJECTS +" = "+project.getId()+", "
//          +ProjectColumnName.NAME_PROJECTS +" ='"+project.getName()+
//                "', "+ProjectColumnName.COST +" = "+ project.getCost() +" WHERE "+ProjectColumnName.ID_PROJECTS +" = "
//                  + project.getId();

        String sql = String.format("UPDATE %s SET %s = ?, %s = ?, %s = ? WHERE %s = ?",
                TableNames.PROJECT,
                ProjectColumnName.ID_PROJECTS,
                ProjectColumnName.NAME_PROJECTS,
                ProjectColumnName.COST,
                ProjectColumnName.ID_PROJECTS);
        System.out.println(sql);

        Connection connection = null;
        PreparedStatement statement = null;
        try {
        connection = ConnectionUtil.getConnection();
        statement = connection.prepareStatement(sql);
            statement.setLong(1, project.getId());
            statement.setString(2, project.getName());
            statement.setLong(3, project.getCost());
            statement.setLong(4, project.getId());
        statement.executeUpdate();
        } finally {
        JdbcUtils.closeResources(connection, statement);
        }
    }

    @Override
    public void delete(Project project) throws SQLException {
//        String sqlDelDevRefProj = "DELETE FROM "+TableNames.PROJECT_DEVELOPER +" WHERE "+ProjectDeveloperColumnName.ID_PROJECT +" = " + project.getId();
//        String sqlDelCustRefProj = "DELETE FROM "+TableNames.CUSTOMER_PROJECT +" WHERE "+CustomerProjectColumnName.ID_PROJECT +" = " + project.getId();
//        String sqlDelCompRefProj = "DELETE FROM "+TableNames.COMPANI_PROJECT +" WHERE "+CompaniProjectColumnName.ID_PROJECT +" = " + project.getId();
//        String sqlDelProj = "DELETE FROM "+TableNames.PROJECT +" WHERE "+ProjectColumnName.ID_PROJECTS +" = " + project.getId();

        String sqlDelDevRefProj = String.format("DELETE FROM %s WHERE %s = ?",
                TableNames.PROJECT_DEVELOPER,
                ProjectDeveloperColumnName.ID_PROJECT);
        String sqlDelCustRefProj = String.format("DELETE FROM %s WHERE %s = ?",
                TableNames.CUSTOMER_PROJECT,
                CustomerProjectColumnName.ID_PROJECT);
        String sqlDelCompRefProj = String.format("DELETE FROM %s WHERE %s = ?",
                TableNames.COMPANI_PROJECT,
                CompaniProjectColumnName.ID_PROJECT);
        String sqlDelProj = String.format("DELETE FROM %s WHERE %s = ?",
                TableNames.PROJECT,
                ProjectColumnName.ID_PROJECTS);

        Connection connection = null;
        PreparedStatement statement = null;
        try {
        connection = ConnectionUtil.getConnection();
        statement = connection.prepareStatement(sqlDelDevRefProj);
            statement.setLong(1, project.getId());
            statement.executeUpdate();
        statement = connection.prepareStatement(sqlDelCustRefProj);
            statement.setLong(1, project.getId());
            statement.executeUpdate();
        statement = connection.prepareStatement(sqlDelCompRefProj);
            statement.setLong(1, project.getId());
            statement.executeUpdate();
        statement = connection.prepareStatement(sqlDelProj);
            statement.setLong(1, project.getId());
        statement.executeUpdate();

        } finally {
        JdbcUtils.closeResources(connection, statement);
        }
    }
}
