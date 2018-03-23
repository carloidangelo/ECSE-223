package ca.mcgill.ecse223.resto.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ca.mcgill.ecse223.resto.application.RestoAppApplication;
import ca.mcgill.ecse223.resto.model.Table;
import ca.mcgill.ecse223.resto.model.MenuItem;
import ca.mcgill.ecse223.resto.model.Order;
import ca.mcgill.ecse223.resto.model.Reservation;
import ca.mcgill.ecse223.resto.model.MenuItem.ItemCategory;
import ca.mcgill.ecse223.resto.model.RestoApp;
import ca.mcgill.ecse223.resto.model.Seat;

public class RestoAppController {

	public RestoAppController() {
	}

	public static void moveTable(Table table, int x, int y) throws InvalidInputException{
		String error ="";
		if (table == null) {	
			error = "The table doesn't exist";		
		}
		if(x<0||y<0) {
			error = "Location entered must be positive";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		int width = table.getWidth();
		int length = table.getLength();
		boolean overlaps = true;
		RestoApp restoApp = RestoAppApplication.getRestoApp();
		for(Table currentTable:restoApp.getCurrentTables()) {
			overlaps = currentTable.doesOverlap(x,y,width,length);
			if (overlaps == true){
				if (!currentTable.equals(table)) {
					error = "Table cannot be placed due to overlapping";
					throw new InvalidInputException(error.trim());
				}
			}
		}
		try {
			table.setX(x);
			table.setY(y);
			RestoAppApplication.save();
		}catch(RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	public static List<MenuItem> getMenuItems(ItemCategory itemCategory) throws InvalidInputException {
		if(itemCategory == null) {
		      throw new InvalidInputException("Item category not in system");
		}
		List<MenuItem> listTemp = new ArrayList<MenuItem>();
		RestoApp restoApp = RestoAppApplication.getRestoApp();
		List<MenuItem> menuItems = restoApp.getMenu().getMenuItems();
		for (MenuItem menuitem : menuItems) {
			boolean current = menuitem.hasCurrentPricedMenuItem();
			ItemCategory category = menuitem.getItemCategory();		
			if (current && category.equals(itemCategory)) {
				listTemp.add(menuitem);
			}
		}
		return listTemp;
		
	}
	
	public static List<ItemCategory> getItemCategories() {
		ArrayList<ItemCategory> listTemp = new ArrayList<ItemCategory>();
		for (ItemCategory itemCategory : ItemCategory.values()) {
			listTemp.add(itemCategory);
		}
		return (List<ItemCategory>) listTemp;
	}
	
	public static List<Table> getCurrentTables() {
		return RestoAppApplication.getRestoApp().getCurrentTables();
	}
	
	public static List<Order> getCurrentOrders() {
		return RestoAppApplication.getRestoApp().getCurrentOrders();
	}
	
	public static void createTable(String numberString, String xString, String yString, String widthString, String lengthString, String numOfSeatsString) throws InvalidInputException {
		String error = "";
		if (numberString.equals("") || xString.equals("") 
				|| yString.equals("") || widthString.equals("") 
				|| lengthString.equals("") || numOfSeatsString.equals("")) 
			{
				error = error + "Every field has to be filled.";
				throw new InvalidInputException(error.trim());
			}
		try {
			int number = Integer.parseInt(numberString); 
			int x = Integer.parseInt(xString);
			int y = Integer.parseInt(yString); 
			int width = Integer.parseInt(widthString); 
			int length = Integer.parseInt(lengthString);
			int numOfSeats = Integer.parseInt(numOfSeatsString);
			RestoApp restoapp = RestoAppApplication.getRestoApp();
			if (number<=0)
			{
				error = "Table number must be positive. ";
			}
			if (x<0) 
			{
				error = error + "x coordinate can not be negative. ";
			}
			if (y<0)
			{
				error = error + "y coordinate can not be negative. ";
			}
			if (width<=0)
			{
				error = error + "Table width must be positive. ";
			}
			if (length<=0)
			{
				error = error + "Table length must be positive. ";
			}
			if (numOfSeats<=0)
			{
				error = error + "Number of seats must be positive. ";
			}
			
			List<Table> currentTables = restoapp.getCurrentTables();
			
			for (int i=0; i<currentTables.size(); i++)
			{
				if (currentTables.get(i).doesOverlap(x, y, width, length) == true)
				{
					error = error + "Table overlaps with another table. ";
					break;
				}
			}
			
			if (error.length() > 0) {
				throw new InvalidInputException(error.trim());
			}
			
			Table table = new Table(number, x, y, width, length, restoapp);
			restoapp.addCurrentTable(table);
			for (int i=0; i<numOfSeats; i++)
			{
				Seat seat = table.addSeat();
				table.addCurrentSeat(seat);
		}
		
		}catch(NumberFormatException e) {
			throw new InvalidInputException("Please provide non-empty numerical input in all fields.");
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
		RestoAppApplication.save();
	}
	
	//adds seat to specified table
	public static void addSeat(Table table) throws InvalidInputException{
		if(table == null)
			throw new InvalidInputException("A table must be specified to add a seat.");
		table.addCurrentSeat(table.addSeat());
		RestoAppApplication.save();
	}
	
	//removes a seat from specified table
	public static void removeSeat(Table table) throws InvalidInputException {
		if(table == null)
			throw new InvalidInputException("A table must be specified to remove a seat.");
		if(table.hasReservations())
			throw new InvalidInputException("A seat cannot be removed from a table that is currently reserved.");
		RestoApp resto = RestoAppApplication.getRestoApp();
		List<Order> currentOrders = resto.getCurrentOrders();
		Boolean tableHasCurrentOrder = false;
		for (Order order : currentOrders)
		{
			List<Table> orderTables = order.getTables();
			tableHasCurrentOrder = orderTables.contains(table);
			if(tableHasCurrentOrder)
			{
				throw new InvalidInputException("A seat cannot be removed from a table that currently has an order.");
			}
		}

		try {
			Seat seat = table.getCurrentSeat(0);
			table.removeCurrentSeat(seat);
		}
		catch(RuntimeException e) {
			throw new InvalidInputException("No more seats can be removed from this table.");
		}
		
		RestoAppApplication.save();
	}
	
	//sets table number of specified table
	public static void setTableNumber(Table table, int number) throws InvalidInputException{
		if(table == null)
			throw new InvalidInputException("A table must be specified to change a table number.");
		if(number <= 0)
			throw new InvalidInputException("A table's number must be greater than 0.");
		Boolean wasSet = table.setNumber(number);
		if(!wasSet)
			throw new InvalidInputException("A table with this number already exists. Please choose a different number.");
		RestoAppApplication.save();
	}
	
	//removes a table
	public static void removeTable(Table table) throws InvalidInputException{
		if(table == null)
			throw new InvalidInputException("A table must be specified to remove it.");
		if(table.hasReservations())
			throw new InvalidInputException("A table with reservations cannot be removed.");
		RestoApp resto = RestoAppApplication.getRestoApp();
		List<Order> currentOrders = resto.getCurrentOrders();
		for(Order order : currentOrders) {
			List<Table> tables = order.getTables();
			Boolean inUse = tables.contains(table);
			if(inUse)
				throw new InvalidInputException("A table cannot be removed if it is currently in use.");
		}
		resto.removeCurrentTable(table);
		RestoAppApplication.save();
	}
	
	//starts an order
	public static void startOrder(List<Table> tables) throws InvalidInputException {
		if(tables == null)
			throw new InvalidInputException("At least one table is required to create an order.");
		RestoApp r = RestoAppApplication.getRestoApp();
		List<Table> currentTables = r.getCurrentTables();
		for(Table table : tables) {
			Boolean current = currentTables.contains(table);
			if(current == false)
				throw new InvalidInputException("Order must include at least one currently used table.");
		}
		Boolean orderCreated = false;
		Order newOrder = null;
		for(Table table : tables) {
			if(orderCreated)
				table.addToOrder(newOrder);
			else {
				Order lastOrder = null;
				if(table.numberOfOrders() > 0)
					lastOrder = table.getOrder(table.numberOfOrders()-1);
				table.startOrder();
				if(table.numberOfOrders() > 0 && !table.getOrder(table.numberOfOrders()-1).equals(lastOrder)) {
					orderCreated = true;
					newOrder = table.getOrder(table.numberOfOrders()-1);
				}
			}
		}
		if(!orderCreated)
			throw new InvalidInputException("At least one of the selected tables cannot currently be included in an order.");
		r.addCurrentOrder(newOrder);
		RestoAppApplication.save();
	}
	
	//ends an order
	public static void endOrder(Order order) throws InvalidInputException {
		if(order == null)
			throw new InvalidInputException("An order must be selected to remove an order.");
		RestoApp r = RestoAppApplication.getRestoApp();
		List<Order> currentOrders = r.getCurrentOrders();
		Boolean current = currentOrders.contains(order);
		if(!current)
			throw new InvalidInputException("Order is not a current Order.");
		List<Table> tables = order.getTables();
		for(Table table : tables)
			table.endOrder(order);
		if(RestoAppController.allTablesAvailableOrDifferentCurrentOrder(tables, order))
			r.removeCurrentOrder(order);
		RestoAppApplication.save();
	}
	
	private static boolean allTablesAvailableOrDifferentCurrentOrder(List<Table> tables, Order order) {
		//TODO
		return false;	//change
	}
	
	//reserve table
	public static void reserveTable(Date date, Time time, int numberInParty, String contactName, String contactEmailAddress, 
									String contactPhoneNumber, List<Table> tables) throws InvalidInputException{
		date = cleanDate(date);
		String error = "";
		if (date == null)
			error = "Date cannot be empty";		
		if(time == null)
			error = "Time cannot be empty";
		if(contactName == null)
			error = "Contact name cannot be empty";
		if(contactEmailAddress == null)
			error = "Contact email address cannot be empty";
		if(contactPhoneNumber == null)
			error = "Contact phone number cannot be empty";
		if(!isDateInPast(date))
			error = "Invalid date";
		if (!isTimeInPast(time))
			error = "Invalid time";
		if(numberInParty <= 0)
			error = "Number in party should be positive";
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		RestoApp restoApp = RestoAppApplication.getRestoApp();
		List<Table> currentTable = restoApp.getCurrentTables();
		int seatCapacity = 0;
		for(Table table: tables) {
			boolean current = currentTable.contains(table);
			if(!current) {
				error = "Table not found in current tables";
				throw new InvalidInputException(error.trim());
			}
			seatCapacity += table.numberOfCurrentSeats();
			List<Reservation> reservations = table.getReservations();
				for(Reservation reservation: reservations) {
					boolean overlaps = reservation.doesOverlap(date, time);
						if(overlaps) {
							error = "Cannot create new reservation due to previous reservations";
							throw new InvalidInputException(error.trim());
						}
				}
		}
		if(seatCapacity < numberInParty)
			throw new InvalidInputException("Not enough seats");
		Table [] tableArray = (Table[]) tables.toArray();
		Reservation res = new Reservation(date, time, numberInParty, contactName, contactEmailAddress, contactPhoneNumber, restoApp, tableArray);
		for(Table table: tables) {
			table.addReservation(res);
			List<Reservation> reservations = table.getReservations();
			Collections.sort(reservations, new Comparator<Reservation>() {
				public int compare(Reservation o1, Reservation o2) {
				      return o1.getDate().compareTo(o2.getDate());
				  }
			});
		}
		try {
			RestoAppApplication.save();
		}catch(RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}

	
	private static boolean isDateInPast(Date date) {
		java.util.Date tempToday = RestoAppApplication.getRestoApp().getCurrentDate();
		return date.before(tempToday);
	}
	
	private static boolean isTimeInPast(Time time) {
		java.util.Date tempToday = RestoAppApplication.getRestoApp().getCurrentTime();
		return time.before(tempToday);
	}
	
	private static Date cleanDate(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTimeInMillis(date.getTime());
	    cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.set(Calendar.MILLISECOND, 0);
	    java.util.Date tempCleanedDate = cal.getTime();
	    java.sql.Date cleanedDate = new java.sql.Date(tempCleanedDate.getTime());
	    return cleanedDate;
	}
}
