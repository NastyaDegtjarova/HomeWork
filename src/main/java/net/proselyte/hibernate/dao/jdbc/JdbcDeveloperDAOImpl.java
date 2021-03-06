package net.proselyte.hibernate.dao.jdbc;

import net.proselyte.hibernate.dao.ConnectionUtil;
import net.proselyte.hibernate.dao.DeveloperDAO;
import net.proselyte.hibernate.dao.constants.TableNames;
import net.proselyte.hibernate.dao.constants.DeveloperColumnNames;
import net.proselyte.hibernate.dao.constants.DeveloperSkillColumnName;
import net.proselyte.hibernate.dao.constants.ProjectDeveloperColumnName;
import net.proselyte.hibernate.dao.constants.SkillColumnNames;
import net.proselyte.hibernate.model.Developer;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nastya on 19.11.2017.
 */
public class JdbcDeveloperDAOImpl implements DeveloperDAO {
    private static JdbcDeveloperDAOImpl instance = new JdbcDeveloperDAOImpl();

    private JdbcDeveloperDAOImpl() {
    }

    public static DeveloperDAO getInstance() {
        return instance;
    }

    @Override
    public Developer getById(Long id) throws SQLException {
        String sql = String.format("SELECT * FROM %s WHERE %s = ?",
                TableNames.DEVELOPER,
                DeveloperColumnNames.ID_DEVELOPER);

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionUtil.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Developer developer = new Developer();
                Long developerId = resultSet.getLong(DeveloperColumnNames.ID_DEVELOPER.name());
                String firstName = resultSet.getString(DeveloperColumnNames.FIRST_NAME.name());
                String lastName = resultSet.getString(DeveloperColumnNames.LAST_NAME.name());
                BigDecimal salary = resultSet.getBigDecimal(DeveloperColumnNames.SALARY.name());

                developer.withId(developerId)
                        .withFirstName(firstName)
                        .withLastName(lastName)
                        .withSalary(salary);
                return developer;
            } else {
                System.out.println("No DEVELOPER with this ID!!!");
            }

        } finally {
            JdbcUtils.closeResources(connection, statement, resultSet);
        }
        return null;
    }

    @Override
    public List<Developer> getAll() throws SQLException {
        List<Developer> developers = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s ",
                TableNames.DEVELOPER);

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Developer developer = new Developer();
                Long developerId = resultSet.getLong(DeveloperColumnNames.ID_DEVELOPER.name());
                String firstName = resultSet.getString(DeveloperColumnNames.FIRST_NAME.name());
                String lastName = resultSet.getString(DeveloperColumnNames.LAST_NAME.name());
                BigDecimal salary = resultSet.getBigDecimal(DeveloperColumnNames.SALARY.name());

                developer.withId(developerId)
                        .withFirstName(firstName)
                        .withLastName(lastName)
                        .withSalary(salary);

                developers.add(developer);
            }

        } finally {
            JdbcUtils.closeResources(connection, statement, resultSet);
        }
        return developers;
    }

    @Override
    public List<Developer> getByProjId(Long projId) throws SQLException {
        List<Developer> developers = new ArrayList<>();
//        String sql = "SELECT d."+DeveloperColumnNames.ID_DEVELOPER +", "+DeveloperColumnNames.FIRST_NAME +", "+
//                DeveloperColumnNames.LAST_NAME +", "+DeveloperColumnNames.SALARY +" FROM "+TableNames.DEVELOPER
//                +" d, "+TableNames.PROJECT_DEVELOPER +" pd WHERE d."+DeveloperColumnNames.ID_DEVELOPER +" = pd."
//                + ProjectDeveloperColumnName.ID_DEVELOPER +" AND pd."+ProjectDeveloperColumnName.ID_PROJECT +" = ?";

        String sql = String.format("SELECT d.%s, %s, %s, %s FROM %s d, %s pd WHERE d.%s = pd.%s AND pd.%s = ?",
                DeveloperColumnNames.ID_DEVELOPER,
                DeveloperColumnNames.FIRST_NAME,
                DeveloperColumnNames.LAST_NAME,
                DeveloperColumnNames.SALARY,
                TableNames.DEVELOPER,
                TableNames.PROJECT_DEVELOPER,
                DeveloperColumnNames.ID_DEVELOPER,
                ProjectDeveloperColumnName.ID_DEVELOPER,
                ProjectDeveloperColumnName.ID_PROJECT);
        System.out.println(sql);

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionUtil.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, projId);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Developer developer = new Developer();
                Long developerId = resultSet.getLong(DeveloperColumnNames.ID_DEVELOPER.name());
                String firstName = resultSet.getString(DeveloperColumnNames.FIRST_NAME.name());
                String lastName = resultSet.getString(DeveloperColumnNames.LAST_NAME.name());
                BigDecimal salary = resultSet.getBigDecimal(DeveloperColumnNames.SALARY.name());

                developer.withId(developerId)
                        .withFirstName(firstName)
                        .withLastName(lastName)
                        .withSalary(salary);

                developers.add(developer);
            }

        } finally {
            JdbcUtils.closeResources(connection, statement, resultSet);
        }
        return null;
    }

    @Override
    public List<Developer> getBySkillId(Long skillId) throws SQLException {
        List<Developer> developers = new ArrayList<>();
//        String sql = "SELECT s."+ SkillColumnNames.ID_SKILLS +", "+SkillColumnNames.SPECIALTY +" FROM "+TableNames.SKILL
//                +" s, "+TableNames.DEVELOPER_SKILL +" ds WHERE s."+SkillColumnNames.ID_SKILLS +" = ds."+
//                DeveloperSkillColumnName.ID_SKILL +" AND ds."+DeveloperSkillColumnName.ID_DEVELOPER +" = ?";

        String sql = String.format("SELECT s.%s, %s FROM %s s, %s ds WHERE s.%s = ds.%s AND ds.%s = ?",
                SkillColumnNames.ID_SKILLS,
                SkillColumnNames.SPECIALTY,
                TableNames.SKILL,
                TableNames.DEVELOPER_SKILL,
                SkillColumnNames.ID_SKILLS,
                DeveloperSkillColumnName.ID_SKILL,
                DeveloperSkillColumnName.ID_DEVELOPER);
        System.out.println(sql);

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionUtil.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, skillId);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Developer developer = new Developer();
                Long developerId = resultSet.getLong(DeveloperColumnNames.ID_DEVELOPER.name());
                String firstName = resultSet.getString(DeveloperColumnNames.FIRST_NAME.name());
                String lastName = resultSet.getString(DeveloperColumnNames.LAST_NAME.name());
                BigDecimal salary = resultSet.getBigDecimal(DeveloperColumnNames.SALARY.name());

                developer.withId(developerId)
                        .withFirstName(firstName)
                        .withLastName(lastName)
                        .withSalary(salary);

                developers.add(developer);
            }

        } finally {
            JdbcUtils.closeResources(connection, statement, resultSet);
        }
        return null;
    }

    @Override
    public void save(Developer developer) throws SQLException {
//        String sql = "INSERT INTO "+TableNames.DEVELOPER +" ("+DeveloperColumnNames.ID_DEVELOPER +",
//      "+DeveloperColumnNames.FIRST_NAME +", "+DeveloperColumnNames.LAST_NAME +", "+DeveloperColumnNames.SALARY +") VALUES " +
//                "("+developer.getId()+ ",'" + developer.getFirstName()+"','"+developer.getLastName()+"',"+developer.getSalary()+")";

        String sql = String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES (?, ?, ?, ?)",
                TableNames.DEVELOPER,
                DeveloperColumnNames.ID_DEVELOPER,
                DeveloperColumnNames.FIRST_NAME,
                DeveloperColumnNames.LAST_NAME,
                DeveloperColumnNames.SALARY);
        System.out.println(sql);

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionUtil.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, developer.getId());
            statement.setString(2, developer.getFirstName());
            statement.setString(3, developer.getLastName());
            statement.setBigDecimal(4, developer.getSalary());
            statement.executeUpdate();
        } finally {
            JdbcUtils.closeResources(connection, statement);
        }

    }

    @Override
    public void update(Developer developer) throws SQLException {
//        String sql = "UPDATE "+TableNames.DEVELOPER +" SET "+DeveloperColumnNames.ID_DEVELOPER +" = "
//          +developer.getId()+", "+DeveloperColumnNames.FIRST_NAME +"='"+developer.getFirstName()+
//                "', "+DeveloperColumnNames.LAST_NAME +" = '"+developer.getLastName()+"', "+DeveloperColumnNames.SALARY +" = "
//                  +developer.getSalary()+" WHERE "+DeveloperColumnNames.ID_DEVELOPER +" = " +
//                developer.getId();

        String sql = String.format("UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ? WHERE %s = ?",
                TableNames.DEVELOPER,
                DeveloperColumnNames.ID_DEVELOPER,
                DeveloperColumnNames.FIRST_NAME,
                DeveloperColumnNames.LAST_NAME,
                DeveloperColumnNames.SALARY,
                DeveloperColumnNames.ID_DEVELOPER);
        System.out.println(sql);

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionUtil.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, developer.getId());
            statement.setString(2, developer.getFirstName());
            statement.setString(3, developer.getLastName());
            statement.setBigDecimal(4, developer.getSalary());
            statement.setLong(5, developer.getId());
            statement.executeUpdate();
        } finally {
            JdbcUtils.closeResources(connection, statement);
        }
    }

    @Override
    public void delete(Developer developer) throws SQLException {
//        String sqlDelDevRefSkill = "DELETE FROM "+TableNames.DEVELOPER_SKILL +" WHERE "+DeveloperSkillColumnName.ID_DEVELOPER +" = " + developer.getId();
//        String sqlDelDevRefProj = "DELETE FROM "+TableNames.PROJECT_DEVELOPER +" WHERE "+ProjectDeveloperColumnName.ID_DEVELOPER +" = " + developer.getId();
//        String sqlDelDev = "DELETE FROM "+TableNames.DEVELOPER +" WHERE "+DeveloperColumnNames.ID_DEVELOPER +" = " + developer.getId();

        String sqlDelDevRefSkill = String.format("DELETE FROM %s WHERE %s = ?",
                TableNames.DEVELOPER_SKILL,
                DeveloperSkillColumnName.ID_DEVELOPER);
        String sqlDelDevRefProj = String.format("DELETE FROM %s WHERE %s = ?",
                TableNames.PROJECT_DEVELOPER,
                ProjectDeveloperColumnName.ID_DEVELOPER);
        String sqlDelDev = String.format("DELETE FROM %s WHERE %s = ?",
                TableNames.DEVELOPER,
                DeveloperColumnNames.ID_DEVELOPER);

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionUtil.getConnection();
            statement = connection.prepareStatement(sqlDelDevRefSkill);
            statement.setLong(1, developer.getId());
            statement.executeUpdate();
            statement = connection.prepareStatement(sqlDelDevRefProj);
            statement.setLong(1, developer.getId());
            statement.executeUpdate();
            statement = connection.prepareStatement(sqlDelDev);
            statement.setLong(1, developer.getId());
            statement.executeUpdate();

        } finally {
            JdbcUtils.closeResources(connection, statement);
        }
    }
}
