package net.proselyte.hibernate.dao.hibernate;

import net.proselyte.hibernate.dao.DeveloperDAO;
import net.proselyte.hibernate.model.Developer;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class HibernateDeveloperDAOImpl implements DeveloperDAO {

    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public void save(Developer developer) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(developer);

        transaction.commit();
        session.close();
    }

    public void update(Developer developer) throws SQLException {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.update(developer);
        transaction.commit();
        session.close();
    }

    public Developer getById(Long id) {
        Session session = this.sessionFactory.openSession();
        Developer developer = session.get(Developer.class, id);
        session.close();
        return developer;
    }

    public void delete(Developer developer) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(developer);
        transaction.commit();
        session.close();
    }

    public List<Developer> getAll() {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("SELECT d.lastName FROM Developer d");
        List<Developer> result = query.list();
        session.close();
        return result;
    }

    public List<Developer> getAllBySpecialty(String specialty) {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("FROM Developer d where d.specialty= :specialty");
        query.setParameter("specialty", specialty);
        List<Developer> result = query.list();
        session.close();
        return result;
    }

    public List<Developer> getDeveloperWithSalaryAbove(BigDecimal salary) {
        Session session = this.sessionFactory.openSession();

        Criteria criteria = session.createCriteria(Developer.class);
        criteria.add(Restrictions.gt("salary", salary));

        List<Developer> result = criteria.list();
        session.close();

        return result;
    }

    public List<Developer> getAllDeveloperSQL() {
        Session session = this.sessionFactory.openSession();

        SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM developer");
        sqlQuery.addEntity(Developer.class);
        List<Developer> result = sqlQuery.list();

        session.close();
        return result;
    }
}
