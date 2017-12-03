package net.proselyte.hibernate.dao.jdbc;


import net.proselyte.hibernate.dao.ConnectionUtil;
import net.proselyte.hibernate.dao.SkillDAO;
import net.proselyte.hibernate.dao.constants.TableNames;
import net.proselyte.hibernate.dao.constants.DeveloperSkillColumnName;
import net.proselyte.hibernate.dao.constants.SkillColumnNames;
import net.proselyte.hibernate.model.Skill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nastya on 20.11.2017.
 */
public class JdbcSkillDAOImpl implements SkillDAO {
    private Statement statement;
    private static JdbcSkillDAOImpl instance = new JdbcSkillDAOImpl();

    private JdbcSkillDAOImpl() {
    }

    public static SkillDAO getInstance() {
        return instance;
    }

    @Override
    public Skill getById(Long id) throws SQLException {
        String sql = "SELECT * FROM "+ TableNames.skill+" WHERE "+ SkillColumnNames.id_skills+" = ?";
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Skill skill = new Skill();
            Long skillId = resultSet.getLong(SkillColumnNames.id_skills.name());
            String specialty = resultSet.getString(SkillColumnNames.specialty.name());

            skill.withIdSkill(skillId)
                    .withSpecialty(specialty);
            return skill;
        }else {
            System.out.println("No skill with this ID!!!");
        }
        resultSet.close();
        statement.close();
        connection.close();
        return null;
    }

    @Override
    public List<Skill> getAll() throws SQLException {
        List<Skill> skills = new ArrayList<>();
        String sql = "SELECT * FROM "+TableNames.skill+"";
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Skill skill = new Skill();
            Long skillId = resultSet.getLong(SkillColumnNames.id_skills.name());
            String specialty = resultSet.getString(SkillColumnNames.specialty.name());

            skill.withIdSkill(skillId)
                    .withSpecialty(specialty);

            skills.add(skill);
        }
        resultSet.close();
        statement.close();
        connection.close();
        return skills;
    }

    @Override
    public void save(Skill skill) throws SQLException {
        String sql = "INSERT INTO "+TableNames.skill+" ("+SkillColumnNames.id_skills+", "+SkillColumnNames.specialty+") VALUES " +
                "("+skill.getIdSkill()+ ",'" + skill.getSpecialty()+"')";
        System.out.println(sql);
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        statement.close();
        connection.close();
    }

    @Override
    public void update(Skill skill) throws SQLException {
        String sql = "UPDATE "+TableNames.skill+" SET "+SkillColumnNames.id_skills+" = "+skill.getIdSkill()+", "+SkillColumnNames.specialty+"='"+skill.getSpecialty()
                +"' WHERE "+SkillColumnNames.id_skills+" = " + skill.getIdSkill();
        System.out.println(sql);
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        statement.close();
        connection.close();
    }

    @Override
    public void delete(Skill skill) throws SQLException {
        String sqlDelDevRefSkill = "DELETE FROM "+TableNames.developer_skill+" WHERE "+ DeveloperSkillColumnName.id_skill+" = " + skill.getIdSkill();
        String sqlDelDev = "DELETE FROM "+TableNames.skill+" WHERE "+SkillColumnNames.id_skills+" = " + skill.getIdSkill();
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sqlDelDevRefSkill);
        statement.executeUpdate(sqlDelDev);
        statement.close();
        connection.close();
    }

    @Override
    public List<Skill> getByDevId(Long id) throws SQLException {
        List<Skill> skills = new ArrayList<>();
        String sql = "SELECT * FROM "+TableNames.skill+" ";
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Skill skill = new Skill();
            Long skillId = resultSet.getLong(SkillColumnNames.id_skills.name());
            String specialty = resultSet.getString(SkillColumnNames.specialty.name());

            skill.withIdSkill(skillId)
                    .withSpecialty(specialty);

            skills.add(skill);
        }
        resultSet.close();
        statement.close();
        connection.close();
        return skills;
    }
}
