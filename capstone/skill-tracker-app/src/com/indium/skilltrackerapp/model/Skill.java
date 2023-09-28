package com.indium.skilltrackerapp.model;

import com.indium.skilltrackerapp.enums.SkillCategory;

public record Skill(
		int id,
	    String name,
	    String description,
	    SkillCategory category,
	    int experience,
	    int associateId) {}
 