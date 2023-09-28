package com.indium.skilltrackerapp.model;

import java.util.List;

public record Associate(
		int id,
	    String name,
	    int age,
	    String businessUnit,
	    String email,
	    String location,
	    List<Skill> skills,
	    String createTime,
	    String updateTime) {};
