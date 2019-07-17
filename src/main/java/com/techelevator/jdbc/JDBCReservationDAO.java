package com.techelevator.jdbc;

import java.sql.Date;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.classes.ReservationDAO;

public class JDBCReservationDAO implements ReservationDAO {
	
	private JdbcTemplate jdbcTemplate; 
	
	 public JDBCReservationDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public long createReservation(String name, String fromDateString, String toDateString, int site_id) {
		String formatedFromDate = fromDateString.substring(6) + "-"+ fromDateString.substring(0,2) + "-" +fromDateString.substring(3,5);
		String formatedToDate = toDateString.substring(6) + "-"+ toDateString.substring(0,2) + "-" +toDateString.substring(3,5);
		
		Date fromDate = Date.valueOf(formatedFromDate);
		Date toDate = Date.valueOf(formatedToDate);
		
		String sql = "INSERT INTO reservation(site_id, name, from_date, to_date) Values (?,?,?,?) RETURNING reservation_id";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, site_id, name, fromDate, toDate);
		result.next();
		long conformationId = result.getLong("reservation_id");
		return conformationId;
	}
	
	




	
	
	

}
