package net.proselyte.hibernate.dao;

import net.proselyte.hibernate.model.Skill;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Nastya on 20.11.2017.
 */
public interface SkillDAO extends GenericDAO<Skill, Long> {
    List<Skill> getByDevId(Long id) throws SQLException;
}
