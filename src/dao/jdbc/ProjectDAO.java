package dao.jdbc;

import dao.GenericDAO;
import model.Customer;
import model.Project;

/**
 * Created by Nastya on 20.11.2017.
 */
public interface ProjectDAO extends GenericDAO <Project, Long> {
    /**
     * Created by Nastya on 20.11.2017.
     */
    interface CustomerDAO extends GenericDAO<Customer, Long> {
    }
}
