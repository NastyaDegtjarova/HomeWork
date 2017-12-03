package net.proselyte.hibernate.dao;

import net.proselyte.hibernate.dao.GenericDAO;
import net.proselyte.hibernate.model.Customer;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Nastya on 21.11.2017.
 */
public interface CustomerDAO  extends GenericDAO<Customer, Long> {
    List<Customer> getByProjId(Long projId) throws SQLException;
}
