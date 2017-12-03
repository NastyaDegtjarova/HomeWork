package net.proselyte.hibernate.dao.jdbc;


import net.proselyte.hibernate.dao.CompanieDAO;
import net.proselyte.hibernate.dao.constants.CompanyColumnNames;
import net.proselyte.hibernate.dao.ConnectionUtil;
import net.proselyte.hibernate.dao.constants.TableNames;
import net.proselyte.hibernate.dao.constants.CompaniProjectColumnName;
import net.proselyte.hibernate.model.Companie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nastya on 20.11.2017.
 */
public class JdbcCompanieDAOImpl implements CompanieDAO {
    private Statement statement;

    private static JdbcCompanieDAOImpl instance = new JdbcCompanieDAOImpl();

    private JdbcCompanieDAOImpl() {
    }

    public static CompanieDAO getInstance() {
        return instance;
    }

    @Override
    public Companie getById(Long id) throws SQLException {
        String sql = "SELECT * FROM "+ TableNames.companie+" WHERE "+ CompanyColumnNames.id_companies+" = ?";
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();



        if (resultSet.next()) {
            Companie companie = new Companie();
            Long companiId = resultSet.getLong(CompanyColumnNames.id_companies.name());
            String nameCompanies = resultSet.getString(CompanyColumnNames.name_companies.name());

            companie.withId_comp(companiId)
                    .withNameComp(nameCompanies);
            resultSet.close();
            statement.close();
            connection.close();
            return companie;
        } else {
            System.out.println("No company with this ID!!!");
        }

        return null;
    }

    @Override
    public List<Companie> getAll() throws SQLException {
        List<Companie> companies = new ArrayList<>();
        String sql = "SELECT * FROM "+TableNames.companie+"";
        Connection connection = ConnectionUtil.getConnection();
        Statement statement =
                connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Companie companie = new Companie();
            Long companieId = resultSet.getLong(CompanyColumnNames.id_companies.name());
            String nameCompanie = resultSet.getString(CompanyColumnNames.name_companies.name());

            companie.withId_comp(companieId)
                    .withNameComp(nameCompanie);

            companies.add(companie);
        }

        resultSet.close();
        statement.close();
        connection.close();
        return companies;
    }

    @Override
    public List<Companie> getByProjId(Long projId) throws SQLException {
        List<Companie> companies = new ArrayList<>();
        String sql = "SELECT c."+CompanyColumnNames.id_companies+", "+CompanyColumnNames.name_companies
                +" FROM "+TableNames.companie+" c, "+TableNames.compani_project+" cp WHERE c."+CompanyColumnNames.id_companies
                +" = cp."+ CompaniProjectColumnName.id_compani+" AND cp."+CompaniProjectColumnName.id_project+" = ?";
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, projId);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Companie companie = new Companie();
            Long companieId = resultSet.getLong(CompanyColumnNames.id_companies.name());
            String nameCompanie = resultSet.getString(CompanyColumnNames.name_companies.name());

            companie.withId_comp(companieId)
                    .withNameComp(nameCompanie);

            companies.add(companie);
        }

        resultSet.close();
        statement.close();
        connection.close();
        return companies;
    }

    @Override
    public void save(Companie companie) throws SQLException {
        String sql = "INSERT INTO "+TableNames.companie+" ("+CompanyColumnNames.id_companies+", "+CompanyColumnNames.name_companies+") VALUES " +
                "("+companie.getIdComp()+ ",'" + companie.getNameComp()+"')";
        System.out.println(sql);
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        statement.close();
        connection.close();
    }

    @Override
    public void update(Companie companie) throws SQLException {
        String sql = "UPDATE "+TableNames.companie+" SET "+CompanyColumnNames.id_companies+" = "+companie.getIdComp()+", "+CompanyColumnNames.name_companies+"='"+companie.getNameComp()
                +"' WHERE "+CompanyColumnNames.id_companies+" = " + companie.getIdComp();
        System.out.println(sql);
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        statement.close();
        connection.close();
    }

    @Override
    public void delete(Companie companie) throws SQLException {
        String sqlDelCompRefProj = "DELETE FROM "+TableNames.compani_project+" WHERE "+CompaniProjectColumnName.id_compani+" = " + companie.getIdComp();
        String sqlDelComp = "DELETE FROM "+TableNames.companie+" WHERE "+CompanyColumnNames.id_companies+" = " + companie.getIdComp();
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sqlDelCompRefProj);
        statement.executeUpdate(sqlDelComp);
        statement.close();
        connection.close();
    }
}
