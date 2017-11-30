package net.proselyte.hibernate.dao.jdbc;


import net.proselyte.hibernate.dao.CompanieDAO;
import net.proselyte.hibernate.dao.ConnectionUtil;
import net.proselyte.hibernate.model.Companie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nastya on 20.11.2017.
 */
public class JdbcCompanieImpl implements CompanieDAO {
    private Statement statement;


    @Override
    public Companie getById(Long id) throws SQLException {
        String sql = "SELECT * FROM companie WHERE id_companies = " + id;
        Statement statement = ConnectionUtil.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);



        if (resultSet.next()) {
            Companie companie = new Companie();
            Long companiId = resultSet.getLong("id_companies");
            String nameCompanies = resultSet.getString("name_companies");

            companie.withId_comp(companiId)
                    .withNameComp(nameCompanies);
            return companie;
        }else {
            System.out.println("No company with this ID!!!");
        }

        return null;
    }

    @Override
    public List<Companie> getAll() throws SQLException {
        List<Companie> companies = new ArrayList<>();
        String sql = "SELECT * FROM companie";
        Statement statement = ConnectionUtil.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Companie companie = new Companie();
            Long companieId = resultSet.getLong("id_companies");
            String nameCompanie = resultSet.getString("name_companies");

            companie.withId_comp(companieId)
                    .withNameComp(nameCompanie);

            companies.add(companie);
        }

        return companies;
    }

    @Override
    public void save(Companie companie) throws SQLException {
        String sql = "INSERT INTO companie (id_companies, name_companies) VALUES " +
                "("+companie.getId_comp()+ ",'" + companie.getName_comp()+"')";
        System.out.println(sql);
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        statement.close();
        connection.close();
    }

    @Override
    public void update(Companie companie) throws SQLException {
        String sql = "UPDATE companie SET id_companies = "+companie.getId_comp()+", name_companies='"+companie.getName_comp()
                +"' WHERE id_companies = " + companie.getId_comp();
        System.out.println(sql);
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        statement.close();
        connection.close();
    }

    @Override
    public void delete(Companie companie) throws SQLException {
        String sqlDelCompRefProj = "DELETE FROM compani_project WHERE id_compani = " + companie.getId_comp();
        String sqlDelComp = "DELETE FROM companie WHERE id_companies = " + companie.getId_comp();
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sqlDelCompRefProj);
        statement.executeUpdate(sqlDelComp);
        statement.close();
        connection.close();
    }
}
