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
	
	public static List<Table> getTables() {
		return RestoAppApplication.getRestoApp().getTables();
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
