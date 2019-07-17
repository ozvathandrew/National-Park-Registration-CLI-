package com.techelevator.classes;

import java.sql.Date;
import java.util.List;

public interface SiteDAO {
	
	public List<Site> getAllSites(String fromDateString, String toDateString, long campgroundId);
	
	public boolean checkOccupancy();
	
}
