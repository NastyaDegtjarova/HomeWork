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
        String sql = String.format("SELECT * FROM %s WHERE %s = ?", TableNames.PROJECT, ProjectColumnName.ID_PROJECTS );

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
        String sql = "SELECT "+ProjectColumnName.ID_PROJECTS +", "+ProjectColumnName.NAME_PROJECTS +", "+ProjectColumnName.COST
                +" FROM "+TableNames.PROJECT +" p, "+TableNames.PROJECT_DEVELOPER +" pd WHERE p."+ProjectColumnName.ID_PROJECTS
                +" = pd."+ ProjectDeveloperColumnName.ID_PROJECT +" AND pd."+ProjectDeveloperColumnName.ID_DEVELOPER +" = ?";

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
        return null;
    }

    @Override
    public List<Project> getByCustId(Long custId) throws SQLException {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT "+ProjectColumnName.ID_PROJECTS +", "+ProjectColumnName.NAME_PROJECTS +", "+ProjectColumnName.COST
                +" FROM "+TableNames.PROJECT +" p, "+TableNames.CUSTOMER_PROJECT +" cp WHERE p."+ProjectColumnName.ID_PROJECTS
                +" = cp."+ CustomerProjectColumnName.ID_PROJECT +" AND cp."+CustomerProjectColumnName.ID_CUSTOMER +" = ?";

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
        return null;
    }

    @Override
    public List<Project> getByCompId(Long compId) throws SQLException {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT "+ProjectColumnName.ID_PROJECTS +", "+ProjectColumnName.NAME_PROJECTS +", "+ProjectColumnName.COST
                +" FROM "+TableNames.PROJECT +" p, "+TableNames.COMPANI_PROJECT +" cp WHERE p."+ProjectColumnName.ID_PROJECTS
                +" = cp."+ CompaniProjectColumnName.ID_PROJECT +" AND cp."+CompaniProjectColumnName.ID_COMPANI +" = ?";

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
        return null;
    }

    @Override
    public List<Project> getAll() throws SQLException {
        List<Project> projects = new ArrayList<>();
        String sql = String.format( "SELECT * FROM %s ", TableNames.PROJECT );

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
        return null;
    }

    @Override
    public void save(Project project) throws SQLException {
        String sql = "INSERT INTO "+TableNames.PROJECT +" ("+ProjectColumnName.ID_PROJECTS +", "+ProjectColumnName.NAME_PROJECTS +", "+ProjectColumnName.COST +") VALUES " +
                "("+project.getId()+ ",'" + project.getName()+"',"+project.getCost()+")";
        System.out.println(sql);

        Connection connection = null;
        Statement statement = null;
        try {
        connection = ConnectionUtil.getConnection();
        statement = connection.createStatement();
        statement.executeUpdate(sql);
        } finally {
        JdbcUtils.closeResources(connection, statement);
        }

    }

    @Override
    public void update(Project project) throws SQLException {
        String sql = "UPDATE "+TableNames.PROJECT +" SET "+ProjectColumnName.ID_PROJECTS +" = "+project.getId()+", "+ProjectColumnName.NAME_PROJECTS +" ='"+project.getName()+
                "', "+ProjectColumnName.COST +" = "+ project.getCost() +" WHERE "+ProjectColumnName.ID_PROJECTS +" = " + project.getId();
        System.out.println(sql);

        Connection connection = null;
        Statement statement = null;
        try {
        connection = ConnectionUtil.getConnection();
        statement = connection.createStatement();
        statement.executeUpdate(sql);
        } finally {
        JdbcUtils.closeResources(connection, statement);
        }
    }

    @Override
    public void delete(Project project) throws SQLException {
        String sqlDelDevRefProj = "DELETE FROM "+TableNames.PROJECT_DEVELOPER +" WHERE "+ProjectDeveloperColumnName.ID_PROJECT +" = " + project.getId();
        String sqlDelCustRefProj = "DELETE FROM "+TableNames.CUSTOMER_PROJECT +" WHERE "+CustomerProjectColumnName.ID_PROJECT +" = " + project.getId();
        String sqlDelCompRefProj = "DELETE FROM "+TableNames.COMPANI_PROJECT +" WHERE "+CompaniProjectColumnName.ID_PROJECT +" = " + project.getId();
        String sqlDelProj = "DELETE FROM "+TableNames.PROJECT +" WHERE "+ProjectColumnName.ID_PROJECTS +" = " + project.getId();

        Connection connection = null;
        Statement statement = null;
        try {
        connection = ConnectionUtil.getConnection();
        statement = connection.createStatement();
        statement.executeUpdate(sqlDelDevRefProj);
        statement.executeUpdate(sqlDelCustRefProj);
        statement.executeUpdate(sqlDelCompRefProj);
        statement.executeUpdate(sqlDelProj);
        } finally {
        JdbcUtils.closeResources(connection, statement);
        }
    }
}
