package com.techelevator.jdbc;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.classes.Site;
import com.techelevator.classes.SiteDAO;

public class JBDCSiteDAO implements SiteDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	 public JBDCSiteDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Site> getAllSites(String fromDateString, String toDateString, long campgroundId) {
		List<Site> siteList = new ArrayList<Site>();
		
		String formatedFromDate = fromDateString.substring(6) + "-"+ fromDateString.substring(0,2) + "-" +fromDateString.substring(3,5);
		String formatedToDate = toDateString.substring(6) + "-"+ toDateString.substring(0,2) + "-" +toDateString.substring(3,5);
		
		Date fromDate = Date.valueOf(formatedFromDate);
		Date toDate = Date.valueOf(formatedToDate);
		
		String sql = "SELECT site.site_id, site.campground_id, site_number, max_occupancy, accessible, max_rv_length, utilities  FROM site "
				+ "LEFT JOIN reservation ON site.site_id = reservation.site_id "
				+ "WHERE reservation.from_date < ? OR reservation.from_date > ? AND reservation.to_date > ? OR reservation.to_date < ? AND campground_id = ? LIMIT 5";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql,fromDate,fromDate,toDate,toDate, campgroundId);
		while( result.next()) {
			siteList.add(mapToSite(result));
		}
		return siteList;
	}

	@Override
	public boolean checkOccupancy() {
		// TODO Auto-generated method stub
		return false;
	}
	
	private Site mapToSite(SqlRowSet result) {
		Site s = new Site();
		
		s.setSiteId(result.getLong("site_id"));
		s.setCampgroundId(result.getLong("campground_id"));
		s.setSiteNumber(result.getInt("site_number"));
		s.setMaxOccupancy(result.getInt("max_occupancy"));
		s.setAccessible(result.getBoolean("accessible"));
		s.setMaxRvLength(result.getInt("max_rv_length"));
		s.setUtilities(result.getBoolean("utilities"));
		return s; 
		
	}
	

}
