package net.proselyte.hibernate.dao;

import net.proselyte.hibernate.model.Project;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Nastya on 20.11.2017.
 */
public interface ProjectDAO extends GenericDAO<Project, Long> {

    List<Project> getByDevId(Long id) throws SQLException;

    List<Project> getByCustId(Long id) throws SQLException;

    List<Project> getByCompId(Long id) throws SQLException;
}
