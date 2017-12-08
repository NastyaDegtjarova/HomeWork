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
        String sql = String.format("SELECT * FROM %s WHERE %s = ?", TableNames.SKILL, SkillColumnNames.ID_SKILLS);

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
        connection = ConnectionUtil.getConnection();
        statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Skill skill = new Skill();
            Long skillId = resultSet.getLong(SkillColumnNames.ID_SKILLS.name());
            String specialty = resultSet.getString(SkillColumnNames.SPECIALTY.name());

            skill.withIdSkill(skillId)
                    .withSpecialty(specialty);
            return skill;
        }else {
            System.out.println("No SKILL with this ID!!!");
        }
        } finally {
            JdbcUtils.closeResources(connection, statement, resultSet);
        }
        return null;
    }

    @Override
    public List<Skill> getAll() throws SQLException {
        List<Skill> skills = new ArrayList<>();
        String sql = String.format( "SELECT * FROM %s ",TableNames.SKILL);

            Connection connection = null;
            Statement statement = null;
            ResultSet resultSet = null;
            try {
        connection = ConnectionUtil.getConnection();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Skill skill = new Skill();
            Long skillId = resultSet.getLong(SkillColumnNames.ID_SKILLS.name());
            String specialty = resultSet.getString(SkillColumnNames.SPECIALTY.name());

            skill.withIdSkill(skillId)
                    .withSpecialty(specialty);

            skills.add(skill);
        }
            } finally {
                JdbcUtils.closeResources(connection, statement, resultSet);
            }
        return null;
    }

    @Override
    public void save(Skill skill) throws SQLException {
        String sql = "INSERT INTO "+TableNames.SKILL +" ("+SkillColumnNames.ID_SKILLS +", "+SkillColumnNames.SPECIALTY +") VALUES " +
                "("+skill.getIdSkill()+ ",'" + skill.getSpecialty()+"')";
        System.out.println(sql);

        Connection connection = null;
        Statement statement = null;
        try {
        connection = ConnectionUtil.getConnection();
        statement = connection.createStatement();
        statement.executeUpdate(sql);
                } finally {
                    JdbcUtils.closeResources(connection, statement);
                }

    }

    @Override
    public void update(Skill skill) throws SQLException {
        String sql = "UPDATE "+TableNames.SKILL +" SET "+SkillColumnNames.ID_SKILLS +" = "+skill.getIdSkill()+", "+SkillColumnNames.SPECIALTY +"='"+skill.getSpecialty()
                +"' WHERE "+SkillColumnNames.ID_SKILLS +" = " + skill.getIdSkill();
        System.out.println(sql);

           Connection connection = null;
           Statement statement = null;

           try {
        connection = ConnectionUtil.getConnection();
        statement = connection.createStatement();
        statement.executeUpdate(sql);
           } finally {
               JdbcUtils.closeResources(connection, statement);
           }
    }

    @Override
    public void delete(Skill skill) throws SQLException {
        String sqlDelDevRefSkill = "DELETE FROM "+TableNames.DEVELOPER_SKILL +" WHERE "+ DeveloperSkillColumnName.ID_SKILL +" = " + skill.getIdSkill();
        String sqlDelDev = "DELETE FROM "+TableNames.SKILL +" WHERE "+SkillColumnNames.ID_SKILLS +" = " + skill.getIdSkill();

        Connection connection = null;
        Statement statement = null;

        try {
        connection = ConnectionUtil.getConnection();
        statement = connection.createStatement();
        statement.executeUpdate(sqlDelDevRefSkill);
        statement.executeUpdate(sqlDelDev);
    } finally {
        JdbcUtils.closeResources(connection, statement);
    }

    }

    @Override
    public List<Skill> getByDevId(Long id) throws SQLException {
        List<Skill> skills = new ArrayList<>();
        String sql = "SELECT * FROM "+TableNames.SKILL +" ";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
        connection = ConnectionUtil.getConnection();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Skill skill = new Skill();
            Long skillId = resultSet.getLong(SkillColumnNames.ID_SKILLS.name());
            String specialty = resultSet.getString(SkillColumnNames.SPECIALTY.name());

            skill.withIdSkill(skillId)
                    .withSpecialty(specialty);

            skills.add(skill);
        }
    } finally {
        JdbcUtils.closeResources(connection, statement, resultSet);
        }
        return null;
    }
}
