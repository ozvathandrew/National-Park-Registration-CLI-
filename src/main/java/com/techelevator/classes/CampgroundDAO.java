package com.techelevator.classes;

import java.util.List;

public interface CampgroundDAO {
	
	public List<Campground> getAllCampgrounds(int parkId);
	
	public boolean checkDataAvailabilty(Campground campground,String reservationStartDate, String reservationEndDate);
	
	
	
	

}
