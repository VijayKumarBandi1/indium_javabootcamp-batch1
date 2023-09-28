package com.indium.skilltrackerapp.serviceimpl;

import java.util.List;

import com.indium.skilltrackerapp.model.Skill;
import com.indium.skilltrackerapp.service.SkillService;
import com.indium.skilltrackerapp.dao.SkillDAO;
import com.indium.skilltrackerapp.dao.SkillDAOImpl;

public class SkillServiceImpl implements SkillService {

	private SkillDAO skillDAO = new SkillDAOImpl();

    @Override
    public boolean addSkill(Skill skill, int id) {
      return  skillDAO.addSkill(skill,id);
    }

    @Override
    public List<Skill> getAllSkills() {
        return skillDAO.getAllSkills();
    }

    @Override
    public Skill getSkillById(int id,int assId) {
        return skillDAO.getSkill(id,assId);
    }

    @Override
    public boolean updateSkill(Skill skill,int id) {
      return skillDAO.updateSkill(skill, id);
    }

    @Override
    public boolean deleteSkill(int id ,int assId) {
        return skillDAO.deleteSkill(id,assId);
    }

    @Override
    public boolean deleteSkillWithAssId(int id) {
      return skillDAO.deleteSkillsByAssociateId(id);
    }
    @Override
    public List<Skill> getSkillsByAssId(int id) {
    return	skillDAO.getSkillsByAssociate(id);
    }
}
