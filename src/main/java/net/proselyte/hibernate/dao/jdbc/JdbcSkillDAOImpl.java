package net.proselyte.hibernate.dao.jdbc;


import net.proselyte.hibernate.dao.ConnectionUtil;
import net.proselyte.hibernate.dao.SkillDAO;
import net.proselyte.hibernate.model.Skill;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nastya on 20.11.2017.
 */
public class JdbcSkillDAOImpl implements SkillDAO {
    private Statement statement;


    @Override
    public Skill getById(Long id) throws SQLException {
        String sql = "SELECT * FROM skill WHERE id_skills = " + id;
        Statement statement = ConnectionUtil.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);



        if (resultSet.next()) {
            Skill skill = new Skill();
            Long skillId = resultSet.getLong("id_skills");
            String specialty = resultSet.getString("specialty");

            skill.withId_skill(skillId)
                    .withSpecialty(specialty);
            return skill;
        }else {
            System.out.println("No skill with this ID!!!");
        }

        return null;
    }

    @Override
    public List<Skill> getAll() throws SQLException {
        List<Skill> skills = new ArrayList<>();
        String sql = "SELECT * FROM skill";
        Statement statement = ConnectionUtil.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Skill skill = new Skill();
            Long skillId = resultSet.getLong("id_skills");
            String specialty = resultSet.getString("specialty");

            skill.withId_skill(skillId)
                    .withSpecialty(specialty);

            skills.add(skill);
        }

        return skills;
    }

    @Override
    public void save(Skill skill) throws SQLException {
        String sql = "INSERT INTO skill (id_skills, specialty) VALUES " +
                "("+skill.getId_skill()+ ",'" + skill.getSpecialty()+"')";
        System.out.println(sql);
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        statement.close();
        connection.close();
    }

    @Override
    public void update(Skill skill) throws SQLException {
        String sql = "UPDATE skill SET id_skills = "+skill.getId_skill()+", specialty='"+skill.getSpecialty()
                +"' WHERE id_skills = " + skill.getId_skill();
        System.out.println(sql);
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        statement.close();
        connection.close();
    }

    @Override
    public void delete(Skill skill) throws SQLException {
        String sqlDelDevRefSkill = "DELETE FROM developer_skill WHERE id_skill = " + skill.getId_skill();
        String sqlDelDev = "DELETE FROM skill WHERE id_skills = " + skill.getId_skill();
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sqlDelDevRefSkill);
        statement.executeUpdate(sqlDelDev);
        statement.close();
        connection.close();
    }
}