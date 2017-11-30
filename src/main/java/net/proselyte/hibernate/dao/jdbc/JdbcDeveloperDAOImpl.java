package net.proselyte.hibernate.dao.jdbc;

import net.proselyte.hibernate.dao.ConnectionUtil;
import net.proselyte.hibernate.dao.DeveloperDAO;
import net.proselyte.hibernate.model.Developer;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nastya on 19.11.2017.
 */
public class JdbcDeveloperDAOImpl implements DeveloperDAO {
    @Override
    public Developer getById(Long id) throws SQLException {
        String sql = "SELECT * FROM developer WHERE id_developer = " + id;
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);



        if (resultSet.next()) {
            Developer developer = new Developer();
            Long developerId = resultSet.getLong("id_developer");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            BigDecimal salary = resultSet.getBigDecimal("salary");

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
        String sql = "SELECT * FROM developer";
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);



        while (resultSet.next()) {
            Developer developer = new Developer();
            Long developerId = resultSet.getLong("id_developer");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            BigDecimal salary = resultSet.getBigDecimal("salary");

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
        String sql = "INSERT INTO developer (id_developer, first_name, last_name, salary) VALUES " +
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
        String sql = "UPDATE developer SET id_developer = "+developer.getId()+", first_name='"+developer.getFirstName()+
                "', last_name = '"+developer.getLastName()+"', salary = "+developer.getSalary()+" WHERE id_developer = " +
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
        String sqlDelDevRefSkill = "DELETE FROM developer_skill WHERE id_developer = " + developer.getId();
        String sqlDelDevRefProj = "DELETE FROM project_developer WHERE id_developer = " + developer.getId();
        String sqlDelDev = "DELETE FROM developer WHERE id_developer = " + developer.getId();
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sqlDelDevRefSkill);
        statement.executeUpdate(sqlDelDevRefProj);
        statement.executeUpdate(sqlDelDev);
        statement.close();
        connection.close();
    }
}
