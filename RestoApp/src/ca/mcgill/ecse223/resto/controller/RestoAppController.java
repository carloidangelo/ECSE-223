package ca.mcgill.ecse223.resto.controller;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse223.resto.application.RestoAppApplication;
import ca.mcgill.ecse223.resto.model.Table;
import ca.mcgill.ecse223.resto.model.MenuItem;
import ca.mcgill.ecse223.resto.model.Order;
import ca.mcgill.ecse223.resto.model.MenuItem.ItemCategory;
import ca.mcgill.ecse223.resto.model.RestoApp;
import ca.mcgill.ecse223.resto.model.Seat;

public class RestoAppController {

	public RestoAppController() {
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
	
	public static void createTable(String numberString, String xString, String yString, String widthString, String lengthString, String numOfSeatsString) throws InvalidInputException {
		String error = "";
		if (numberString.equals("") || xString.equals("") 
				|| yString.equals("") || widthString.equals("") 
				|| lengthString.equals("") || numOfSeatsString.equals("")) 
			{
				error = error + "Every field has to be filled.";
				throw new InvalidInputException(error.trim());
			}
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
		if (Integer.toString(number).equals("") || Integer.toString(x).equals("") 
			|| Integer.toString(y).equals("") || Integer.toString(width).equals("") 
			|| Integer.toString(length).equals("") || Integer.toString(numOfSeats).equals("")) 
		{
			error = error + "Every field has to be filled.";
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
		
		try {
			Table table = new Table(number, x, y, width, length, restoapp);
			restoapp.addCurrentTable(table);
			for (int i=0; i<numOfSeats; i++)
			{
				Seat seat = table.addSeat();
				table.addSeat(seat);
			}
			RestoAppApplication.save();
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
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
		String error = "A table must be specified to remove a seat.";
		if(table == null)
			throw new InvalidInputException(error);
		if(table.hasReservations())
			throw new InvalidInputException("A seat cannot be removed from a table that is currently reserved.");
		RestoApp resto = RestoAppApplication.getRestoApp();
		List<Order> currentOrders = resto.getCurrentOrders();
		for (Order order : currentOrders)
		{
			List<Table> orderTables = order.getTables();
			Boolean tableHasCurrentOrder = orderTables.contains(table);
			if(!tableHasCurrentOrder)
			{
				try
				{
					Seat seat = table.getCurrentSeat(0);
					table.removeCurrentSeat(seat);
					break;
				}
				catch(RuntimeException e)
				{
					throw new InvalidInputException("A table must always have at least one seat.");
				}
			}
			else
				throw new InvalidInputException("A seat cannot be removed from a table that currently has an order.");
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
}
