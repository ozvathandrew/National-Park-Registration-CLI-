package com.techelevator.classes;

import java.sql.Date;

public interface ReservationDAO {
	
	public long createReservation(String name, String fromDateString, String toDateString, int site_id);
}
