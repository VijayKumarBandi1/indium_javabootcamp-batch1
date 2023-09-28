package com.indium.skilltrackerapp.dao;

import java.util.List;

import com.indium.skilltrackerapp.model.Skill;

public interface SkillDAO {
	
	 boolean addSkill(Skill skill,int associateId);
	    boolean updateSkill(Skill skill,int associateId);
	    boolean deleteSkill(int skillId, int assId);
	    Skill getSkill(int skillId,int assId);
	    List<Skill> getAllSkills();
	    List<Skill> getSkillsByAssociate(int associateId);
	    boolean deleteSkillsByAssociateId(int skillId);

}
