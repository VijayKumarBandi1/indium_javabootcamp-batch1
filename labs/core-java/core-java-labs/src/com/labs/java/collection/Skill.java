package com.labs.java.collection;

public class Skill {

	private int id;
	private String name;
	private boolean primary;
	
	public Skill(int id, String name, boolean primary) {
		super();
		this.id = id;
		this.name = name;
		this.primary = primary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isPrimary() {
		return primary;
	}

	public void setPrimary(boolean primary) {
		this.primary = primary;
	}

	@Override
	public String toString() {
		return "Skill [id=" + id + ", name=" + name + ", primary=" + primary + "]";
	}
	
	
}
