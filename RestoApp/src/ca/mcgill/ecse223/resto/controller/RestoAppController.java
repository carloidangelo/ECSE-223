package ca.mcgill.ecse223.resto.controller;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse223.resto.application.RestoAppApplication;
import ca.mcgill.ecse223.resto.model.Table;
import ca.mcgill.ecse223.resto.model.MenuItem;
import ca.mcgill.ecse223.resto.model.MenuItem.ItemCategory;
import ca.mcgill.ecse223.resto.model.RestoApp;

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
}
