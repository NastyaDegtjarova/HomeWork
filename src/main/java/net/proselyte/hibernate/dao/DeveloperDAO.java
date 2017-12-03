package net.proselyte.hibernate.dao;


import net.proselyte.hibernate.model.Developer;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Nastya on 19.11.2017.
 */
public interface DeveloperDAO extends GenericDAO<Developer, Long> {
    List<Developer> getByProjId(Long projId) throws SQLException;

    List<Developer> getBySkillId(Long skillId) throws SQLException;
}
