package com.labs.java.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LinkedListDemo {
	
	public static void main(String[] args) {
		LinkedList skills = new LinkedList();
		skills.add("java");
		skills.add("js");
		skills.add("css");
		skills.add("css");
		skills.add(100);
		skills.add(true);
		
		skills.add(100.50);
		skills.add(true);
		
		System.out.println(skills);
		
		//add an element in the given position
		skills.add(1, "html");
		System.out.println(skills);
		
		//update the element
		skills.set(4,"spring");
		System.out.println(skills);
		
		//remove the element
		skills.remove(5);
		System.out.println(skills);
		
		skills.remove(true);
		System.out.println(skills);
		
		//printing array collections using for each 
		for(Object skill:skills) {
			//if not checking type we are getting java.lang.ClassCastException: 
			if(skill instanceof String) {
				System.out.println(((String)skill).toUpperCase());
			}
			else
			System.out.println(skill);
		}
		
		//printing array collections using iterator
		System.out.println("printing array collections using iterator");
		Iterator it = skills.iterator();
		
		while (it.hasNext()) {
			System.out.println(it.next()); 
			
		}
		
	}

}
