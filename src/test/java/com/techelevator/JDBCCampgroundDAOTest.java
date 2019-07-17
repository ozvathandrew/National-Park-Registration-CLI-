package com.techelevator;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.classes.Campground;
import com.techelevator.classes.CampgroundDAO;
import com.techelevator.jdbc.JDBCCampgroundDAO;



public class JDBCCampgroundDAOTest extends DAOIntegrationTest {
	
	private CampgroundDAO dao;
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	@Before
	public void setup() {
		dataSource = super.getDataSource();
		dao = new JDBCCampgroundDAO(dataSource);
	}
	
	@Test 
	public void get_all_campgrounds_returns_all_campgrounds() {
		List<Campground> campgroundList = dao.getAllCampgrounds(1);
		int currentSize = campgroundList.size();
		addCampground();
		List<Campground> newCampgroundList = dao.getAllCampgrounds(1);
		int newSize = newCampgroundList.size();
		Assert.assertEquals(currentSize + 1, newSize);
	}
	
	
	private void addCampground() {
		String sql = "INSERT INTO campground (park_id, name, open_from_mm, open_to_mm, daily_fee)"
				+ "VALUES (1,'TestCampgroundName', '01','02', 35.00)";
		jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql);
		
		
	}
	
}
