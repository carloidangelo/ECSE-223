/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.util.*;

// line 88 "../../../../../RestoApp1a.ump"
public class Restaurant
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Restaurant Attributes
  private String restaurantName;

  //Restaurant Associations
  private List<Table> table;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Restaurant(String aRestaurantName)
  {
    restaurantName = aRestaurantName;
    table = new ArrayList<Table>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setRestaurantName(String aRestaurantName)
  {
    boolean wasSet = false;
    restaurantName = aRestaurantName;
    wasSet = true;
    return wasSet;
  }

  public String getRestaurantName()
  {
    return restaurantName;
  }

  public Table getTable(int index)
  {
    Table aTable = table.get(index);
    return aTable;
  }

  public List<Table> getTable()
  {
    List<Table> newTable = Collections.unmodifiableList(table);
    return newTable;
  }

  public int numberOfTable()
  {
    int number = table.size();
    return number;
  }

  public boolean hasTable()
  {
    boolean has = table.size() > 0;
    return has;
  }

  public int indexOfTable(Table aTable)
  {
    int index = table.indexOf(aTable);
    return index;
  }

  public static int minimumNumberOfTable()
  {
    return 0;
  }

  public boolean addTable(Table aTable)
  {
    boolean wasAdded = false;
    if (table.contains(aTable)) { return false; }
    table.add(aTable);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTable(Table aTable)
  {
    boolean wasRemoved = false;
    if (table.contains(aTable))
    {
      table.remove(aTable);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addTableAt(Table aTable, int index)
  {  
    boolean wasAdded = false;
    if(addTable(aTable))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTable()) { index = numberOfTable() - 1; }
      table.remove(aTable);
      table.add(index, aTable);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTableAt(Table aTable, int index)
  {
    boolean wasAdded = false;
    if(table.contains(aTable))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTable()) { index = numberOfTable() - 1; }
      table.remove(aTable);
      table.add(index, aTable);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTableAt(aTable, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    table.clear();
  }


  public String toString()
  {
    return super.toString() + "["+
            "restaurantName" + ":" + getRestaurantName()+ "]";
  }
}