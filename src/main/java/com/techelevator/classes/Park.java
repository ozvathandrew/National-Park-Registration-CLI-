package com.techelevator.classes;

import java.sql.Date;

public class Park {
	
	private Long park_id;
	private String name; 
	private String location; 
	private Date date; 
	private int area; 
	private int visitors; 
	private String description;
	
	
	public Long getPark_id() {
		return park_id;
	}


	public void setPark_id(Long park_id) {
		this.park_id = park_id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public int getArea() {
		return area;
	}


	public void setArea(int area) {
		this.area = area;
	}


	public int getVisitors() {
		return visitors;
	}


	public void setVisitors(int visitors) {
		this.visitors = visitors;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	} 
	
	
	
	

}
