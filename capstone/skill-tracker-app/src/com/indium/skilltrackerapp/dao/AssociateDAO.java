package com.indium.skilltrackerapp.dao;

import java.util.List;

import com.indium.skilltrackerapp.model.Associate;

public interface AssociateDAO {
	
	boolean addAssociate(Associate associate);
    boolean updateAssociate(Associate associate);
    boolean deleteAssociate(int id);
    Associate getAssociate(int associateId);
    List<Associate> getAllAssociates();

}
