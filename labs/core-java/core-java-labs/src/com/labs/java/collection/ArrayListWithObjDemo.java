package com.labs.java.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayListWithObjDemo {

	public static void main(String[] args) {

		Skill java = new Skill(1, "java", true);
		Skill py = new Skill(2, "py", false);
		Skill css = new Skill(3, "css", true);
		Skill html = new Skill(4, "html", true);
		Skill sql = new Skill(5, "SQL", true);
		
		List<Skill> skill = new ArrayList();

        skill.add(java);
        skill.add(py);
        skill.add(css);
        skill.add(html);
        skill.set(1,sql);
        
        skill.remove(py);
        
      //  System.out.println(skill);
        
        Iterator<Skill> it = skill.iterator();
        
        while (it.hasNext()) {
			//System.out.println(it.next());
			Skill sk = it.next();
			System.out.println(sk.getId() +" "+ sk.getName()+" "+sk.isPrimary());
		}
	}

}
