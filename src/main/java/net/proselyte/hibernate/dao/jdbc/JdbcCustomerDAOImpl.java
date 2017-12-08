package net.proselyte.hibernate.dao.jdbc;

import net.proselyte.hibernate.dao.ConnectionUtil;
import net.proselyte.hibernate.dao.constants.CustomerColumnNames;
import net.proselyte.hibernate.dao.CustomerDAO;
import net.proselyte.hibernate.dao.constants.TableNames;
import net.proselyte.hibernate.dao.constants.CustomerProjectColumnName;
import net.proselyte.hibernate.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nastya on 20.11.2017.
 */
public class JdbcCustomerDAOImpl implements CustomerDAO {
    private Statement statement;
    private static JdbcCustomerDAOImpl instance = new JdbcCustomerDAOImpl();

    private JdbcCustomerDAOImpl() {
    }

    public static CustomerDAO getInstance() {
        return instance;
    }

    @Override
    public Customer getById(Long id) throws SQLException {
        String sql = String.format("SELECT * FROM %s WHERE %s = ?",TableNames.CUSTOMER, CustomerColumnNames.ID_CUSTOMERS);

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionUtil.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();

            Customer customer = new Customer();

            if (resultSet.next()) {
                Long customerId = resultSet.getLong(CustomerColumnNames.ID_CUSTOMERS.name());
                String firstNameCustomer = resultSet.getString(CustomerColumnNames.FIRST_NAME_CUSTOMERS.name());
                String lastNameCustomer = resultSet.getString(CustomerColumnNames.LAST_NAME_CUSTOMERS.name());

                customer.withIdCust(customerId)
                        .withFirstNameCust(firstNameCustomer)
                        .withLastNameCust(lastNameCustomer);
                return customer;
            } else {
                System.out.println("No CUSTOMER with this ID!!!");

            }
        }finally{
                JdbcUtils.closeResources(connection, statement, resultSet);
        }
        return null;
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String sql = String.format( "SELECT * FROM %s ", TableNames.CUSTOMER );

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
        connection = ConnectionUtil.getConnection();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Customer customer = new Customer();
            Long customerId = resultSet.getLong(CustomerColumnNames.ID_CUSTOMERS.name());
            String firstNameCustomer = resultSet.getString(CustomerColumnNames.FIRST_NAME_CUSTOMERS.name());
            String lastNameCustomer = resultSet.getString(CustomerColumnNames.LAST_NAME_CUSTOMERS.name());

            customer.withIdCust(customerId)
                    .withFirstNameCust(firstNameCustomer)
                    .withLastNameCust(lastNameCustomer);

            customers.add(customer);
        }
             } finally {
            JdbcUtils.closeResources(connection, statement, resultSet);
             }
        return null;
    }

    @Override
    public List<Customer> getByProjId(Long projId) throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT c."+CustomerColumnNames.ID_CUSTOMERS +", "+CustomerColumnNames.FIRST_NAME_CUSTOMERS +", "
                +CustomerColumnNames.LAST_NAME_CUSTOMERS +" FROM "+TableNames.CUSTOMER +" c, "+TableNames.CUSTOMER_PROJECT
                +" cp WHERE c."+CustomerColumnNames.ID_CUSTOMERS +" = cp."+ CustomerProjectColumnName.ID_CUSTOMER +" AND cp."
                +CustomerProjectColumnName.ID_PROJECT +" = ?";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
        connection = ConnectionUtil.getConnection();
        statement = connection.prepareStatement(sql);
        statement.setLong(1, projId);
        resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Customer customer = new Customer();
            Long customerId = resultSet.getLong(CustomerColumnNames.ID_CUSTOMERS.name());
            String firstNameCustomer = resultSet.getString(CustomerColumnNames.FIRST_NAME_CUSTOMERS.name());
            String lastNameCustomer = resultSet.getString(CustomerColumnNames.LAST_NAME_CUSTOMERS.name());

            customer.withIdCust(customerId)
                    .withFirstNameCust(firstNameCustomer)
                    .withLastNameCust(lastNameCustomer);

            customers.add(customer);
        }
        } finally {
            JdbcUtils.closeResources(connection, statement, resultSet);
        }
        return null;
    }

    @Override
    public void save(Customer customer) throws SQLException {
        String sql = "INSERT INTO "+TableNames.CUSTOMER +" ("+CustomerColumnNames.ID_CUSTOMERS +", "+CustomerColumnNames.FIRST_NAME_CUSTOMERS +", "+CustomerColumnNames.LAST_NAME_CUSTOMERS +") VALUES " +
                "("+customer.getIdCustomer()+ ",'" + customer.getFirstNameCust()+"','" + customer.getLastNameCust()+"')";
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
    public void update(Customer customer) throws SQLException {
        String sql = "UPDATE "+TableNames.CUSTOMER +" SET "+CustomerColumnNames.ID_CUSTOMERS +" = "+customer.getIdCustomer()+", "+CustomerColumnNames.FIRST_NAME_CUSTOMERS +"='"+customer.getFirstNameCust()
                +"', "+CustomerColumnNames.LAST_NAME_CUSTOMERS +" = '"+customer.getLastNameCust()+"' WHERE "+CustomerColumnNames.ID_CUSTOMERS +" = " + customer.getIdCustomer();
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
    public void delete(Customer customer) throws SQLException {
        String sqlDelCustRefProj = "DELETE FROM "+TableNames.CUSTOMER_PROJECT +" WHERE "+CustomerProjectColumnName.ID_CUSTOMER +" = " + customer.getIdCustomer();
        String sqlDelCust = "DELETE FROM "+TableNames.CUSTOMER +" WHERE "+CustomerColumnNames.ID_CUSTOMERS +" = " + customer.getIdCustomer();

        Connection connection = null;
        Statement statement = null;
        try {
        connection = ConnectionUtil.getConnection();
        statement = connection.createStatement();
        statement.executeUpdate(sqlDelCustRefProj);
        statement.executeUpdate(sqlDelCust);

        } finally {
            JdbcUtils.closeResources(connection, statement);
        }
    }
}
