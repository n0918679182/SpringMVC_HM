package com.study.springmvcgit.case03.entity;

public class ExamPayType {
	private String id;
	private String name;
	
	
	public ExamPayType() {
	}
	public ExamPayType(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	@Override
	public String toString() {
		return "ExamPayType [id=" + id + ", name=" + name + "]";
	}
	
}
