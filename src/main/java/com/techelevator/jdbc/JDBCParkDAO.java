package com.techelevator.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.classes.Park;
import com.techelevator.classes.ParkDAO;

public class JDBCParkDAO implements ParkDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	 public JDBCParkDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Park> getAllParks() {
		List<Park> parkList = new ArrayList<Park>();
		String sql = "SELECT park_id, name, location, establish_date, area, visitors, description FROM park ORDER BY name ASC";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
		while(result.next()) {
			parkList.add(mapToPark(result));
		}
		return parkList;
	}
	
	
	private Park mapToPark(SqlRowSet result) {
		Park p = new Park();
		
		p.setPark_id(result.getLong("park_id"));
		p.setName(result.getString("name"));
		p.setLocation(result.getString("location"));
		p.setDate(result.getDate("establish_date"));
		p.setArea(result.getInt("area"));
		p.setVisitors(result.getInt("visitors"));
		p.setDescription(result.getString("description"));
		return p;
	}

}
