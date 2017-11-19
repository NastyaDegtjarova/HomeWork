package dao.jdbc;

import dao.ConnectionUtil;
import dao.DeveloperDAO;
import model.Developer;

import java.math.BigDecimal;
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
        String sql = "SELECT * FROM developers WHERE id_developer = " + id;
        Statement statement = ConnectionUtil.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        Developer developer = new Developer();

        while (resultSet.next()) {
            Long developerId = resultSet.getLong("id_developer");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            //String specialty = resultSet.getString("specialty");
            BigDecimal salary = resultSet.getBigDecimal("salary_developers");

            developer.withId(developerId)
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withSalary(salary);
        }


        return developer;
    }

    @Override
    public List<Developer> getAll() throws SQLException {
        List<Developer> developers = new ArrayList<>();
        String sql = "SELECT * FROM developers";
        Statement statement = ConnectionUtil.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);



        while (resultSet.next()) {
            Developer developer = new Developer();
            Long developerId = resultSet.getLong("id_developer");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            //String specialty = resultSet.getString("specialty");
            BigDecimal salary = resultSet.getBigDecimal("salary_developers");

            developer.withId(developerId)
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withSalary(salary);

            developers.add(developer);
        }

        return developers;
    }

    @Override
    public void save(Developer developer) {

    }

    @Override
    public void update(Developer developer) {

    }

    @Override
    public void delete(Developer developer) {

    }
}
