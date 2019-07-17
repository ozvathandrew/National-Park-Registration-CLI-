package com.techelevator;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.classes.ReservationDAO;
import com.techelevator.classes.Site;
import com.techelevator.classes.SiteDAO;
import com.techelevator.jdbc.JBDCSiteDAO;

import org.junit.Assert;

public class JDBCSiteDAOTest extends DAOIntegrationTest {
	
	private SiteDAO dao;
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	@Before 
	public void setup() {
		dataSource = super.getDataSource();
		dao = new JBDCSiteDAO(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Test
	public void create_site_returns_new_site() {
		List<Site> oldSiteCount = dao.getAllSites("06/18/1995", "06/22/1995", 4);
		int oldSize = oldSiteCount.size();
		addSite();
		addSite();
		List<Site> newSiteList = dao.getAllSites("06/18/1995", "06/22/1995", 4);
		int newListSize = newSiteList.size();
		
		// will fail because sql statment has LIMIT 5
		Assert.assertEquals(oldSize + 2, newListSize);		
	}
	
	
	
	private void addSite() {
		String sql = "INSERT INTO site (campground_id, site_number,max_occupancy, accessible,max_rv_length,utilities)" 
				  + "VALUES (4,0,0,true,0,true)";
		jdbcTemplate.update(sql);
	}
}
