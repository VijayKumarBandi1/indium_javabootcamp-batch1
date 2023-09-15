package com.labs.java.collection;

import java.util.Comparator;

public class SkillComparatorById implements Comparator<Skill> {

	@Override
	public int compare(Skill o1, Skill o2) {
		
		return o1.getId()-o2.getId();
	}

}
