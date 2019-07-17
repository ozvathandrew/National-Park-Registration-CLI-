package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.techelevator.classes.Campground;
import com.techelevator.classes.Park;
import com.techelevator.classes.Site;

public class Menu {
	
	private PrintWriter out;
	private Scanner in;
	
	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}
	
	public String printParks(List<Park> parkList) {
		out.println("Select a Park for Further Details");
		for(int i = 0; i < parkList.size(); i++) {
			Park currentPark = parkList.get(i);
			out.println(i + 1 + ") " + currentPark.getName());
		}
		out.println("Q) Quit");
		out.flush();
		String userChoice = in.nextLine();
		return userChoice;
	}
	
	public void printParkInformation(Park choosenPark) {
		out.println(choosenPark.getName());
		out.println("Location: " + choosenPark.getLocation());
		out.println("Established: " + choosenPark.getDate());
		out.println("Area: " + choosenPark.getArea() + "sq km");
		out.println("Annual Visitors: " + choosenPark.getVisitors());
		out.println("\n" + choosenPark.getDescription());
		out.flush();
	}
	
	
	
	public String selectCommand() {
		out.println("Select Command");
		out.println("1) View Campground");
		out.println("2) Search for Reservation");
		out.println("3) Return to Previous Screen");
		out.flush();
		String userChoice = in.nextLine();
		return userChoice;
		
	}
	
	public void printCampground(List<Campground> campList) {
		out.printf("%-35s %-25s %-25s %-25s", "Name", "Open", "Close", "Daily Fee");
		out.println("");
		for(int i = 0 ; i < campList.size() ; i++) {
			Campground campground = campList.get(i);
			out.printf("%-2s %-35s %-25s %-25s %-25s", "#" + (i+1), campground.getName(), campground.getOpenMonth(), campground.getClosingMonth(), campground.getDailyFee());
			out.println("");
		}		
	}
	
	public String selectSecondCommand() {
		out.println("1)" + "Search for Available Reservation");
		out.println("2)" + "Return to Previous Screeen");
		String userChoice = in.nextLine();
		return userChoice; 
	}
	
	public int campgroundSelect() {
		out.println("Which campground (enter 0 to cancel)?");
		out.flush();
		int userChoice = in.nextInt();
		return userChoice; 
	}
	
	public String fromDate() {
		out.println("What is the arrival date? (mm/dd/yyyy)");
		out.flush();
		String fromDate = in.nextLine();
		
		
		return fromDate;
	}
	
	public String toDate() {
		out.println("What is the departure date? (mm/dd/yyyy)");
		out.flush();
		String toDate = in.nextLine();
		
		return toDate;
	}
	
	public void printSitesAvailable(List<Site> siteList) {
		out.printf("%-15s %-15s %-15s %-15s %-15s", "Site No.", "Max Occup.", "Accessible", "Max Rv Length", "Utility");
		out.println("");
		out.flush();
		for(int i = 0 ; i < siteList.size() ; i++) {
			Site site = siteList.get(i);
			out.printf("%-15s %-15s %-15s %-15s %-15s", site.getSiteId(), site.getMaxOccupancy(), site.isAccessible(), site.getMaxRvLength(), site.isUtilities());
			out.println("");
			out.flush();
		}
	
	}
	
	public int selectSite() {
		out.println("Please select a campsite to make a reservation");
		out.flush();
		int selectedSite = in.nextInt();
		return selectedSite;
	}
	
	public String nameForReservation() {
		out.println("What name do you want the reservation under?");
		out.flush();
		in.nextLine();
		String reservationName = in.nextLine();
		
		return reservationName;
	}
	
	
	public void printMsgToUser(String msg) {
		out.println(msg + "\n");
		out.flush();
	}
	

}
