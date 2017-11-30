package net.proselyte.hibernate.dao.hibernate;

import net.proselyte.hibernate.dao.SkillDAO;
import net.proselyte.hibernate.model.Skill;
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
public class HibernateSkiiDAOImpl implements SkillDAO {
    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public void save(Skill skill) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(skill);

        transaction.commit();
        session.close();
    }

    public void update(Skill skill) throws SQLException {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.update(skill);
        transaction.commit();
        session.close();
    }

    public Skill getById(Long id) {
        Session session = this.sessionFactory.openSession();
        Skill skill = session.get(Skill.class, id);
        session.close();
        return skill;
    }

    public void delete(Skill skill) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(skill);
        transaction.commit();
        session.close();
    }

    public List<Skill> getAll() {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("FROM Skill s");
        List<Skill> result = query.list();
        session.close();
        return result;
    }
}
