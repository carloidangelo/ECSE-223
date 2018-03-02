package ca.mcgill.ecse223.resto.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import ca.mcgill.ecse223.resto.application.RestoAppApplication;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.controller.RestoAppController;
import ca.mcgill.ecse223.resto.model.MenuItem;
import ca.mcgill.ecse223.resto.model.MenuItem.ItemCategory;
import ca.mcgill.ecse223.resto.model.Table;

public class RestoAppPage extends JFrame {
	
	private static final long serialVersionUID = -4426310869335015542L;
	
	private String error = "";
	private JLabel errorMessage;
	
	private JButton menu;
	private JButton changeLayout;
	
	private JButton addTable;
	private JButton removeTable;
	private JButton updateTable;
	private JButton changeLocation;
	
	private JButton appetizer;
	private JButton main; 
	private JButton dessert;
	private JButton alcoholicBeverage; 
	private JButton nonAlcoholicBeverage;
	
	private JPanel appetizerMenu;
	private JPanel mainMenu;
	private JPanel dessertMenu;
	private JPanel alcoholicBeverageMenu; 
	private JPanel nonAlcoholicBeverageMenu;
	
	private RestoLayout restoLayout;
	private JScrollPane restoLayoutContainer;
	private List<Table> tables;
	
	public RestoAppPage() {
		initComponents();
		returnToMainMenu();
	}
	
	private void initComponents() {
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setBackground(Color.WHITE);
        setResizable(true);
		setTitle("Restaurant Application");
		
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
				
		menu = new JButton("Menu");
		menu.setBackground(Color.BLUE);
		menu.setForeground(Color.WHITE);
		
		changeLayout = new JButton("Restaurant Layout");
		changeLayout.setBackground(Color.GRAY);
		changeLayout.setForeground(Color.WHITE);
		
		addTable = new JButton("Add Table");
		removeTable = new JButton("Remove Table");
		updateTable = new JButton("Update Table");
		changeLocation = new JButton("Change Table Location");
		
		appetizer = new JButton("Appetizer");
		main = new JButton("Main");
		dessert = new JButton("Dessert");
		alcoholicBeverage = new JButton("Alcoholic Beverage"); 
		nonAlcoholicBeverage = new JButton("Non-alcoholic Beverage");
		
		restoLayout = new RestoLayout();
		restoLayoutContainer = new JScrollPane(restoLayout);
	
		restoLayoutContainer.setPreferredSize(new Dimension(1250, 500));
		restoLayoutContainer.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		restoLayoutContainer.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		tables = new ArrayList<Table>();
		Table test1 = new Table(3, 200, 200, 60, 150, RestoAppApplication.getRestoApp());
		Table test2 = new Table(35, 150, 150, 75, 75, RestoAppApplication.getRestoApp());
		tables.add(test1);
		tables.add(test2);
		restoLayout.setTables(tables);
		
		appetizerMenu  = new JPanel();
		mainMenu  = new JPanel();
		dessertMenu  = new JPanel();
		alcoholicBeverageMenu = new JPanel();
		nonAlcoholicBeverageMenu  = new JPanel();

		menu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				returnToMainMenu();
				appetizer.setVisible(true);
				main.setVisible(true);
				dessert.setVisible(true);
				alcoholicBeverage.setVisible(true);
				nonAlcoholicBeverage.setVisible(true);
				pack();
			}
		});
		
		changeLayout.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				returnToMainMenu();
				addTable.setVisible(true);
				removeTable.setVisible(true);
				updateTable.setVisible(true);
				changeLocation.setVisible(true);
				pack();
			}
		});
		
		appetizer.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeMenus();
				appetizerMenu.setVisible(true); 
				String constraint = "width=75" + " height=75"; 
				int numberOfColumns = 2;
				int numberOfRows = (int)Math.ceil(((double)RestoAppController.getItemCategories().size())/numberOfColumns);
		        GridLayout gl = new GridLayout(numberOfRows,numberOfColumns);
		        appetizerMenu.setLayout(gl);
				for (MenuItem menuitem : giveList(RestoAppController.getItemCategories().get(0))){
					JButton button;
					appetizerMenu.add(button = new JButton(menuitem.getName()),constraint);
					button.setFont(new Font("Calibri", Font.PLAIN, 20));	
				}
				pack();
			}
		});
		
		main.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeMenus();
				mainMenu.setVisible(true);
				String constraint = "width=75" + " height=75"; 
				int numberOfColumns = 2;
				int numberOfRows = (int)Math.ceil(((double)RestoAppController.getItemCategories().size())/numberOfColumns);
		        GridLayout gl = new GridLayout(numberOfRows,numberOfColumns);
		        mainMenu.setLayout(gl);
				for (MenuItem menuitem : giveList(RestoAppController.getItemCategories().get(1))){
					JButton button;
					mainMenu.add(button = new JButton(menuitem.getName()),constraint);
					button.setFont(new Font("Calibri", Font.PLAIN, 20));	
				}
				pack();
			}
		});
		
		dessert.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeMenus();
				dessertMenu.setVisible(true);
				String constraint = "width=75" + " height=75"; 
				int numberOfColumns = 2;
				int numberOfRows = (int)Math.ceil(((double)RestoAppController.getItemCategories().size())/numberOfColumns);
		        GridLayout gl = new GridLayout(numberOfRows,numberOfColumns);
		        dessertMenu.setLayout(gl);
				for (MenuItem menuitem : giveList(RestoAppController.getItemCategories().get(2))){
					JButton button;
					dessertMenu.add(button = new JButton(menuitem.getName()),constraint);
					button.setFont(new Font("Calibri", Font.PLAIN, 20));	
				}
				pack();
			}
		});
		
		alcoholicBeverage.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeMenus();
				alcoholicBeverageMenu.setVisible(true);
				String constraint = "width=75" + " height=75"; 
				int numberOfColumns = 2;
				int numberOfRows = (int)Math.ceil(((double)RestoAppController.getItemCategories().size())/numberOfColumns);
		        GridLayout gl = new GridLayout(numberOfRows,numberOfColumns);
		        alcoholicBeverageMenu.setLayout(gl);
				for (MenuItem menuitem : giveList(RestoAppController.getItemCategories().get(3))){
					JButton button;
					alcoholicBeverageMenu.add(button = new JButton(menuitem.getName()),constraint);
					button.setFont(new Font("Calibri", Font.PLAIN, 20));	
				}
				pack();
			}
		});
		
		nonAlcoholicBeverage.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeMenus();
				nonAlcoholicBeverageMenu.setVisible(true);
				String constraint = "width=75" + " height=75"; 
				int numberOfColumns = 2;
				int numberOfRows = (int)Math.ceil(((double)RestoAppController.getItemCategories().size())/numberOfColumns);
		        GridLayout gl = new GridLayout(numberOfRows,numberOfColumns);
		        nonAlcoholicBeverageMenu.setLayout(gl);
				for (MenuItem menuitem : giveList(RestoAppController.getItemCategories().get(4))){
					JButton button;
					nonAlcoholicBeverageMenu.add(button = new JButton(menuitem.getName()),constraint);
					button.setFont(new Font("Calibri", Font.PLAIN, 20));	
				}
				pack();
			}
		});
		
		JSeparator horizontalLine = new JSeparator();
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createParallelGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createSequentialGroup()
						.addComponent(menu,200,200,400)
						.addComponent(changeLayout))
				.addComponent(horizontalLine)
				.addGroup(layout.createSequentialGroup()
						.addComponent(appetizer)
						.addComponent(main)
						.addComponent(dessert)
						.addComponent(alcoholicBeverage)
						.addComponent(nonAlcoholicBeverage)
						.addComponent(addTable)
						.addComponent(removeTable)
						.addComponent(updateTable)
						.addComponent(changeLocation))
				.addGroup(layout.createSequentialGroup()
						.addComponent(appetizerMenu)
						.addComponent(mainMenu)
						.addComponent(dessertMenu)
						.addComponent(alcoholicBeverageMenu)
						.addComponent(nonAlcoholicBeverageMenu))
				.addComponent(restoLayoutContainer)
		);
		
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {menu, appetizer, main, dessert, alcoholicBeverage,
																				nonAlcoholicBeverage, addTable, removeTable, updateTable, 
																				changeLocation});

		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createParallelGroup()
						.addComponent(menu)
						.addComponent(changeLayout))
				.addComponent(horizontalLine)
				.addGroup(layout.createParallelGroup()
						.addComponent(appetizer)
						.addComponent(main)
						.addComponent(dessert)
						.addComponent(alcoholicBeverage)
						.addComponent(nonAlcoholicBeverage)
						.addComponent(addTable)
						.addComponent(removeTable)
						.addComponent(updateTable)
						.addComponent(changeLocation))
				.addGroup(layout.createParallelGroup()
						.addComponent(appetizerMenu)
						.addComponent(mainMenu)
						.addComponent(dessertMenu)
						.addComponent(alcoholicBeverageMenu)
						.addComponent(nonAlcoholicBeverageMenu))
				.addComponent(restoLayoutContainer)
		);
		
	}
	
	private void returnToMainMenu() {
		
		error = "";
		errorMessage.setText(error);

		appetizerMenu.removeAll();
		mainMenu.removeAll();
		dessertMenu.removeAll();
		alcoholicBeverageMenu.removeAll();
		nonAlcoholicBeverageMenu.removeAll();
		
		appetizerMenu.setVisible(false);
		mainMenu.setVisible(false);
		dessertMenu.setVisible(false);
		alcoholicBeverageMenu.setVisible(false);
		nonAlcoholicBeverageMenu.setVisible(false);
		
		addTable.setVisible(false);
		removeTable.setVisible(false);
		updateTable.setVisible(false);
		changeLocation.setVisible(false);
	
		pack();
		
		appetizer.setVisible(false);
		main.setVisible(false);
		dessert.setVisible(false);
		alcoholicBeverage.setVisible(false);
		nonAlcoholicBeverage.setVisible(false);
	}
	
	private List <MenuItem> giveList(ItemCategory aItemCategory) { 
		error = "";
		errorMessage.setText(error);
        List<MenuItem> listTemp = new ArrayList<MenuItem>();
        try {
        	listTemp = RestoAppController.getMenuItems(aItemCategory);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			error = e.getMessage();
			errorMessage.setText(error);
		}
		return listTemp;
	}
	
	private void removeMenus() {
		
		appetizerMenu.removeAll();
		mainMenu.removeAll();
		dessertMenu.removeAll();
		alcoholicBeverageMenu.removeAll();
		nonAlcoholicBeverageMenu.removeAll();
		
		appetizerMenu.setVisible(false);
		mainMenu.setVisible(false);
		dessertMenu.setVisible(false);
		alcoholicBeverageMenu.setVisible(false);
		nonAlcoholicBeverageMenu.setVisible(false);
	}
	
	

}

	
