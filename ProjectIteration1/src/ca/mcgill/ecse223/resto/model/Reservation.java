/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.sql.Date;
import java.sql.Time;
import java.util.*;

// line 34 "../../../../../RestoApp1a.ump"
public class Reservation
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Reservation Attributes
  private Date date;
  private Time time;
  private int numPersons;
  private int reservationNumber;

  //Reservation Associations
  private Customer booker;
  private List<Table> table;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Reservation(Date aDate, Time aTime, int aNumPersons, int aReservationNumber, Customer aBooker)
  {
    date = aDate;
    time = aTime;
    numPersons = aNumPersons;
    reservationNumber = aReservationNumber;
    if (aBooker == null || aBooker.getReservation() != null)
    {
      throw new RuntimeException("Unable to create Reservation due to aBooker");
    }
    booker = aBooker;
    table = new ArrayList<Table>();
  }

  public Reservation(Date aDate, Time aTime, int aNumPersons, int aReservationNumber, String aNameForBooker, String aPhoneNumberForBooker, String aEmailAddressForBooker, boolean aHasPaidForBooker, boolean aHasReservationForBooker, Seat aSeatForBooker)
  {
    date = aDate;
    time = aTime;
    numPersons = aNumPersons;
    reservationNumber = aReservationNumber;
    booker = new Customer(aNameForBooker, aPhoneNumberForBooker, aEmailAddressForBooker, aHasPaidForBooker, aHasReservationForBooker, aSeatForBooker, this);
    table = new ArrayList<Table>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setTime(Time aTime)
  {
    boolean wasSet = false;
    time = aTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumPersons(int aNumPersons)
  {
    boolean wasSet = false;
    numPersons = aNumPersons;
    wasSet = true;
    return wasSet;
  }

  public boolean setReservationNumber(int aReservationNumber)
  {
    boolean wasSet = false;
    reservationNumber = aReservationNumber;
    wasSet = true;
    return wasSet;
  }

  public Date getDate()
  {
    return date;
  }

  public Time getTime()
  {
    return time;
  }

  public int getNumPersons()
  {
    return numPersons;
  }

  public int getReservationNumber()
  {
    return reservationNumber;
  }

  public Customer getBooker()
  {
    return booker;
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

  public boolean isNumberOfTableValid()
  {
    boolean isValid = numberOfTable() >= minimumNumberOfTable();
    return isValid;
  }

  public static int minimumNumberOfTable()
  {
    return 1;
  }

  public Table addTable(int aTableNumber, int aNumSeats, boolean aIsAvailable, TableLocation aLocation, Waiter aWaiter)
  {
    Table aNewTable = new Table(aTableNumber, aNumSeats, aIsAvailable, this, aLocation, aWaiter);
    return aNewTable;
  }

  public boolean addTable(Table aTable)
  {
    boolean wasAdded = false;
    if (table.contains(aTable)) { return false; }
    Reservation existingReservation = aTable.getReservation();
    boolean isNewReservation = existingReservation != null && !this.equals(existingReservation);

    if (isNewReservation && existingReservation.numberOfTable() <= minimumNumberOfTable())
    {
      return wasAdded;
    }
    if (isNewReservation)
    {
      aTable.setReservation(this);
    }
    else
    {
      table.add(aTable);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTable(Table aTable)
  {
    boolean wasRemoved = false;
    //Unable to remove aTable, as it must always have a reservation
    if (this.equals(aTable.getReservation()))
    {
      return wasRemoved;
    }

    //reservation already at minimum (1)
    if (numberOfTable() <= minimumNumberOfTable())
    {
      return wasRemoved;
    }

    table.remove(aTable);
    wasRemoved = true;
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
    Customer existingBooker = booker;
    booker = null;
    if (existingBooker != null)
    {
      existingBooker.delete();
    }
    for(int i=table.size(); i > 0; i--)
    {
      Table aTable = table.get(i - 1);
      aTable.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "numPersons" + ":" + getNumPersons()+ "," +
            "reservationNumber" + ":" + getReservationNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "time" + "=" + (getTime() != null ? !getTime().equals(this)  ? getTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "booker = "+(getBooker()!=null?Integer.toHexString(System.identityHashCode(getBooker())):"null");
  }
}