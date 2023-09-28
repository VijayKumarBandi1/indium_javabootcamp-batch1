package com.indium.skilltrackerapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.indium.skilltrackerapp.SkillTrackerApp;
import com.indium.skilltrackerapp.enums.SkillCategory;
import com.indium.skilltrackerapp.model.Associate;
import com.indium.skilltrackerapp.model.Skill;
import com.indium.skilltrackerapp.service.SkillService;
import com.indium.skilltrackerapp.serviceimpl.SkillServiceImpl;

public class AssociateDAOImpl implements AssociateDAO {
	
	 private Connection conn = null;
	    private PreparedStatement pstmt = null;
	    private ResultSet rs = null;
	    SkillService skillService = new SkillServiceImpl();
	    

	    // Define SQL queries as variables with descriptive names
	    private static final String INSERT_ASSOCIATE_QUERY = "INSERT INTO associates(name, age, business_unit, email, location, create_time, update_time) VALUES(?, ?, ?, ?, ?, ?, ?)";
	    private static final String UPDATE_ASSOCIATE_QUERY = "UPDATE associates SET name=?, age=?, business_unit=?, email=?, location=?,update_time=? WHERE id=?";
	    private static final String DELETE_ASSOCIATE_QUERY = "DELETE FROM associates WHERE id=?";
	    private static final String SELECT_ASSOCIATE_QUERY = "SELECT * FROM associates WHERE id=?";
	    private static final String SELECT_ALL_ASSOCIATES_QUERY = "SELECT * FROM associates";
	    
	    public AssociateDAOImpl() {
	        conn = DatabaseConnection.getConnection();
	    }

	    @Override
	    public boolean addAssociate(Associate associate) {
	    	Timestamp currentTime = new Timestamp(System.currentTimeMillis());
	        boolean status = false;
	        int generatedAssociateId = -1;
	        try {
	            pstmt = conn.prepareStatement(INSERT_ASSOCIATE_QUERY,Statement.RETURN_GENERATED_KEYS);
	            pstmt.setString(1, associate.name());
	            pstmt.setInt(2, associate.age());
	            pstmt.setString(3, associate.businessUnit());
	            pstmt.setString(4, associate.email());
	            pstmt.setString(5, associate.location());
	            pstmt.setTimestamp(6,currentTime);
	            pstmt.setTimestamp(7, currentTime);

	            status = pstmt.executeUpdate() > 0;
	            ResultSet generatedKeys = pstmt.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                generatedAssociateId = generatedKeys.getInt(1); 
	            }
	            if (generatedAssociateId > 0) {
	                for (Skill skill : SkillTrackerApp.storeSkillTem) {
	                    skillService.addSkill(skill, generatedAssociateId);
	                }
	                SkillTrackerApp.storeSkillTem.clear();
	            }
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        if (status) {
	            System.out.println("Associate added successfully with ID: " + generatedAssociateId);
	        } else {
	            System.out.println("Failed to add associate.");
	        }
	        return status;
	    }

	    @Override
	    public boolean updateAssociate(Associate associate) {
	    	Timestamp updateTime = new Timestamp(System.currentTimeMillis());
	        boolean status = false;
	        try {
	            pstmt = conn.prepareStatement(UPDATE_ASSOCIATE_QUERY);
	            pstmt.setString(1, associate.name());
	            pstmt.setInt(2, associate.age());
	            pstmt.setString(3, associate.businessUnit());
	            pstmt.setString(4, associate.email());
	            pstmt.setString(5, associate.location());
	            pstmt.setTimestamp(6, updateTime);
	            pstmt.setInt(7, associate.id());

	            status = pstmt.executeUpdate() > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return status;
	    }

	    @Override
	    public boolean deleteAssociate(int id) {
	        boolean status = false;
	        try {
	            pstmt = conn.prepareStatement(DELETE_ASSOCIATE_QUERY);
	            pstmt.setInt(1, id);

	            status = pstmt.executeUpdate() > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return status;
	    }

	    @Override
	    public Associate getAssociate(int associateId) {
	        Associate associate = null;
	        try {
	            pstmt = conn.prepareStatement(SELECT_ASSOCIATE_QUERY);
	            pstmt.setInt(1, associateId);
	            rs = pstmt.executeQuery();

	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String name = rs.getString("name");
	                int age = rs.getInt("age");
	                String businessUnit = rs.getString("business_unit");
	                String email = rs.getString("email");
	                String location = rs.getString("location");
	                String createTime = rs.getString("create_time");
	                String updateTime = rs.getString("update_time");
                    List <Skill> skills = skillService.getSkillsByAssId(associateId);
	                

	                // Check if the associate already exists
	                if (associate == null) {
	                    associate = new Associate(id, name, age, businessUnit, email, location, skills, createTime, updateTime);
	                }
	               
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return associate;
	    }


	    @Override
	    public List<Associate> getAllAssociates() {
	        List<Associate> associates = new ArrayList<>();
	        try {
	            pstmt = conn.prepareStatement(SELECT_ALL_ASSOCIATES_QUERY);
	            rs = pstmt.executeQuery();

	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String name = rs.getString("name");
	                int age = rs.getInt("age");
	                String businessUnit = rs.getString("business_unit");
	                String email = rs.getString("email");
	                String location = rs.getString("location");
	                String createTime = rs.getString("create_time");
	                String updateTime = rs.getString("update_time");

	                List<Skill> allSkills = skillService.getAllSkills();
	                List<Skill> skills = allSkills.stream()
	                    .filter(sk -> sk.associateId() == id)
	                    .collect(Collectors.toList());
	                    Associate associate = new Associate(id, name, age, businessUnit, email, location, skills, createTime, updateTime);
	                    associates.add(associate);
	                
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	           
	        } 
	        return associates;
	    }

}
