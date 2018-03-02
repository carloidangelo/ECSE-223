/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.util.*;

// line 49 "../../../../../RestoApp1a.ump"
public class Waiter extends Person
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Waiter Associations
  private List<Table> assignedTable;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Waiter(String aName, String aPhoneNumber, String aEmailAddress)
  {
    super(aName, aPhoneNumber, aEmailAddress);
    assignedTable = new ArrayList<Table>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Table getAssignedTable(int index)
  {
    Table aAssignedTable = assignedTable.get(index);
    return aAssignedTable;
  }

  public List<Table> getAssignedTable()
  {
    List<Table> newAssignedTable = Collections.unmodifiableList(assignedTable);
    return newAssignedTable;
  }

  public int numberOfAssignedTable()
  {
    int number = assignedTable.size();
    return number;
  }

  public boolean hasAssignedTable()
  {
    boolean has = assignedTable.size() > 0;
    return has;
  }

  public int indexOfAssignedTable(Table aAssignedTable)
  {
    int index = assignedTable.indexOf(aAssignedTable);
    return index;
  }

  public static int minimumNumberOfAssignedTable()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Table addAssignedTable(int aTableNumber, int aNumSeats, boolean aIsAvailable, Reservation aReservation, TableLocation aLocation)
  {
    return new Table(aTableNumber, aNumSeats, aIsAvailable, aReservation, aLocation, this);
  }

  public boolean addAssignedTable(Table aAssignedTable)
  {
    boolean wasAdded = false;
    if (assignedTable.contains(aAssignedTable)) { return false; }
    Waiter existingWaiter = aAssignedTable.getWaiter();
    boolean isNewWaiter = existingWaiter != null && !this.equals(existingWaiter);
    if (isNewWaiter)
    {
      aAssignedTable.setWaiter(this);
    }
    else
    {
      assignedTable.add(aAssignedTable);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAssignedTable(Table aAssignedTable)
  {
    boolean wasRemoved = false;
    //Unable to remove aAssignedTable, as it must always have a waiter
    if (!this.equals(aAssignedTable.getWaiter()))
    {
      assignedTable.remove(aAssignedTable);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addAssignedTableAt(Table aAssignedTable, int index)
  {  
    boolean wasAdded = false;
    if(addAssignedTable(aAssignedTable))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssignedTable()) { index = numberOfAssignedTable() - 1; }
      assignedTable.remove(aAssignedTable);
      assignedTable.add(index, aAssignedTable);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAssignedTableAt(Table aAssignedTable, int index)
  {
    boolean wasAdded = false;
    if(assignedTable.contains(aAssignedTable))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssignedTable()) { index = numberOfAssignedTable() - 1; }
      assignedTable.remove(aAssignedTable);
      assignedTable.add(index, aAssignedTable);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAssignedTableAt(aAssignedTable, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=assignedTable.size(); i > 0; i--)
    {
      Table aAssignedTable = assignedTable.get(i - 1);
      aAssignedTable.delete();
    }
    super.delete();
  }

}