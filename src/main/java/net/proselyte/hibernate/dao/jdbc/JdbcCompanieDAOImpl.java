package net.proselyte.hibernate.dao.jdbc;


import net.proselyte.hibernate.dao.CompanieDAO;
import net.proselyte.hibernate.dao.constants.CompanyColumnNames;
import net.proselyte.hibernate.dao.ConnectionUtil;
import net.proselyte.hibernate.dao.constants.TableNames;
import net.proselyte.hibernate.dao.constants.CompaniProjectColumnName;
import net.proselyte.hibernate.model.Companie;

import java.io.IOException;
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
//        String sql = "SELECT * FROM " + TableNames.companie + " WHERE " + CompanyColumnNames.id_companies + " = ?";
        String sql = String.format("SELECT * FROM %s WHERE %s = ?", TableNames.companie, CompanyColumnNames.id_companies);

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionUtil.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Companie companie = new Companie();
                Long companiId = resultSet.getLong(CompanyColumnNames.id_companies.name());
                String nameCompanies = resultSet.getString(CompanyColumnNames.name_companies.name());

                companie.withId_comp(companiId)
                        .withNameComp(nameCompanies);

                return companie;
            } else {
                System.out.println("No company with this ID!!!");
            }

        } finally {
            JdbcUtils.closeResources(connection, statement, resultSet);
        }
        return null;

    }


    @Override
    public List<Companie> getAll() throws SQLException {
        List<Companie> companies = new ArrayList<>();
        String sql = "SELECT * FROM " + TableNames.companie + "";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
        connection = ConnectionUtil.getConnection();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Companie companie = new Companie();
            Long companieId = resultSet.getLong(CompanyColumnNames.id_companies.name());
            String nameCompanie = resultSet.getString(CompanyColumnNames.name_companies.name());

            companie.withId_comp(companieId)
                    .withNameComp(nameCompanie);

            companies.add(companie);
        }

        } finally {
            JdbcUtils.closeResources(connection, statement, resultSet);
        }
        return null;
    }

    @Override
    public List<Companie> getByProjId(Long projId) throws SQLException {
        List<Companie> companies = new ArrayList<>();
        String sql = "SELECT c." + CompanyColumnNames.id_companies + ", " + CompanyColumnNames.name_companies
                + " FROM " + TableNames.companie + " c, " + TableNames.compani_project + " cp WHERE c." + CompanyColumnNames.id_companies
                + " = cp." + CompaniProjectColumnName.id_compani + " AND cp." + CompaniProjectColumnName.id_project + " = ?";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
        connection = ConnectionUtil.getConnection();
        statement = connection.prepareStatement(sql);
        statement.setLong(1, projId);
        resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Companie companie = new Companie();
            Long companieId = resultSet.getLong(CompanyColumnNames.id_companies.name());
            String nameCompanie = resultSet.getString(CompanyColumnNames.name_companies.name());

            companie.withId_comp(companieId)
                    .withNameComp(nameCompanie);

            companies.add(companie);
        }

        } finally {
            JdbcUtils.closeResources(connection, statement, resultSet);
        }
        return null;
    }

    @Override
    public void save(Companie companie) throws SQLException {
        String sql = "INSERT INTO " + TableNames.companie + " (" + CompanyColumnNames.id_companies + ", " + CompanyColumnNames.name_companies + ") VALUES " +
                "(" + companie.getIdComp() + ",'" + companie.getNameComp() + "')";
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
    public void update(Companie companie) throws SQLException {
        String sql = "UPDATE " + TableNames.companie + " SET " + CompanyColumnNames.id_companies + " = " + companie.getIdComp() + ", " + CompanyColumnNames.name_companies + "='" + companie.getNameComp()
                + "' WHERE " + CompanyColumnNames.id_companies + " = " + companie.getIdComp();
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
    public void delete(Companie companie) throws SQLException {
        String sqlDelCompRefProj = "DELETE FROM " + TableNames.compani_project + " WHERE " + CompaniProjectColumnName.id_compani + " = " + companie.getIdComp();
        String sqlDelComp = "DELETE FROM " + TableNames.companie + " WHERE " + CompanyColumnNames.id_companies + " = " + companie.getIdComp();

        Connection connection = null;
        Statement statement = null;
        try {
        connection = ConnectionUtil.getConnection();
        statement = connection.createStatement();
        statement.executeUpdate(sqlDelCompRefProj);
        statement.executeUpdate(sqlDelComp);
        } finally {
            JdbcUtils.closeResources(connection, statement);
        }
    }
}
