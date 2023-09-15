package com.labs.java.collection;

import java.util.Comparator;

public class SkillComparatorByName  implements Comparator<Skill>{

	@Override
	public int compare(Skill o1, Skill o2) {
		// TODO Auto-generated method stub
		return o1.getName().compareTo(o2.getName());
	}

}
