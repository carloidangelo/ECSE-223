package ca.mcgill.ecse223.resto.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.*;

import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.controller.RestoAppController;
import ca.mcgill.ecse223.resto.model.MenuItem;
import ca.mcgill.ecse223.resto.model.MenuItem.ItemCategory;

import ca.mcgill.ecse223.resto.model.RestoApp;

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
	
	private JLabel tableNumber;
	private JLabel xCoord;
	private JLabel yCoord;
	private JLabel tableWidth;
	private JLabel tableLength;
	private JLabel numOfSeats;
	
	private JTextField tableNumberField;
	private JTextField xCoordField;
	private JTextField yCoordField;
	private JTextField tableWidthField;
	private JTextField tableLengthField;
	private JTextField numOfSeatsField;
	
	private JButton addTableButton;
	
	private JPanel appetizerMenu;
	private JPanel mainMenu;
	private JPanel dessertMenu;
	private JPanel alcoholicBeverageMenu; 
	private JPanel nonAlcoholicBeverageMenu;
	
	private RestoLayout restoLayout;
	private JScrollPane restoLayoutContainer;
	
	//remove table
	private JComboBox <String> selectTableRemoveTable;
	private JLabel selectTableRemoveTableLabel;
	private JButton removeTableConfirm;
	private Integer selectedTable2 = -1;
	
	//move table UI
	private JComboBox <String> tableList;
	
	private JLabel moveTable;
	
	private JButton moveTableButton;
	private Integer selectedTable = -1;
	
	//Select table combo box hashmaps
	private HashMap<Integer, Table> tables;
	private HashMap<Table, Integer> tablesReverse;
	
	//select table (update table)
	private JLabel selectTableUpdateTableLabel;
	private JComboBox <String> selectTableUpdateTable;
	private Integer selectedTable1 = -1;
	private java.awt.event.ActionListener selectTableUpdateTableListener;
	
	
	//update table seat number
	private JLabel changeNumSeatsLabel;
	private JButton addSeat;
	private JButton removeSeat;
	
	//update table number
	private JLabel updateTableNumberLabel;
	private JLabel tableNumberLabel;
	private JTextField newTableNumber;
	private JButton setTableNumber;
	
	public RestoAppPage() {
		initComponents();
		returnToMainMenu();
		refreshData();
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
		
		Font header = new Font("Dialog", Font.BOLD, 14);
		
		//select table (update table)
		selectTableUpdateTableLabel = new JLabel("Select Table");
		selectTableUpdateTableLabel.setFont(header);
		selectTableUpdateTable = new JComboBox<String>(new String[0]);
		//action listener for combo box removed when combo box rebuilding, added back after (see refreshData())
		selectTableUpdateTable.setMaximumSize(new Dimension(300, 25));
		
		//update seat number
		addSeat = new JButton("Add Seat");
		addSeat.setMaximumSize(new Dimension(136, 20));
		removeSeat = new JButton("Remove Seat");
		removeSeat.setMaximumSize(new Dimension(136, 20));
		changeNumSeatsLabel = new JLabel("Change number of seats");
		changeNumSeatsLabel.setFont(header);
		
		//update table number
		tableNumberLabel = new JLabel("New Table Number:");
		newTableNumber = new JTextField();
		newTableNumber.setMaximumSize(new Dimension(136, 25));
		setTableNumber = new JButton("Set Table Number");
		setTableNumber.setMaximumSize(new Dimension(136, 20));
		updateTableNumberLabel = new JLabel("Change table number");
		updateTableNumberLabel.setFont(header);

		tableNumber = new JLabel("Table Number");
		xCoord = new JLabel("X");
		yCoord = new JLabel("Y");
		tableWidth = new JLabel("Width");
		tableLength = new JLabel("Length");
		numOfSeats = new JLabel("Number of Seats");
		
		tableNumberField = new JTextField("");
		xCoordField = new JTextField("");
		yCoordField = new JTextField("");
		tableWidthField = new JTextField("");
		tableLengthField = new JTextField("");
		numOfSeatsField = new JTextField("");
		
		addTableButton = new JButton("Add");
		
		restoLayout = new RestoLayout(this);
		restoLayoutContainer = new JScrollPane(restoLayout);
	
		restoLayoutContainer.setPreferredSize(new Dimension(1250, 500));
		restoLayoutContainer.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		restoLayoutContainer.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		appetizerMenu  = new JPanel();
		mainMenu  = new JPanel();
		dessertMenu  = new JPanel();
		alcoholicBeverageMenu = new JPanel();
		nonAlcoholicBeverageMenu  = new JPanel();
		
		//elements for remove table
		selectTableRemoveTable = new JComboBox<String>(new String[0]);
		selectTableRemoveTable.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {				
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
		        selectedTable2 = cb.getSelectedIndex();
			}
		});
		selectTableRemoveTableLabel = new JLabel("Select Table:");
		removeTableConfirm = new JButton("Remove this table");
		
		//elements for move table
		tableList = new JComboBox<String>(new String[0]);
		tableList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {				
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
		        selectedTable = cb.getSelectedIndex();
			}
		});
		moveTable = new JLabel("Select Table");
		moveTableButton = new JButton("Confirm");
		

		menu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				returnToMainMenu();
				appetizer.setVisible(true);
				main.setVisible(true);
				dessert.setVisible(true);
				alcoholicBeverage.setVisible(true);
				nonAlcoholicBeverage.setVisible(true);
				refreshData();
			}
		});
		
		changeLayout.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				returnToMainMenu();
				addTable.setVisible(true);
				removeTable.setVisible(true);
				updateTable.setVisible(true);
				changeLocation.setVisible(true);
				refreshData();
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
				refreshData();
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
				refreshData();
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
				refreshData();
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
				refreshData();
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
				refreshData();
			}
		});
		
		addTable.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeUpdateTableSubmenu();
				removeMoveTableSubmenu();
				removeRemoveTableSubmenu();
				
				error = "";
				
				tableNumber.setVisible(true);
				xCoord.setVisible(true);
				yCoord.setVisible(true);
				tableWidth.setVisible(true);
				tableLength.setVisible(true);
				numOfSeats.setVisible(true);
				
				tableNumberField.setVisible(true);
				xCoordField.setVisible(true);
				yCoordField.setVisible(true);
				tableWidthField.setVisible(true);
				tableLengthField.setVisible(true);
				numOfSeatsField.setVisible(true);
				
				addTableButton.setVisible(true);
				
				refreshData();
			}
		});
		
		addTableButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				error = "";
				String tableNumber = tableNumberField.getText();
				String xCoord = xCoordField.getText();
				String yCoord = yCoordField.getText();
				String tableWidth = tableWidthField.getText();
				String tableLength = tableLengthField.getText();
				String numOfSeats = numOfSeatsField.getText();
				if (error.length() == 0) {
					try {
						RestoAppController.createTable(tableNumber, xCoord, yCoord,
															tableWidth, tableLength, numOfSeats);
						
					} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
						error = e.getMessage();
						errorMessage.setText(error);
					}
				}
				refreshData();
			}
		});
		
		updateTable.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeAddTableSubmenu();
				removeMoveTableSubmenu();
				removeRemoveTableSubmenu();
				
				error = "";
				
				selectTableUpdateTable.setVisible(true);
				tableNumberLabel.setVisible(true);
				newTableNumber.setVisible(true);
				setTableNumber.setVisible(true);
				addSeat.setVisible(true);
				removeSeat.setVisible(true);
				selectTableUpdateTableLabel.setVisible(true);
				updateTableNumberLabel.setVisible(true);
				changeNumSeatsLabel.setVisible(true);
				
				refreshData();
			}
		});
		
		setTableNumber.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				error = "";
				
				if (error.length() == 0) {
					try {
						RestoAppController.setTableNumber(tables.get(selectedTable1), Integer.parseInt(newTableNumber.getText()));
						
					} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
						error = "Please provide non-empty numerical input for the table number.";
						errorMessage.setText(error);
					} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
						error = e.getMessage();
						errorMessage.setText(error);
					}
				}
				
				refreshData();
			}
		});
		
		addSeat.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				error = "";
				
				if (error.length() == 0) {
					try {
						RestoAppController.addSeat(tables.get(selectedTable1));
						
					} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
						error = e.getMessage();
						errorMessage.setText(error);
					}
				}
				
				refreshData();
			}
		});
		
		removeSeat.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				error = "";
				
				if (error.length() == 0) {
					try {
						RestoAppController.removeSeat(tables.get(selectedTable1));
						
					} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
						error = e.getMessage();
						errorMessage.setText(error);
					}
				}
				
				refreshData();
			}
		});
		
		removeTable.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeAddTableSubmenu();
				removeUpdateTableSubmenu();
				removeMoveTableSubmenu();
				
				error = "";
				
				selectTableRemoveTable.setVisible(true);
				selectTableRemoveTableLabel.setVisible(true);
				removeTableConfirm.setVisible(true);
				
				refreshData();
			}
		});
		
		removeTableConfirm.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				error = "";
				
				if(restoLayout.getSelectedTable() == tables.get(selectedTable2))
					restoLayout.setSelectedTable(null);
				try {
					RestoAppController.removeTable(tables.get(selectedTable2));
				}
				catch (InvalidInputException e){
					error = e.getMessage();
					errorMessage.setText(error);
				}
				
				refreshData();
			}
		});
		
		changeLocation.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeAddTableSubmenu();
				removeUpdateTableSubmenu();
				removeRemoveTableSubmenu();
				
				error = "";
				
				moveTable.setVisible(true);
				xCoord.setVisible(true);
				yCoord.setVisible(true);
				
				tableList.setVisible(true);
				xCoordField.setVisible(true);
				yCoordField.setVisible(true);
				
				moveTableButton.setVisible(true);
				
				refreshData();
			}
		});
		
		moveTableButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				error = "";
				
				if (error.length() == 0) {
					try {
						int xCoord = Integer.parseInt(xCoordField.getText());
						int yCoord = Integer.parseInt(yCoordField.getText());
						RestoAppController.moveTable(tables.get(selectedTable),xCoord, yCoord);
						
					}
					catch (NumberFormatException e) {
					// TODO Auto-generated catch block
						error = "Please provide non-empty numerical input for table coordinates.";
						errorMessage.setText(error);
					}
					catch (InvalidInputException e) {
					// TODO Auto-generated catch block
						error = e.getMessage();
						errorMessage.setText(error);
					}
				}
				refreshData();
			}
		});
		
		
		JSeparator horizontalLine = new JSeparator();
		Dimension verticalLineSize = new Dimension(0,100);
		JSeparator verticalLine0 = new JSeparator(SwingConstants.VERTICAL);
		verticalLine0.setMaximumSize(verticalLineSize);
		JSeparator verticalLine1 = new JSeparator(SwingConstants.VERTICAL);
		verticalLine1.setMaximumSize(verticalLineSize);
		
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
						.addComponent(nonAlcoholicBeverageMenu)
						.addGroup(layout.createParallelGroup()
								.addGroup(layout.createSequentialGroup()
										.addComponent(tableNumber,100,150,400)
										.addComponent(xCoord)
										.addComponent(yCoord)
										.addComponent(tableWidth)
										.addComponent(tableLength)
										.addComponent(numOfSeats))
								.addGroup(layout.createSequentialGroup()
										.addComponent(tableNumberField)
										.addComponent(xCoordField)
										.addComponent(yCoordField)
										.addComponent(tableWidthField)
										.addComponent(tableLengthField)
										.addComponent(numOfSeatsField))
								.addGroup(layout.createSequentialGroup()
										.addComponent(addTableButton)))
						//move table layout
						.addGroup(layout.createParallelGroup()
								.addGroup(layout.createSequentialGroup()
										.addComponent(moveTable,200,200,400)
										.addComponent(xCoord)
										.addComponent(yCoord))
								.addGroup(layout.createSequentialGroup()
										.addComponent(tableList)
										.addComponent(xCoordField)
										.addComponent(yCoordField))
								.addGroup(layout.createSequentialGroup()
										.addComponent(moveTableButton))))
				
				//update table layout
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
								.addComponent(selectTableUpdateTableLabel)
								.addComponent(selectTableUpdateTable))
						.addComponent(verticalLine0)
						.addGroup(layout.createParallelGroup()
								.addComponent(updateTableNumberLabel)
								.addGroup(layout.createSequentialGroup()
										.addComponent(tableNumberLabel)
										.addGroup(layout.createParallelGroup()
												.addComponent(newTableNumber)
												.addComponent(setTableNumber))))
						.addComponent(verticalLine1)
						.addGroup(layout.createParallelGroup()
								.addComponent(changeNumSeatsLabel)
								.addComponent(addSeat)
								.addComponent(removeSeat)))
				.addGroup(layout.createSequentialGroup()
						.addComponent(selectTableRemoveTableLabel)
						.addGroup(layout.createParallelGroup()
								.addComponent(selectTableRemoveTable)
								.addComponent(removeTableConfirm)))
				.addComponent(restoLayoutContainer)
				);
		
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {menu, appetizer, main, dessert, alcoholicBeverage,
																				nonAlcoholicBeverage, addTable, removeTable, updateTable, 
																				changeLocation});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {tableNumber, xCoord, yCoord, tableWidth, tableLength, numOfSeats,
																				tableNumberField, xCoordField, yCoordField, tableWidthField, 
																				tableLengthField, numOfSeatsField, addTableButton});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {moveTable,xCoord, yCoord, tableList, 
																				xCoordField,yCoordField,moveTableButton});
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
						.addComponent(nonAlcoholicBeverageMenu)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup()
										.addComponent(tableNumber)
										.addComponent(xCoord)
										.addComponent(yCoord)
										.addComponent(tableWidth)
										.addComponent(tableLength)
										.addComponent(numOfSeats))
								.addGroup(layout.createParallelGroup()
										.addComponent(tableNumberField)
										.addComponent(xCoordField)
										.addComponent(yCoordField)
										.addComponent(tableWidthField)
										.addComponent(tableLengthField)
										.addComponent(numOfSeatsField))
								.addComponent(addTableButton))
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup()
										.addComponent(moveTable)
										.addComponent(xCoord)
										.addComponent(yCoord))
								.addGroup(layout.createParallelGroup()
										.addComponent(tableList)
										.addComponent(xCoordField)
										.addComponent(yCoordField))
								.addComponent(moveTableButton)))
				//update table layout
				.addGroup(layout.createParallelGroup()
						.addGroup(layout.createSequentialGroup()
								.addComponent(selectTableUpdateTableLabel)
								.addComponent(selectTableUpdateTable))
						.addComponent(verticalLine0)
						.addGroup(layout.createSequentialGroup()
								.addComponent(updateTableNumberLabel)
								.addGroup(layout.createParallelGroup()
										.addComponent(tableNumberLabel)
										.addGroup(layout.createSequentialGroup()
												.addComponent(newTableNumber)
												.addComponent(setTableNumber))))
						.addComponent(verticalLine1)
						.addGroup(layout.createSequentialGroup()
								.addComponent(changeNumSeatsLabel)
								.addComponent(addSeat)
								.addComponent(removeSeat)))
				.addGroup(layout.createParallelGroup()
						.addComponent(selectTableRemoveTableLabel)
						.addGroup(layout.createSequentialGroup()
								.addComponent(selectTableRemoveTable)
								.addComponent(removeTableConfirm)))

				.addComponent(restoLayoutContainer)
		);
		
	}
	
	private void returnToMainMenu() {
		
		error = "";
		errorMessage.setText(error);

		removeAddTableSubmenu();
		removeMenus();
		removeChangeLayoutSubmenu();
		removeUpdateTableSubmenu();
		removeMenusSubmenu();
		removeMoveTableSubmenu();
		removeRemoveTableSubmenu();
		
		pack();
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
	
	private void removeMenusSubmenu() {
		appetizer.setVisible(false);
		main.setVisible(false);
		dessert.setVisible(false);
		alcoholicBeverage.setVisible(false);
		nonAlcoholicBeverage.setVisible(false);
	}
	
	private void removeChangeLayoutSubmenu() {
		addTable.setVisible(false);
		removeTable.setVisible(false);
		updateTable.setVisible(false);
		changeLocation.setVisible(false);
	}
	
	private void removeAddTableSubmenu() {
		tableNumber.setVisible(false);
		xCoord.setVisible(false);
		yCoord.setVisible(false);
		tableWidth.setVisible(false);
		tableLength.setVisible(false);
		numOfSeats.setVisible(false);
		
		tableNumberField.setVisible(false);
		xCoordField.setVisible(false);
		yCoordField.setVisible(false);
		tableWidthField.setVisible(false);
		tableLengthField.setVisible(false);
		numOfSeatsField.setVisible(false);
		
		addTableButton.setVisible(false);
	}
	
	private void removeUpdateTableSubmenu() {
		selectTableUpdateTable.setVisible(false);
		tableNumberLabel.setVisible(false);
		newTableNumber.setVisible(false);
		setTableNumber.setVisible(false);
		addSeat.setVisible(false);
		removeSeat.setVisible(false);
		selectTableUpdateTableLabel.setVisible(false);
		updateTableNumberLabel.setVisible(false);
		changeNumSeatsLabel.setVisible(false);
	}
	
	private void removeMoveTableSubmenu() {
		moveTable.setVisible(false);
		tableList.setVisible(false);
		moveTableButton.setVisible(false);
	}
	
	private void removeRemoveTableSubmenu() {
		selectTableRemoveTable.setVisible(false);
		selectTableRemoveTableLabel.setVisible(false);
		removeTableConfirm.setVisible(false);
	}
	
	private void refreshData() {
		
		errorMessage.setText(error);
		
		tableNumberField.setText("");
		xCoordField.setText("");
		yCoordField.setText("");
		tableWidthField.setText("");
		tableLengthField.setText("");
		numOfSeatsField.setText("");
		newTableNumber.setText("");
		
		//update table table selector removed to prevent unwanted actionPerformed calls
		selectTableUpdateTable.removeActionListener(selectTableUpdateTableListener);
		
		//move table & update table combo box refresh
		tables = new HashMap<Integer, Table>();
		tablesReverse = new HashMap<Table, Integer>();
		tableList.removeAllItems();
		selectTableUpdateTable.removeAllItems();
		selectTableRemoveTable.removeAllItems();
		Integer index = 0;
		for (Table table : RestoAppController.getCurrentTables()) {
			tables.put(index, table);
			tablesReverse.put(table, index);
			tableList.addItem("#" + table.getNumber());
			selectTableUpdateTable.addItem("#" + table.getNumber());
			selectTableRemoveTable.addItem("#" + table.getNumber());
			index++;
		};
		
		//for move table, combo box resets
		selectedTable = -1;
		tableList.setSelectedIndex(selectedTable);
		
		//for remove table, the combo box resets
		selectedTable2 = -1;
		selectTableRemoveTable.setSelectedIndex(selectedTable2);
		
		//for update table, combo box goes to table selected in restaurant layout, -1 if none selected
		Table layoutSelectedTable = restoLayout.getSelectedTable();
		if(layoutSelectedTable == null)
			selectedTable1 = -1;
		else
			selectedTable1 = tablesReverse.get(layoutSelectedTable);
		selectTableUpdateTable.setSelectedIndex(selectedTable1);
		
		//selectTableUpdateTable re-added
		selectTableUpdateTableListener = new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {				
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedTable1 = cb.getSelectedIndex();
				restoLayout.setSelectedTable(tables.get(selectedTable1));
				System.out.println("Changed to index"+selectedTable1);
			}
		};
		selectTableUpdateTable.addActionListener(selectTableUpdateTableListener);
		
		restoLayout.setTables(RestoAppController.getCurrentTables());
		
		pack();

	}
	
	void tableClicked() {
		selectedTable1 = tablesReverse.get(restoLayout.getSelectedTable());
		selectTableUpdateTable.setSelectedIndex(selectedTable1);
		selectedTable = tablesReverse.get(restoLayout.getSelectedTable());
		tableList.setSelectedIndex(selectedTable);
		selectedTable2 = tablesReverse.get(restoLayout.getSelectedTable());
		selectTableRemoveTable.setSelectedIndex(selectedTable2);
	}

}

	
