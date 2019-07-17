package com.techelevator.classes;

public class Campground {
	
	private long campgroundId;
	private String name;
	private int openMonth;
	private int closingMonth;
	private double dailyFee;
			
	public long getCampground_id() {
		return campgroundId;
	}
	public void setCampground_id(long campground_id) {
		this.campgroundId = campground_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOpenMonth() {
		return openMonth;
	}
	public void setOpenMonth(int openMonth) {
		this.openMonth = openMonth;
	}
	public int getClosingMonth() {
		return closingMonth;
	}
	public void setClosingMonth(int closingMonth) {
		this.closingMonth = closingMonth;
	}
	public double getDailyFee() {
		return dailyFee;
	}
	public void setDailyFee(double dailyFee) {
		this.dailyFee = dailyFee;
	}

}
