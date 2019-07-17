package com.techelevator.jdbc;

import java.awt.color.CMMException;	
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.classes.Campground;
import com.techelevator.classes.CampgroundDAO;
import com.techelevator.classes.Park;

public class JDBCCampgroundDAO implements CampgroundDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	 public JDBCCampgroundDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Campground> getAllCampgrounds(int parkId) {
		List<Campground> campgroundList = new ArrayList<Campground>();
		String sql = "SELECT campground_id, park_id, name, open_from_mm, open_to_mm, daily_fee FROM campground WHERE park_id = ?"; 
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, parkId);
		while(result.next()) {
			campgroundList.add(mapToCampground(result));
		}
		
		return campgroundList;
	}

	@Override
	public boolean checkDataAvailabilty(Campground campground, String reservationStartDate, String reservationEndDate) {
		String sql = "SELECT open_from_mm, open_to_mm FROM campground WHERE campground_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql,campground.getCampground_id());
		
		int openDate = 0;
		int closeDate = 0;
		
		while(result.next()) {
			 openDate = Integer.parseInt(result.getString("open_from_mm")); 
			 closeDate = Integer.parseInt(result.getString("open_to_mm"));
			
		}

		int startDate = Integer.parseInt(reservationStartDate.substring(0,2));
		int endDate = Integer.parseInt(reservationEndDate.substring(0,2));
		
		if(startDate < openDate || endDate > closeDate) {
			return false;
		}
		
		return true;
	}
	
	private Campground mapToCampground(SqlRowSet result) {
		Campground c = new Campground();
		
		c.setCampground_id(result.getLong("campground_id"));
		c.setName(result.getString("name"));
		c.setOpenMonth(Integer.parseInt(result.getString("open_from_mm")));
		c.setClosingMonth(Integer.parseInt(result.getString("open_to_mm")));
		c.setDailyFee(result.getDouble("daily_fee"));
		return c;
	}

}


