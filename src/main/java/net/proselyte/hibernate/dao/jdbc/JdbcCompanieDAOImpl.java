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
//        String sql = "SELECT * FROM " + TableNames.COMPANIE + " WHERE " + CompanyColumnNames.ID_COMPANIES + " = ?";
        String sql = String.format("SELECT * FROM %s WHERE %s = ?", TableNames.COMPANIE, CompanyColumnNames.ID_COMPANIES);

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
                Long companiId = resultSet.getLong(CompanyColumnNames.ID_COMPANIES.name());
                String nameCompanies = resultSet.getString(CompanyColumnNames.NAME_COMPANIES.name());

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
            String sql = String.format( "SELECT * FROM %s ", TableNames.COMPANIE);

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
        connection = ConnectionUtil.getConnection();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Companie companie = new Companie();
            Long companieId = resultSet.getLong(CompanyColumnNames.ID_COMPANIES.name());
            String nameCompanie = resultSet.getString(CompanyColumnNames.NAME_COMPANIES.name());

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
//        String sql = "SELECT c." + CompanyColumnNames.ID_COMPANIES + ", " + CompanyColumnNames.NAME_COMPANIES
//                + " FROM " + TableNames.COMPANIE + " c, " + TableNames.COMPANI_PROJECT + " cp
// WHERE c." + CompanyColumnNames.ID_COMPANIES
//                + " = cp." + CompaniProjectColumnName.ID_COMPANI + " AND cp." + CompaniProjectColumnName.ID_PROJECT + " = ?";

        String sql = String.format( "SELECT с.%s, %s FROM %s с, %s cp WHERE c.%s = cp.%s AND cp.%s = ?",
                CompanyColumnNames.ID_COMPANIES,
                CompanyColumnNames.NAME_COMPANIES,
                TableNames.COMPANIE,
                TableNames.COMPANI_PROJECT,
                CompanyColumnNames.ID_COMPANIES,
                CompaniProjectColumnName.ID_COMPANI,
                CompaniProjectColumnName.ID_PROJECT);

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
            Long companieId = resultSet.getLong(CompanyColumnNames.ID_COMPANIES.name());
            String nameCompanie = resultSet.getString(CompanyColumnNames.NAME_COMPANIES.name());

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
//        String sql = "INSERT INTO " + TableNames.COMPANIE + " (" + CompanyColumnNames.ID_COMPANIES + ", " + CompanyColumnNames.NAME_COMPANIES + ")
// VALUES " + "(" + companie.getIdComp() + ",'" + companie.getNameComp() + "')";
        String sql = String.format("INSERT INTO %s (%s, %s) VALUES (?, ?)",
                TableNames.COMPANIE,
                CompanyColumnNames.ID_COMPANIES,
                CompanyColumnNames.NAME_COMPANIES);
        System.out.println(sql);

        Connection connection = null;
        PreparedStatement statement = null;
        try {
        connection = ConnectionUtil.getConnection();
        statement = connection.prepareStatement(sql);
        statement.setLong(1, companie.getIdComp());
        statement.setString(2, companie.getNameComp());
        statement.executeUpdate();
        } finally {
            JdbcUtils.closeResources(connection, statement);
        }

    }

    @Override
    public void update(Companie companie) throws SQLException {
//        String sql = "UPDATE " + TableNames.COMPANIE + "
// SET " + CompanyColumnNames.ID_COMPANIES + " = " + companie.getIdComp() + ", "
// + CompanyColumnNames.NAME_COMPANIES + "='" + companie.getNameComp()
//                + "' WHERE " + CompanyColumnNames.ID_COMPANIES + " = " + companie.getIdComp();
        String sql = String.format("UPDATE %s SET %s = ?, %s = ? WHERE %s = ?",
                TableNames.COMPANIE,
                CompanyColumnNames.ID_COMPANIES,
                CompanyColumnNames.NAME_COMPANIES,
                CompanyColumnNames.ID_COMPANIES);
        System.out.println(sql);

        Connection connection = null;
        PreparedStatement statement = null;
        try {
        connection = ConnectionUtil.getConnection();
        statement = connection.prepareStatement(sql);
        statement.setLong(1, companie.getIdComp());
        statement.setString(2, companie.getNameComp());
        statement.setLong(3, companie.getIdComp());
        statement.executeUpdate();
    } finally {
            JdbcUtils.closeResources(connection, statement);
    }

    }

    @Override
    public void delete(Companie companie) throws SQLException {
//        String sqlDelCompRefProj = "DELETE FROM " + TableNames.COMPANI_PROJECT + " WHERE " + CompaniProjectColumnName.ID_COMPANI + " = " + companie.getIdComp();
        String sqlDelCompRefProj = String.format("DELETE FROM %s WHERE %s = ?",
                TableNames.COMPANI_PROJECT,
                CompaniProjectColumnName.ID_COMPANI);
        //String sqlDelComp = "DELETE FROM " + TableNames.COMPANIE + " WHERE " + CompanyColumnNames.ID_COMPANIES + " = " + companie.getIdComp();
        String sqlDelComp = String.format("DELETE FROM %s WHERE %s = ?",
                TableNames.COMPANIE,
                CompanyColumnNames.ID_COMPANIES);
        Connection connection = null;
        PreparedStatement statement = null;
        try {
        connection = ConnectionUtil.getConnection();
        statement = connection.prepareStatement(sqlDelCompRefProj);
        statement.setLong(1, companie.getIdComp());
        statement.executeUpdate();
        statement = connection.prepareStatement(sqlDelComp);
        statement.setLong(1, companie.getIdComp());
        statement.executeUpdate();
        } finally {
            JdbcUtils.closeResources(connection, statement);
        }
    }
}
