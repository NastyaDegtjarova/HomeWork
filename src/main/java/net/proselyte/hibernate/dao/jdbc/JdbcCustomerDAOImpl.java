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
        String sql = "SELECT * FROM "+ TableNames.customer+" WHERE "+ CustomerColumnNames.id_customers+" = ?";
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();

        Customer customer = new Customer();

        if (resultSet.next()) {
            Long customerId = resultSet.getLong(CustomerColumnNames.id_customers.name());
            String firstNameCustomer = resultSet.getString(CustomerColumnNames.first_name_customers.name());
            String lastNameCustomer = resultSet.getString(CustomerColumnNames.last_name_customers.name());

            customer.withIdCust(customerId)
                    .withFirstNameCust(firstNameCustomer)
                    .withLastNameCust(lastNameCustomer);
        }else {
            System.out.println("No customer with this ID!!!");
        }
        resultSet.close();
        statement.close();
        connection.close();
        return customer;
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM "+TableNames.customer+"";
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Customer customer = new Customer();
            Long customerId = resultSet.getLong(CustomerColumnNames.id_customers.name());
            String firstNameCustomer = resultSet.getString(CustomerColumnNames.first_name_customers.name());
            String lastNameCustomer = resultSet.getString(CustomerColumnNames.last_name_customers.name());

            customer.withIdCust(customerId)
                    .withFirstNameCust(firstNameCustomer)
                    .withLastNameCust(lastNameCustomer);

            customers.add(customer);
        }
        resultSet.close();
        statement.close();
        connection.close();
        return customers;
    }

    @Override
    public List<Customer> getByProjId(Long projId) throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT c."+CustomerColumnNames.id_customers+", "+CustomerColumnNames.first_name_customers+", "
                +CustomerColumnNames.last_name_customers+" FROM "+TableNames.customer+" c, "+TableNames.customer_project
                +" cp WHERE c."+CustomerColumnNames.id_customers+" = cp."+ CustomerProjectColumnName.id_customer+" AND cp."
                +CustomerProjectColumnName.id_project+" = ?";
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, projId);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Customer customer = new Customer();
            Long customerId = resultSet.getLong(CustomerColumnNames.id_customers.name());
            String firstNameCustomer = resultSet.getString(CustomerColumnNames.first_name_customers.name());
            String lastNameCustomer = resultSet.getString(CustomerColumnNames.last_name_customers.name());

            customer.withIdCust(customerId)
                    .withFirstNameCust(firstNameCustomer)
                    .withLastNameCust(lastNameCustomer);

            customers.add(customer);
        }
        resultSet.close();
        statement.close();
        connection.close();
        return customers;
    }

    @Override
    public void save(Customer customer) throws SQLException {
        String sql = "INSERT INTO "+TableNames.customer+" ("+CustomerColumnNames.id_customers+", "+CustomerColumnNames.first_name_customers+", "+CustomerColumnNames.last_name_customers+") VALUES " +
                "("+customer.getIdCustomer()+ ",'" + customer.getFirstNameCust()+"','" + customer.getLastNameCust()+"')";
        System.out.println(sql);
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        statement.close();
        connection.close();
    }

    @Override
    public void update(Customer customer) throws SQLException {
        String sql = "UPDATE "+TableNames.customer+" SET "+CustomerColumnNames.id_customers+" = "+customer.getIdCustomer()+", "+CustomerColumnNames.first_name_customers+"='"+customer.getFirstNameCust()
                +"', "+CustomerColumnNames.last_name_customers+" = '"+customer.getLastNameCust()+"' WHERE "+CustomerColumnNames.id_customers+" = " + customer.getIdCustomer();
        System.out.println(sql);
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);

        statement.close();
        connection.close();
    }

    @Override
    public void delete(Customer customer) throws SQLException {
        String sqlDelCustRefProj = "DELETE FROM "+TableNames.customer_project+" WHERE "+CustomerProjectColumnName.id_customer+" = " + customer.getIdCustomer();
        String sqlDelCust = "DELETE FROM "+TableNames.customer+" WHERE "+CustomerColumnNames.id_customers+" = " + customer.getIdCustomer();
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sqlDelCustRefProj);
        statement.executeUpdate(sqlDelCust);

        statement.close();
        connection.close();
    }
}
