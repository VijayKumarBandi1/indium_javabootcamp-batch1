package com.indium.skilltrackerapp.service;

import java.util.List;

import com.indium.skilltrackerapp.model.Skill;

public interface SkillService {
	
	 boolean addSkill(Skill skill,int id);
	    List<Skill> getAllSkills();
	    Skill getSkillById(int id,int assId);
	    boolean updateSkill(Skill skill,int id);
	    boolean deleteSkill(int id, int assId);
	    boolean deleteSkillWithAssId(int id);
	    List<Skill> getSkillsByAssId(int id);

}
