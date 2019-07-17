package com.techelevator;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.classes.Campground;
import com.techelevator.classes.CampgroundDAO;
import com.techelevator.classes.Park;
import com.techelevator.classes.ParkDAO;
import com.techelevator.classes.ReservationDAO;
import com.techelevator.classes.SiteDAO;
import com.techelevator.jdbc.JBDCSiteDAO;
import com.techelevator.jdbc.JDBCCampgroundDAO;
import com.techelevator.jdbc.JDBCParkDAO;
import com.techelevator.jdbc.JDBCReservationDAO;
import com.techelevator.view.Menu;

public class CampgroundCLI {
	
	public static void main(String[] args) {
		DataSource dataSource = makeConnection();
		CampgroundCLI application = new CampgroundCLI();
			application.run();
	}

//	public CampgroundCLI(DataSource datasource) {
//		CampgroundDAO campground = new JDBCCampgroundDAO(datasource);
//		SiteDAO site = new JBDCSiteDAO(datasource);
//		ParkDAO park = new JDBCParkDAO(datasource);
//		ReservationDAO reservation = new JDBCReservationDAO(datasource);
//		
//	}

	public void run() {
		while(1>0) {
			DataSource dataSource = makeConnection();
			ParkDAO park = new JDBCParkDAO(dataSource);
			Menu menu = createMenu();
			
			String firstChoice = menu.printParks(park.getAllParks());
			
			if(firstChoice.toUpperCase().equals("Q")) {
				break;
			}
			else if(Integer.parseInt(firstChoice) < park.getAllParks().size()) {
				handleFirstChoice(firstChoice);
			}
			else {
				handleFirstChoice(firstChoice);	
				menu.printMsgToUser("***Please select a valid choice***");
			}
		}
	}
	
	public void handleFirstChoice(String firstChoice) {
		while (1>0) {
			DataSource dataSource = makeConnection();		
			ParkDAO park = new JDBCParkDAO(dataSource);		
			List<Park> parkList = park.getAllParks();
			Menu menu = createMenu();
			
			menu.printParkInformation(parkList.get(Integer.parseInt(firstChoice) -1));
			String secondChoice = menu.selectCommand();
			
			if(secondChoice.equals("1")) {
				handleSecondChoice(firstChoice);
			}
			else if(secondChoice.equals("2")) {
				//handleReservation();
			}
			else if(secondChoice.equals("3")) {
				break;
			}
			else {
				menu.printMsgToUser("*** Please select a valid choice ***");
			}
		}
		
	}
	
	public void handleSecondChoice(String firstChoice) {
		while(1>0) {
			DataSource dataSource = makeConnection();
			CampgroundDAO campground = new JDBCCampgroundDAO(dataSource);
			Menu menu = createMenu();
			List<Campground> campgroundList = campground.getAllCampgrounds(Integer.parseInt(firstChoice));
			
			menu.printCampground(campground.getAllCampgrounds(Integer.parseInt(firstChoice)));
			int userChoice = menu.campgroundSelect();
			
			if(userChoice == 0) {
				break;
			}
			else if(userChoice > campground.getAllCampgrounds(Integer.parseInt(firstChoice)).size()) {
				menu.printMsgToUser("*** Please enter a valid choice ***");
			}
			else 
				handleThirdChoice(userChoice, campgroundList.get(userChoice -1), firstChoice);
		}
	}
	
	public void handleThirdChoice(int userChoice, Campground chosenCampground, String parkChoice) {
		DataSource dataSource = makeConnection();
		CampgroundDAO campground = new JDBCCampgroundDAO(dataSource);
		ReservationDAO reservation = new JDBCReservationDAO(dataSource);
		SiteDAO site = new JBDCSiteDAO(dataSource);
		Menu menu = createMenu();
		String fromDateString = menu.fromDate();
		String toDateString = menu.toDate();
		
		if(campground.checkDataAvailabilty(chosenCampground, fromDateString, toDateString)) {
			menu.printSitesAvailable(site.getAllSites(fromDateString, toDateString, chosenCampground.getCampground_id()));
			
			int selectedSite = menu.selectSite();
			String reservationName = menu.nameForReservation();
			Long reservationId = reservation.createReservation(reservationName, fromDateString, toDateString, selectedSite);
			menu.printMsgToUser("Thank you, your reservation has been made and your reservation number is " + reservationId);
			
		}
		
		else
			menu.printMsgToUser("No available campsites for those dates, please enter a new date");
			handleSecondChoice(parkChoice);
	}
	
	public static DataSource makeConnection() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		
		return dataSource;
	}
	
	public Menu createMenu() {
		OutputStream out = System.out;
		InputStream in = System.in;
		Menu menu = new Menu(in,out);
		
		return menu;
	}
	
	
}
