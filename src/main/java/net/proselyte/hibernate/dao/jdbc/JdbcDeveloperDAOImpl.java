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
        String sql = "SELECT * FROM "+ TableNames.developer+" WHERE "+ DeveloperColumnNames.id_developer+" = ?";
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Developer developer = new Developer();
            Long developerId = resultSet.getLong(DeveloperColumnNames.id_developer.name());
            String firstName = resultSet.getString(DeveloperColumnNames.first_name.name());
            String lastName = resultSet.getString(DeveloperColumnNames.last_name.name());
            BigDecimal salary = resultSet.getBigDecimal(DeveloperColumnNames.salary.name());

            developer.withId(developerId)
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withSalary(salary);
            return developer;
        }else {
            System.out.println("No developer with this ID!!!");
        }

        resultSet.close();
        statement.close();
        connection.close();
        return null;
    }

    @Override
    public List<Developer> getAll() throws SQLException {
        List<Developer> developers = new ArrayList<>();
        String sql = "SELECT * FROM "+TableNames.developer+"";
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Developer developer = new Developer();
            Long developerId = resultSet.getLong(DeveloperColumnNames.id_developer.name());
            String firstName = resultSet.getString(DeveloperColumnNames.first_name.name());
            String lastName = resultSet.getString(DeveloperColumnNames.last_name.name());
            BigDecimal salary = resultSet.getBigDecimal(DeveloperColumnNames.salary.name());

            developer.withId(developerId)
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withSalary(salary);

            developers.add(developer);
        }

        resultSet.close();
        statement.close();
        connection.close();
        return developers;
    }

    @Override
    public List<Developer> getByProjId(Long projId) throws SQLException {
        List<Developer> developers = new ArrayList<>();
        String sql = "SELECT d."+DeveloperColumnNames.id_developer+", "+DeveloperColumnNames.first_name+", "+
                DeveloperColumnNames.last_name+", "+DeveloperColumnNames.salary+" FROM "+TableNames.developer
                +" d, "+TableNames.project_developer+" pd WHERE d."+DeveloperColumnNames.id_developer+" = pd."
                + ProjectDeveloperColumnName.id_developer+" AND pd."+ProjectDeveloperColumnName.id_project+" = ?";
            System.out.println(sql);
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, projId);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Developer developer = new Developer();
            Long developerId = resultSet.getLong(DeveloperColumnNames.id_developer.name());
            String firstName = resultSet.getString(DeveloperColumnNames.first_name.name());
            String lastName = resultSet.getString(DeveloperColumnNames.last_name.name());
            BigDecimal salary = resultSet.getBigDecimal(DeveloperColumnNames.salary.name());

            developer.withId(developerId)
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withSalary(salary);

            developers.add(developer);
        }

        resultSet.close();
        statement.close();
        connection.close();
        return developers;
    }

    @Override
    public List<Developer> getBySkillId(Long skillId) throws SQLException {
        List<Developer> developers = new ArrayList<>();
        String sql = "SELECT s."+ SkillColumnNames.id_skills+", "+SkillColumnNames.specialty+" FROM "+TableNames.skill
                +" s, "+TableNames.developer_skill+" ds WHERE s."+SkillColumnNames.id_skills+" = ds."+
                DeveloperSkillColumnName.id_skill+" AND ds."+DeveloperSkillColumnName.id_developer+" = ?";
            System.out.println(sql);
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, skillId);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Developer developer = new Developer();
            Long developerId = resultSet.getLong(DeveloperColumnNames.id_developer.name());
            String firstName = resultSet.getString(DeveloperColumnNames.first_name.name());
            String lastName = resultSet.getString(DeveloperColumnNames.last_name.name());
            BigDecimal salary = resultSet.getBigDecimal(DeveloperColumnNames.salary.name());

            developer.withId(developerId)
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withSalary(salary);

            developers.add(developer);
        }

        resultSet.close();
        statement.close();
        connection.close();
        return developers;
    }

    @Override
    public void save(Developer developer) throws SQLException {
        String sql = "INSERT INTO "+TableNames.developer+" ("+DeveloperColumnNames.id_developer+", "+DeveloperColumnNames.first_name+", "+DeveloperColumnNames.last_name+", "+DeveloperColumnNames.salary+") VALUES " +
                "("+developer.getId()+ ",'" + developer.getFirstName()+"','"+developer.getLastName()+"',"+developer.getSalary()+")";
        System.out.println(sql);
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        statement.close();
        connection.close();
    }

    @Override
    public void update(Developer developer) throws SQLException {
        String sql = "UPDATE "+TableNames.developer+" SET "+DeveloperColumnNames.id_developer+" = "+developer.getId()+", "+DeveloperColumnNames.first_name+"='"+developer.getFirstName()+
                "', "+DeveloperColumnNames.last_name+" = '"+developer.getLastName()+"', "+DeveloperColumnNames.salary+" = "+developer.getSalary()+" WHERE "+DeveloperColumnNames.id_developer+" = " +
                developer.getId();
        System.out.println(sql);
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        statement.close();
        connection.close();
    }

    @Override
    public void delete(Developer developer) throws SQLException {
        String sqlDelDevRefSkill = "DELETE FROM "+TableNames.developer_skill+" WHERE "+DeveloperSkillColumnName.id_developer+" = " + developer.getId();
        String sqlDelDevRefProj = "DELETE FROM "+TableNames.project_developer+" WHERE "+ProjectDeveloperColumnName.id_developer+" = " + developer.getId();
        String sqlDelDev = "DELETE FROM "+TableNames.developer+" WHERE "+DeveloperColumnNames.id_developer+" = " + developer.getId();
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sqlDelDevRefSkill);
        statement.executeUpdate(sqlDelDevRefProj);
        statement.executeUpdate(sqlDelDev);
        statement.close();
        connection.close();
    }
}
