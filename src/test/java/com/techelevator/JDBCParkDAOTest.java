package com.techelevator;

import java.util.List;	

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.classes.Park;
import com.techelevator.classes.ParkDAO;
import com.techelevator.jdbc.JDBCParkDAO;

import org.junit.Assert;

public class JDBCParkDAOTest extends DAOIntegrationTest {
	
	private ParkDAO dao;
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	@Before
	public void setup() {
		dataSource = super.getDataSource();
		dao = new JDBCParkDAO(dataSource);
	}
	
	@Test
	public void get_all_parks_should_return_all_parks() {
		List<Park> originalParkList = dao.getAllParks();
		int oldListSize = originalParkList.size();
		
		addPark();
		
		List<Park> newParkList = dao.getAllParks();
		int newListSize = newParkList.size();
		
		Assert.assertEquals(oldListSize+1, newListSize);
		
	}
	
	public void addPark() {
		String sql = "INSERT INTO park (name, location, establish_date, area, visitors, description)"
				+ "VALUES ('testPark', 'testLocation', '2019-01-10', 12345, 54321, 'test')";
		jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql);	
	}

}
