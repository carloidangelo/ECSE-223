package ca.mcgill.ecse223.resto.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.swing.*;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.controller.RestoAppController;
import ca.mcgill.ecse223.resto.model.MenuItem;
import ca.mcgill.ecse223.resto.model.MenuItem.ItemCategory;

import ca.mcgill.ecse223.resto.model.RestoApp;
import ca.mcgill.ecse223.resto.model.Table;
import ca.mcgill.ecse223.resto.model.Order;


public class RestoAppPage extends JFrame {
	
	private static final long serialVersionUID = -4426310869335015542L;
	
	//Error
	private String error = "";
	private JLabel errorMessage;
	
	//Main Menu
	private JButton menu;
	private JButton changeLayout;
	private JButton reservation;
	private JButton changeTableStatus;
	
	//Menu SubMenu
	private JButton appetizer;
	private JButton main; 
	private JButton dessert;
	private JButton alcoholicBeverage; 
	private JButton nonAlcoholicBeverage;
	
	//Change Layout SubMenu	
	private JButton addTable;
	private JButton removeTable;
	private JButton updateTable;
	private JButton changeLocation;
	
	//Reservation SubMenu
	private JButton reserveTable;
	
	//Menu SubMenu SubMenu
	private JPanel appetizerMenu;
	private JPanel mainMenu;
	private JPanel dessertMenu;
	private JPanel alcoholicBeverageMenu; 
	private JPanel nonAlcoholicBeverageMenu;
	
	//Add Table SubMenu
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
	
	//Remove Table SubMenu
	private JComboBox <String> selectTableRemoveTable;
	private JLabel selectTableRemoveTableLabel;
	private JButton removeTableConfirm;
	private Integer selectedTable2 = -1;
	
	/*Update Table SubMenu*/
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
	
	//Move Table SubMenu
	private JComboBox <String> tableList;
		
	private JLabel moveTable;
	
	private JLabel xCoord2;
	private JLabel yCoord2;
	private JTextField xCoordField2;
	private JTextField yCoordField2;
		
	private JButton moveTableButton;
	private Integer selectedTable = -1;
	
	//Reserve Table SubMenu
	private JLabel RESSelectTable;
	private JLabel RESDate;
	private JLabel RESTime;
	private JLabel RESNumberInParty;
	private JLabel RESContactName;
	private JLabel RESContactEmail;
	private JLabel RESContactPhoneNumber;
	
	private JTextField RESTimeField;
	private JTextField RESNumberInPartyField;
	private JTextField RESContactNameField;
	private JTextField RESContactEmailField;
	private JTextField RESContactPhoneNumberField;
	
	private JButton RESMakeReservation;
	private JDatePickerImpl RESDateCalendar;
	private JPanel RESSelectTableMenu;
	private JScrollPane RESSelectTableMenuScroll;
	
	//Change table status submenu
	private JComboBox <String> SelectGroupList;
	
	private JLabel CHGTABSTASelectTable;
	private JLabel CHGTABSTADate;
	private JLabel CHGTABSTATime;
	private JLabel CHGTABSTASelectGroup;
	
	private Integer SelectedGroup = -1;
	
	private JTextField CHGTABSTATimeField;
	private JTextField CHGTABSTASelectGroupField;
	
	private JButton CHGTABSTAAssignTables;
	private JButton CHGTABSTARemoveGroup;
	
	private JDatePickerImpl CHGTABSTADateCalendar;
	private JPanel CHGTABSTASelectTableMenu;
	private JScrollPane CHGTABSTASelectTableMenuScroll;
	
	//select group combo box hashmap
	private HashMap<Integer, Order> groups;
	

	//Restaurant Layout
	private RestoLayout restoLayout;
	private JScrollPane restoLayoutContainer;
	
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
		
		//Error
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		
		//Main Menu		
		menu = new JButton("Menu");
		changeLayout = new JButton("Restaurant Layout");
		reservation = new JButton("Reservation");
		changeTableStatus = new JButton("Change Table Status"); 
		
		//Menu SubMenu
		appetizer = new JButton("Appetizer");
		main = new JButton("Main");
		dessert = new JButton("Dessert");
		alcoholicBeverage = new JButton("Alcoholic Beverage"); 
		nonAlcoholicBeverage = new JButton("Non-alcoholic Beverage");
		
		//Change Layout SubMenu
		addTable = new JButton("Add Table");
		removeTable = new JButton("Remove Table");
		updateTable = new JButton("Update Table");
		changeLocation = new JButton("Change Table Location");
		
		//Reservation SubMenu
		reserveTable = new JButton("Reserve Table");
		
		//Menu SubMenu SubMenu
		appetizerMenu  = new JPanel();
		mainMenu  = new JPanel();
		dessertMenu  = new JPanel();
		alcoholicBeverageMenu = new JPanel();
		nonAlcoholicBeverageMenu  = new JPanel();
		
		//Add Table SubMenu
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
		
		//Remove Table SubMenu
		selectTableRemoveTable = new JComboBox<String>(new String[0]);
		selectTableRemoveTable.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {				
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedTable2 = cb.getSelectedIndex();
			}
		});
		selectTableRemoveTableLabel = new JLabel("Select Table:");
		removeTableConfirm = new JButton("Remove this table");
				
		/*Update Table SubMenu*/
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
		
		//Move Table SubMenu
		tableList = new JComboBox<String>(new String[0]);
		tableList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {				
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
		        selectedTable = cb.getSelectedIndex();
			}
		});
		moveTable = new JLabel("Select Table");
		moveTableButton = new JButton("Confirm");
		xCoord2 = new JLabel("X");
		yCoord2 = new JLabel("Y");
		xCoordField2 = new JTextField("");
		yCoordField2 = new JTextField("");
		
		//Reserve Table SubMenu
		RESSelectTable = new JLabel("Select Table(s)");
		RESDate = new JLabel("Date");
		RESTime = new JLabel("Time");
		RESNumberInParty = new JLabel("Number in party");
		RESContactName = new JLabel("Contact Name");
		RESContactEmail = new JLabel("Contact Email");
		RESContactPhoneNumber = new JLabel("Contact Phone");
		
		RESTimeField = new JTextField("");
		RESNumberInPartyField = new JTextField("");
		RESContactNameField = new JTextField("");
		RESContactEmailField = new JTextField("");
		RESContactPhoneNumberField = new JTextField("");
		
		RESMakeReservation = new JButton("Make Reservation");
			
		SqlDateModel model = new SqlDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		RESDateCalendar = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		
		RESSelectTableMenu = new JPanel();
		RESSelectTableMenuScroll = new JScrollPane(RESSelectTableMenu);
		RESSelectTableMenuScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		//Change Table Status submenu
		CHGTABSTASelectTable = new JLabel("Select Table(s)");
		CHGTABSTADate = new JLabel("Date");
		CHGTABSTATime = new JLabel("Time");
		CHGTABSTASelectGroup = new JLabel("Select Group");
		
		CHGTABSTATimeField = new JTextField("");
		CHGTABSTASelectGroupField = new JTextField("");
		
		CHGTABSTAAssignTables = new JButton("Assign Tables");
		CHGTABSTARemoveGroup = new JButton("Remove Group");
		
		CHGTABSTADateCalendar = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		
		CHGTABSTASelectTableMenu = new JPanel();
		CHGTABSTASelectTableMenuScroll = new JScrollPane(CHGTABSTASelectTableMenu);
		CHGTABSTASelectTableMenuScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		SelectGroupList = new JComboBox<String>(new String[0]);
		SelectGroupList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {				
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				SelectedGroup = cb.getSelectedIndex();
			}
		});
		
		//Action Listeners
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
		
		reservation.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				returnToMainMenu();
				reserveTable.setVisible(true);
				refreshData();
			}
		});
		
		changeTableStatus.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				returnToMainMenu();
				error = "";
				
				SelectGroupList.setVisible(true);
				
				CHGTABSTASelectTable.setVisible(true);
				CHGTABSTADate.setVisible(true);
				CHGTABSTATime.setVisible(true);
				CHGTABSTASelectGroup.setVisible(true);
								
				CHGTABSTATimeField.setVisible(true);
				CHGTABSTASelectGroupField.setVisible(true);
				
				CHGTABSTAAssignTables.setVisible(true);
				CHGTABSTARemoveGroup.setVisible(true);
				
				CHGTABSTADateCalendar.setVisible(true);
				CHGTABSTASelectTableMenu.setVisible(true);
				CHGTABSTASelectTableMenuScroll.setVisible(true);

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
					appetizerMenu.add(button = new JButton(menuitem.getName() + "   $" + String.valueOf(menuitem.getCurrentPricedMenuItem().getPrice())),constraint);
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
					mainMenu.add(button = new JButton(menuitem.getName() + "   $" + String.valueOf(menuitem.getCurrentPricedMenuItem().getPrice())),constraint);
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
					dessertMenu.add(button = new JButton(menuitem.getName() + "   $" + String.valueOf(menuitem.getCurrentPricedMenuItem().getPrice())),constraint);
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
					alcoholicBeverageMenu.add(button = new JButton(menuitem.getName() + "   $" + String.valueOf(menuitem.getCurrentPricedMenuItem().getPrice())),constraint);
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
					nonAlcoholicBeverageMenu.add(button = new JButton(menuitem.getName() + "   $" + String.valueOf(menuitem.getCurrentPricedMenuItem().getPrice())),constraint);
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
		
		changeLocation.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeAddTableSubmenu();
				removeUpdateTableSubmenu();
				removeRemoveTableSubmenu();
				
				error = "";
				
				moveTable.setVisible(true);
				xCoord2.setVisible(true);
				yCoord2.setVisible(true);
				
				tableList.setVisible(true);
				xCoordField2.setVisible(true);
				yCoordField2.setVisible(true);
				
				moveTableButton.setVisible(true);
				
				refreshData();
			}
		});
		
		moveTableButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				error = "";
				
				if (error.length() == 0) {
					try {
						int xCoord = Integer.parseInt(xCoordField2.getText());
						int yCoord = Integer.parseInt(yCoordField2.getText());
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
		
		reserveTable.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				error = "";
				
				RESSelectTable.setVisible(true);
				RESDate.setVisible(true);
				RESTime.setVisible(true);
				RESNumberInParty.setVisible(true);
				RESContactName.setVisible(true);
				RESContactEmail.setVisible(true);
				RESContactPhoneNumber.setVisible(true);
				
				RESTimeField.setVisible(true);
				RESNumberInPartyField.setVisible(true);
				RESContactNameField.setVisible(true);
				RESContactEmailField.setVisible(true);
				RESContactPhoneNumberField.setVisible(true);
				
				RESMakeReservation.setVisible(true);
				RESDateCalendar.setVisible(true);
				RESSelectTableMenuScroll.setVisible(true);

				refreshData();
			}
		});
		
		CHGTABSTAAssignTables.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				error = "";
				
				//TODO
				
				refreshData();
			}
		});
		
		//Restaurant Layout
		restoLayout = new RestoLayout(this);
		restoLayoutContainer = new JScrollPane(restoLayout);
	
		restoLayoutContainer.setPreferredSize(new Dimension(1250, 500));
		restoLayoutContainer.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		restoLayoutContainer.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		//Layout
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
				//Error
				.addComponent(errorMessage)
				//Main Menu
				.addGroup(layout.createSequentialGroup()
						.addComponent(menu,200,200,400)
						.addComponent(changeLayout)
						.addComponent(reservation)
						.addComponent(changeTableStatus))
				.addComponent(horizontalLine)
				//Menu SubMenu
				.addGroup(layout.createSequentialGroup()
						.addComponent(appetizer)
						.addComponent(main)
						.addComponent(dessert)
						.addComponent(alcoholicBeverage)
						.addComponent(nonAlcoholicBeverage))
				//Change Layout SubMenu
				.addGroup(layout.createSequentialGroup()
						.addComponent(addTable)
						.addComponent(removeTable)
						.addComponent(updateTable)
						.addComponent(changeLocation))
				//Reservation SubMenu
				.addGroup(layout.createSequentialGroup()
						.addComponent(reserveTable))
				//Menu SubMenu SubMenu
				.addGroup(layout.createSequentialGroup()
						.addComponent(appetizerMenu)
						.addComponent(mainMenu)
						.addComponent(dessertMenu)
						.addComponent(alcoholicBeverageMenu)
						.addComponent(nonAlcoholicBeverageMenu))
				//Add Table SubMenu
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
				//Remove Table SubMenu
				.addGroup(layout.createSequentialGroup()
						.addComponent(selectTableRemoveTableLabel)
						.addGroup(layout.createParallelGroup()
								.addComponent(selectTableRemoveTable,400,400,500)
								.addComponent(removeTableConfirm)))
				//Update Table SubMenu
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
				//Move Table SubMenu
				.addGroup(layout.createParallelGroup()
						.addGroup(layout.createSequentialGroup()
								.addComponent(moveTable,200,200,400)
								.addComponent(xCoord2)
								.addComponent(yCoord2))
						.addGroup(layout.createSequentialGroup()
								.addComponent(tableList)
								.addComponent(xCoordField2)
								.addComponent(yCoordField2))
						.addGroup(layout.createSequentialGroup()
								.addComponent(moveTableButton)))
				//Reserve Table SubMenu
				.addGroup(layout.createParallelGroup()
						.addComponent(RESSelectTable,100,150,200)	
						.addGroup(layout.createSequentialGroup()
								.addComponent(RESSelectTableMenuScroll,500,500,600)
								.addGroup(layout.createParallelGroup()
										.addGroup(layout.createSequentialGroup()
												.addComponent(RESDate)
												.addComponent(RESTime)
												.addComponent(RESNumberInParty))
										.addGroup(layout.createSequentialGroup()
												.addComponent(RESDateCalendar)
												.addComponent(RESTimeField)
												.addComponent(RESNumberInPartyField))
										.addGroup(layout.createSequentialGroup()
												.addComponent(RESContactName)
												.addComponent(RESContactEmail)
												.addComponent(RESContactPhoneNumber))
										.addGroup(layout.createSequentialGroup()
												.addComponent(RESContactNameField)
												.addComponent(RESContactEmailField)
												.addComponent(RESContactPhoneNumberField))
										.addComponent(RESMakeReservation,200,200,400))))	
				
				//Change table status SubMenu
				.addGroup(layout.createParallelGroup()
						.addComponent(CHGTABSTASelectTable,100,150,200)	
						.addGroup(layout.createSequentialGroup()
								.addComponent(CHGTABSTASelectTableMenuScroll,500,500,600)
								.addGroup(layout.createParallelGroup()
										.addGroup(layout.createSequentialGroup()
												.addComponent(CHGTABSTADate)
												.addComponent(CHGTABSTATime))
										.addGroup(layout.createSequentialGroup()
												.addComponent(CHGTABSTADateCalendar)
												.addComponent(CHGTABSTATimeField)
												.addComponent(CHGTABSTAAssignTables))
										.addGroup(layout.createSequentialGroup()
												.addComponent(CHGTABSTASelectGroup))
										.addGroup(layout.createSequentialGroup()
												.addComponent(SelectGroupList)
												.addComponent(CHGTABSTARemoveGroup)))))
				//Restaurant Layout
				.addComponent(restoLayoutContainer));
		
		//Menu
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {changeLayout, reservation, changeTableStatus});
		
		//Menu SubMenu
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {menu, appetizer, main, dessert, alcoholicBeverage,
																				nonAlcoholicBeverage, addTable, removeTable, updateTable, 
																				changeLocation, reserveTable});
		//AddTable
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {tableNumber, xCoord, yCoord, tableWidth, tableLength, numOfSeats,
																				tableNumberField, xCoordField, yCoordField, tableWidthField, 
																				tableLengthField, numOfSeatsField, addTableButton});
		//Move Table
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {moveTable,xCoord2, yCoord2, tableList, 
																				xCoordField2,yCoordField2,moveTableButton});
		//Reserve Table
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {RESSelectTable,RESDate, RESTime, RESNumberInParty, 
																				RESDateCalendar,RESTimeField,RESNumberInPartyField,RESContactName,
																				RESContactEmail, RESContactPhoneNumber, RESContactNameField, RESContactEmailField, 
																				RESContactPhoneNumberField});
		//Change Table Status
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {SelectGroupList, CHGTABSTADate, CHGTABSTATime, CHGTABSTAAssignTables, 
																				CHGTABSTADateCalendar, CHGTABSTADateCalendar, CHGTABSTATimeField, 
																				CHGTABSTASelectGroup, CHGTABSTARemoveGroup});

		layout.setVerticalGroup(
				layout.createSequentialGroup()
				//Error
				.addComponent(errorMessage)
				//Main Menu
				.addGroup(layout.createParallelGroup()
						.addComponent(menu)
						.addComponent(changeLayout)
						.addComponent(reservation)
						.addComponent(changeTableStatus))
				.addComponent(horizontalLine)
				//Menu SubMenu
				.addGroup(layout.createParallelGroup()
						.addComponent(appetizer)
						.addComponent(main)
						.addComponent(dessert)
						.addComponent(alcoholicBeverage)
						.addComponent(nonAlcoholicBeverage))
				//Change Layout SubMenu
				.addGroup(layout.createParallelGroup()
						.addComponent(addTable)
						.addComponent(removeTable)
						.addComponent(updateTable)
						.addComponent(changeLocation))
				//Reservation SubMenu
				.addGroup(layout.createParallelGroup()
						.addComponent(reserveTable))
				//Menu SubMenu SubMenu
				.addGroup(layout.createParallelGroup()
						.addComponent(appetizerMenu)
						.addComponent(mainMenu)
						.addComponent(dessertMenu)
						.addComponent(alcoholicBeverageMenu)
						.addComponent(nonAlcoholicBeverageMenu))
				//Add Table SubMenu
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
				//Remove Table SubMenu
				.addGroup(layout.createParallelGroup()
						.addComponent(selectTableRemoveTableLabel)
						.addGroup(layout.createSequentialGroup()
								.addComponent(selectTableRemoveTable)
								.addComponent(removeTableConfirm)))
				//Update Table SubMenu
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
				//Move Table SubMenu
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
								.addComponent(moveTable)
								.addComponent(xCoord2)
								.addComponent(yCoord2))
						.addGroup(layout.createParallelGroup()
								.addComponent(tableList)
								.addComponent(xCoordField2)
								.addComponent(yCoordField2))
						.addComponent(moveTableButton))
				//Reserve Table SubMenu
				.addGroup(layout.createSequentialGroup()
						.addComponent(RESSelectTable)	
						.addGroup(layout.createParallelGroup()
								.addComponent(RESSelectTableMenuScroll,75,75,200)
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup()
												.addComponent(RESDate)
												.addComponent(RESTime)
												.addComponent(RESNumberInParty))
										.addGroup(layout.createParallelGroup()
												.addComponent(RESDateCalendar)
												.addComponent(RESTimeField)
												.addComponent(RESNumberInPartyField))
										.addGroup(layout.createParallelGroup()
												.addComponent(RESContactName)
												.addComponent(RESContactEmail)
												.addComponent(RESContactPhoneNumber))
										.addGroup(layout.createParallelGroup()
												.addComponent(RESContactNameField)
												.addComponent(RESContactEmailField)
												.addComponent(RESContactPhoneNumberField))
										.addComponent(RESMakeReservation))))
				
				//Change Table Status SubMenu
				.addGroup(layout.createSequentialGroup()
						.addComponent(CHGTABSTASelectTable)	
						.addGroup(layout.createParallelGroup()
								.addComponent(CHGTABSTASelectTableMenuScroll,75,75,200)
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup()
												.addComponent(CHGTABSTADate)
												.addComponent(CHGTABSTATime))
										.addGroup(layout.createParallelGroup()
												.addComponent(CHGTABSTADateCalendar)
												.addComponent(CHGTABSTATimeField)
												.addComponent(CHGTABSTAAssignTables))
										.addGroup(layout.createParallelGroup()
												.addComponent(CHGTABSTASelectGroup))
										.addGroup(layout.createParallelGroup()
												.addComponent(SelectGroupList)
												.addComponent(CHGTABSTARemoveGroup)))))	
				
				//Restaurant Layout
				.addComponent(restoLayoutContainer)
		);
	
	}
	
	private void returnToMainMenu() {
		
		error = "";
		errorMessage.setText(error);

		removeMenusSubmenu();
		removeChangeLayoutSubmenu();
		removeReservationSubmenu();
		removeMenus();
		removeAddTableSubmenu();
		removeRemoveTableSubmenu();
		removeUpdateTableSubmenu();
		removeMoveTableSubmenu();
		removeReserveTableSubmenu();
		removeChangeTableStatusSubmenu();
		
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
	
	private void removeReservationSubmenu() {
		reserveTable.setVisible(false);
	}
	
	private void removeChangeTableStatusSubmenu() {
		SelectGroupList.setVisible(false);
		
		CHGTABSTASelectTable.setVisible(false);
		CHGTABSTADate.setVisible(false);
		CHGTABSTATime.setVisible(false);
		CHGTABSTASelectGroup.setVisible(false);
						
		CHGTABSTATimeField.setVisible(false);
		CHGTABSTASelectGroupField.setVisible(false);
		
		CHGTABSTAAssignTables.setVisible(false);
		CHGTABSTARemoveGroup.setVisible(false);
		
		CHGTABSTADateCalendar.setVisible(false);
		CHGTABSTASelectTableMenu.setVisible(false);
		CHGTABSTASelectTableMenuScroll.setVisible(false);
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
	
	private void removeRemoveTableSubmenu() {
		selectTableRemoveTable.setVisible(false);
		selectTableRemoveTableLabel.setVisible(false);
		removeTableConfirm.setVisible(false);
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
		xCoord2.setVisible(false);
		yCoord2.setVisible(false);
		xCoordField2.setVisible(false);
		yCoordField2.setVisible(false);
	}
	
	private void removeReserveTableSubmenu() {
		RESSelectTable.setVisible(false);
		RESDate.setVisible(false);
		RESTime.setVisible(false);
		RESNumberInParty.setVisible(false);
		RESContactName.setVisible(false);
		RESContactEmail.setVisible(false);
		RESContactPhoneNumber.setVisible(false);
		
		RESTimeField.setVisible(false);
		RESNumberInPartyField.setVisible(false);
		RESContactNameField.setVisible(false);
		RESContactEmailField.setVisible(false);
		RESContactPhoneNumberField.setVisible(false);
		
		RESMakeReservation.setVisible(false);
		RESDateCalendar.setVisible(false);
		RESSelectTableMenu.removeAll();
		RESSelectTableMenuScroll.setVisible(false);
	}
	
	
	
	private void refreshData() {
		
		errorMessage.setText(error);
		
		tableNumberField.setText("");
		xCoordField.setText("");
		yCoordField.setText("");
		xCoordField2.setText("");
		yCoordField2.setText("");
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
		
		//change table status combo box refresh
		groups = new HashMap<Integer, Order>();
		SelectGroupList.removeAllItems();
		Integer indexGroup = 0;
		String tablesInGroup = new String();
		for (Order order : RestoAppController.getCurrentOrders()) {
			groups.put(indexGroup, order);
			for (int i = 0; i<order.numberOfTables(); i++)
			{	
				tablesInGroup = "";
				Table currentTable = order.getTable(i);
				tablesInGroup += "#" + currentTable.getNumber() + " ";
			}
			
			SelectGroupList.addItem(tablesInGroup);
			indexGroup++;
		}
	
		
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
			}
		};
		selectTableUpdateTable.addActionListener(selectTableUpdateTableListener);
		
		RESDateCalendar.getModel().setValue(null);
		RESSelectTableMenu.removeAll();
		int sizeY = 10;
		for (Table table : RestoAppController.getCurrentTables()){
			JCheckBox tableCheckBox;
			RESSelectTableMenu.add(tableCheckBox = new JCheckBox("Table #" + String.valueOf(table.getNumber())));
			RESSelectTableMenu.setPreferredSize(new Dimension(500, sizeY));
			sizeY += 10;
		}
		
		CHGTABSTADateCalendar.getModel().setValue(null);
		CHGTABSTASelectTableMenu.removeAll();
		int sizeY2 = 10;
		for (Table table : RestoAppController.getCurrentTables()){
			JCheckBox tableCheckBox;
			CHGTABSTASelectTableMenu.add(tableCheckBox = new JCheckBox("Table #" + String.valueOf(table.getNumber())));
			CHGTABSTASelectTableMenu.setPreferredSize(new Dimension(500, sizeY));
			sizeY2 += 10;
		}
		
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

	
