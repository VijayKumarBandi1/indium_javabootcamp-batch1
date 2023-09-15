package com.labs.java.collection;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class TreeMapWithObjDemo {

	public static void main(String[] args) {
		
		Comparator<String> COMPARATOR = new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o2.compareTo(o1) ;
			}
		};
		

		Map  skills = new TreeMap(COMPARATOR);

		Skill java = new Skill(1, "java", true);
		Skill py = new Skill(2, "py", false);
		Skill css = new Skill(3, "css", true);
		Skill html = new Skill(4, "html", true);
		Skill sql = new Skill(5, "SQL", true);
		
	skills.put(java.getName(), java);
	skills.put(py.getName(), py);
	skills.put(css.getName(), css);
	skills.put(html.getName(), html);
	skills.put(sql.getName(), sql);
	
	Set en = skills.entrySet();
	
	for(Object entry:en) {
		System.out.println(entry);
	}

		
	}

}
