package net.proselyte.hibernate.dao.jdbc;

import net.proselyte.hibernate.dao.ConnectionUtil;
import net.proselyte.hibernate.dao.CustomerDAO;
import net.proselyte.hibernate.model.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nastya on 20.11.2017.
 */
public class JdbcCustomerImpl implements CustomerDAO {
    private Statement statement;


    @Override
    public Customer getById(Long id) throws SQLException {
        String sql = "SELECT * FROM customer WHERE id_customers = " + id;
        Statement statement = ConnectionUtil.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        Customer customer = new Customer();

        if (resultSet.next()) {
            Long customerId = resultSet.getLong("id_customers");
            String firstNameCustomer = resultSet.getString("first_name_customers");
            String lastNameCustomer = resultSet.getString("last_name_customers");

            customer.withIdCust(customerId)
                    .withFirstNameCust(firstNameCustomer)
                    .withLastNameCust(lastNameCustomer);
        }else {
            System.out.println("No customer with this ID!!!");
        }

        return customer;
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer";
        Statement statement = ConnectionUtil.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Customer customer = new Customer();
            Long customerId = resultSet.getLong("id_customer");
            String firstNameCustomer = resultSet.getString("first_name_customers");
            String lastNameCustomer = resultSet.getString("last_name_customers");

            customer.withIdCust(customerId)
                    .withFirstNameCust(firstNameCustomer)
                    .withLastNameCust(lastNameCustomer);

            customers.add(customer);
        }

        return customers;
    }

    @Override
    public void save(Customer customer) throws SQLException {
        String sql = "INSERT INTO customer (id_customers, first_name_customers, last_name_customers) VALUES " +
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
        String sql = "UPDATE customer SET id_customers = "+customer.getIdCustomer()+", first_name_customers='"+customer.getFirstNameCust()
                +"', last_name_customers = '"+customer.getLastNameCust()+"' WHERE id_customers = " + customer.getIdCustomer();
        System.out.println(sql);
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        statement.close();
        connection.close();
    }

    @Override
    public void delete(Customer customer) throws SQLException {
        String sqlDelCustRefProj = "DELETE FROM customer_project WHERE id_customer = " + customer.getIdCustomer();
        String sqlDelCust = "DELETE FROM customer WHERE id_customers = " + customer.getIdCustomer();
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sqlDelCustRefProj);
        statement.executeUpdate(sqlDelCust);
        statement.close();
        connection.close();
    }
}
