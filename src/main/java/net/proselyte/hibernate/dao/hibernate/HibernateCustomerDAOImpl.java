package net.proselyte.hibernate.dao.hibernate;

import net.proselyte.hibernate.dao.CustomerDAO;
import net.proselyte.hibernate.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Nastya on 27.11.2017.
 */
public class HibernateCustomerDAOImpl implements CustomerDAO {
    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public void save(Customer customer) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(customer);

        transaction.commit();
        session.close();
    }

    public void update(Customer customer) throws SQLException {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.update(customer);
        transaction.commit();
        session.close();
    }

    public Customer getById(Long id) {
        Session session = this.sessionFactory.openSession();
        Customer customer = session.get(Customer.class, id);
        session.close();
        return customer;
    }

    public void delete(Customer customer) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(customer);
        transaction.commit();
        session.close();
    }

    public List<Customer> getAll() {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("FROM Customer c");
        List<Customer> result = query.list();
        session.close();
        return result;
}
}
