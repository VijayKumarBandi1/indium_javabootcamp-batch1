package com.indium.skilltrackerapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.indium.skilltrackerapp.enums.SkillCategory;
import com.indium.skilltrackerapp.model.Skill;

public class SkillDAOImpl implements SkillDAO {
	
	 private Connection conn = null;
	    private PreparedStatement pstmt = null;
	    private ResultSet rs = null;
	   
	   // static AssociateDAO associateDAO = new AssociateDAOImpl();

	    // Define SQL queries as variables with descriptive names
	    private static final String INSERT_SKILL_QUERY = "INSERT INTO skills(name, description, category, experience ,associate_id) VALUES(?, ?, ?, ?,?)";
	    private static final String UPDATE_SKILL_QUERY = "UPDATE skills SET name=?, description=?, category=?, experience=? WHERE id=?";
	    private static final String DELETE_SKILL_QUERY = "DELETE FROM skills WHERE id=? AND associate_id=?";
	    private static final String SELECT_SKILL_QUERY = "SELECT * FROM skills WHERE id=? AND associate_id=?";
	    private static final String SELECT_ALL_SKILLS_QUERY = "SELECT * FROM skills";
	    private static final String SELECT_SKILLS_BY_ASSOCIATE_QUERY = "SELECT * FROM skills WHERE associate_id=?";
	    private static final String DELETE_SKILL_With_ASSO_ID_QUERY = "DELETE FROM skills WHERE associate_id=?";

	    public SkillDAOImpl() {
	        conn = DatabaseConnection.getConnection();
	    }

	    @Override
	    public boolean addSkill(Skill skill,int id) {
	    	
	        boolean status = false;
	        int skillId = -1; 
	        SkillCategory category = null;
	        try {
	            pstmt = conn.prepareStatement(INSERT_SKILL_QUERY,Statement.RETURN_GENERATED_KEYS);
	            pstmt.setString(1, skill.name());
	            pstmt.setString(2, skill.description());
	            pstmt.setString(3, skill.category().name());
	            pstmt.setInt(4, skill.experience());
	            pstmt.setInt(5, id);

	            status = pstmt.executeUpdate() > 0;
	            if (status) {
	                ResultSet generatedKeys = pstmt.getGeneratedKeys();
	                if (generatedKeys.next()) {
	                    skillId = generatedKeys.getInt(1); 
	                }
	                category = skill.category();
	            }
	           
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return status;
	    }

	    @Override
	    public boolean updateSkill(Skill skill,int id) {
	        boolean status = false;
	        try {
	            pstmt = conn.prepareStatement(UPDATE_SKILL_QUERY);
	            pstmt.setString(1, skill.name());
	            pstmt.setString(2, skill.description());
	            pstmt.setString(3, skill.category().name());
	            pstmt.setInt(4, skill.experience());
	            pstmt.setInt(5, skill.id());

	            status = pstmt.executeUpdate() > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return status;
	    }

	    @Override
	    public boolean deleteSkill(int id,int assId) {
	        boolean status = false;
	        try {
	            pstmt = conn.prepareStatement(DELETE_SKILL_QUERY);
	            pstmt.setInt(1, id);
	            pstmt.setInt(2, assId);

	            status = pstmt.executeUpdate() > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return status;
	    }

	    @Override
	    public Skill getSkill(int skillId,int assId) {
	        Skill skill = null;
	        try {
	            pstmt = conn.prepareStatement(SELECT_SKILL_QUERY);
	            pstmt.setInt(1, skillId);
	            pstmt.setInt(2, assId); 

	            rs = pstmt.executeQuery();

	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String name = rs.getString("name");
	                String description = rs.getString("description");
	                String categoryStr = rs.getString("category");
	                int experience = rs.getInt("experience");
	                int associateId = rs.getInt("associate_id");
	                SkillCategory category= null;
	                 
	                switch (categoryStr) {
					case "Primary": 
						category = SkillCategory.Primary;
						break;
					case "Secondary":
						category = SkillCategory.Secondary;
						break;
	                }

	                skill = new Skill(id, name, description, category, experience,associateId);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return skill;
	    }

	    @Override
	    public List<Skill> getAllSkills() {
	        List<Skill> skills = new ArrayList<>();
	        try {
	            pstmt = conn.prepareStatement(SELECT_ALL_SKILLS_QUERY);

	            rs = pstmt.executeQuery();

	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String name = rs.getString("name");
	                String description = rs.getString("description");
	                String categoryStr = rs.getString("category");
	                int experience = rs.getInt("experience");
	                int associateId = rs.getInt("associate_id");
	                SkillCategory category= null;
	                 
	                switch (categoryStr) {
					case "Primary": 
						category = SkillCategory.Primary;
						break;
					case "Secondary":
						category = SkillCategory.Secondary;
						break;
	                }

	                skills.add(new Skill(id, name, description, category, experience,associateId));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return skills;
	    }

	    @Override
	    public List<Skill> getSkillsByAssociate(int associateId) {
	        List<Skill> skills = new ArrayList<>();
	        try {
	            pstmt = conn.prepareStatement(SELECT_SKILLS_BY_ASSOCIATE_QUERY);
	            pstmt.setInt(1, associateId);

	            rs = pstmt.executeQuery();

	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String name = rs.getString("name");
	                String description = rs.getString("description");
	                String categoryStr = rs.getString("category");
	                int experience = rs.getInt("experience");
	                SkillCategory category= null;
	                 
	                switch (categoryStr) {
					case "Primary": 
						category = SkillCategory.Primary;
						break;
					case "Secondary":
						category = SkillCategory.Secondary;
						break;
	                }

	                skills.add(new Skill(id, name, description, category, experience,associateId));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return skills;
	    }
	    
	    @Override
	    public boolean deleteSkillsByAssociateId(int assId) {
	    	 boolean status = false;
		        try {
		            pstmt = conn.prepareStatement(DELETE_SKILL_With_ASSO_ID_QUERY);
		            pstmt.setInt(1, assId);

		            status = pstmt.executeUpdate() > 0;
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        return status;
	    }

}
