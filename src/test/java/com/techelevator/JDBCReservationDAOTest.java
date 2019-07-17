package com.techelevator;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.classes.Reservation;
import com.techelevator.classes.ReservationDAO;
import com.techelevator.jdbc.JDBCReservationDAO;

public class JDBCReservationDAOTest extends DAOIntegrationTest {
	
	private ReservationDAO dao;
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	@Before 
	public void setup() {
		dataSource = super.getDataSource();
		dao = new JDBCReservationDAO(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Test
	public void create_reservation_inserts_reservation() {
		List<Reservation> oldReservationCount = getAllReservations();
		int oldSize = oldReservationCount.size();		
		long siteId = dao.createReservation("testname", "01/01/9999", "01/02/9999", 99);
		List<Reservation> newReservationCount = getAllReservations();
		int newSize = newReservationCount.size();
		Assert.assertEquals(oldSize + 1, newSize);
		
	}
	
	
	private List<Reservation> getAllReservations(){
		
		String sql = "SELECT reservation_id, site_id, name, from_date, to_date, create_date FROM reservation";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
		List<Reservation> allReservation = new ArrayList<Reservation>();
		while(result.next()) {
			allReservation.add(mapToReservation(result));
			
		}
		return allReservation;
	}
	
	private Reservation mapToReservation(SqlRowSet result) {
		Reservation r = new Reservation();
		
		r.setReservationId(result.getInt("reservation_id"));
		r.setSiteId(result.getLong("site_id"));
		r.setName(result.getString("name"));
		r.setFromDate(result.getDate("from_date"));
		r.setToDate(result.getDate("to_date"));
		r.setCreateDate(result.getDate("create_date"));
		
		return r;
	}
	

}
