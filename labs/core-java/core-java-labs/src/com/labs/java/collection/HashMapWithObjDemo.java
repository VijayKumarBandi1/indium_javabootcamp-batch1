package com.labs.java.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class HashMapWithObjDemo {

	public static void main(String[] args) {
		Map skill = new HashMap();
		

		Skill java = new Skill(1, "java", true);
		Skill py = new Skill(2, "py", false);
		Skill css = new Skill(3, "css", true);
		Skill html = new Skill(4, "html", true);
		Skill sql = new Skill(5, "SQL", true);
		
		skill.put(java.getId(), java);
		skill.put(py.getId(), py);
		skill.put(css.getId(), css);
		skill.put(html.getId(), html);
		skill.put(sql.getId(), sql);
		
		System.out.println(skill);
		
		Set<Map.Entry<Integer,Skill>> en = skill.entrySet();
		
		//Map Entry
//		for(Map.Entry entry: en) {
//			System.out.println(entry.getKey()+" "+entry.getValue());
//		}
		 
		for(Object entry:en) {
			System.out.println(entry);
		}
			
		
		}

}
