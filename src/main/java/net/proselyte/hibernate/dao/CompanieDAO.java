package net.proselyte.hibernate.dao;

import net.proselyte.hibernate.dao.GenericDAO;
import net.proselyte.hibernate.model.Companie;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Nastya on 20.11.2017.
 */
public interface CompanieDAO extends GenericDAO<Companie, Long> {
    List<Companie> getByProjId(Long projId) throws SQLException;
}
