package com.indium.skilltrackerapp.service;

import java.util.List;

import com.indium.skilltrackerapp.model.Associate;

public interface AssociateService {
	
	boolean addAssociate(Associate associate);
    List<Associate> getAllAssociates();
    Associate getAssociateById(int id);
    boolean updateAssociate(Associate associate);
    boolean deleteAssociate(int id);
    List<Associate> searchAssociatesByName(String name);
    List<Associate> searchAssociatesByLocation(String location);
    List<Associate> searchAssociatesBySkills(String skills);
    int getTotalAssociateCount();
    int getTotalAssociatesWithSkillsCount(int minSkillsCount);
    List<Integer> getAssociateIdsWithSkillsCount(int minSkillsCount);
    int getTotalAssociatesWithGivenSkills(String skills);
    List<Object[]> getAssociateWiseSkillCount();
    List<Object[]> getBusinessUnitWiseAssociateCount();
    List<Object[]> getLocationWiseSkillCount();
    List<Object[]> getSkillWiseAssociateCount();
    List<Object[]> getSkillWiseAvgAssociateExperience();

}
