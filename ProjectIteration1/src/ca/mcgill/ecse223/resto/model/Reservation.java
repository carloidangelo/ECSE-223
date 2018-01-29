/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.util.*;

// line 34 "../../../../../RestoApp1a.ump"
public class Reservation
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Reservation Attributes
  private String date;
  private String time;
  private int numPersons;
  private int reservationNumber;

  //Reservation Associations
  private Customer bookedBy;
  private List<Table> reserves;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Reservation(String aDate, String aTime, int aNumPersons, int aReservationNumber, Customer aBookedBy)
  {
    date = aDate;
    time = aTime;
    numPersons = aNumPersons;
    reservationNumber = aReservationNumber;
    if (aBookedBy == null || aBookedBy.getBooked() != null)
    {
      throw new RuntimeException("Unable to create Reservation due to aBookedBy");
    }
    bookedBy = aBookedBy;
    reserves = new ArrayList<Table>();
  }

  public Reservation(String aDate, String aTime, int aNumPersons, int aReservationNumber, String aNameForBookedBy, String aPhoneNumberForBookedBy, String aEmailAddressForBookedBy, boolean aHasPaidForBookedBy, boolean aHasReservationForBookedBy, Seat aSittingInForBookedBy)
  {
    date = aDate;
    time = aTime;
    numPersons = aNumPersons;
    reservationNumber = aReservationNumber;
    bookedBy = new Customer(aNameForBookedBy, aPhoneNumberForBookedBy, aEmailAddressForBookedBy, aHasPaidForBookedBy, aHasReservationForBookedBy, aSittingInForBookedBy, this);
    reserves = new ArrayList<Table>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDate(String aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setTime(String aTime)
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

  public String getDate()
  {
    return date;
  }

  public String getTime()
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

  public Customer getBookedBy()
  {
    return bookedBy;
  }

  public Table getReserve(int index)
  {
    Table aReserve = reserves.get(index);
    return aReserve;
  }

  public List<Table> getReserves()
  {
    List<Table> newReserves = Collections.unmodifiableList(reserves);
    return newReserves;
  }

  public int numberOfReserves()
  {
    int number = reserves.size();
    return number;
  }

  public boolean hasReserves()
  {
    boolean has = reserves.size() > 0;
    return has;
  }

  public int indexOfReserve(Table aReserve)
  {
    int index = reserves.indexOf(aReserve);
    return index;
  }

  public boolean isNumberOfReservesValid()
  {
    boolean isValid = numberOfReserves() >= minimumNumberOfReserves();
    return isValid;
  }

  public static int minimumNumberOfReserves()
  {
    return 1;
  }

  public Table addReserve(int aTableNumber, int aNumSeats, boolean aIsAvailable, TableLocation aLocatedAt, Waiter aWaitedBy)
  {
    Table aNewReserve = new Table(aTableNumber, aNumSeats, aIsAvailable, this, aLocatedAt, aWaitedBy);
    return aNewReserve;
  }

  public boolean addReserve(Table aReserve)
  {
    boolean wasAdded = false;
    if (reserves.contains(aReserve)) { return false; }
    Reservation existingReservedFor = aReserve.getReservedFor();
    boolean isNewReservedFor = existingReservedFor != null && !this.equals(existingReservedFor);

    if (isNewReservedFor && existingReservedFor.numberOfReserves() <= minimumNumberOfReserves())
    {
      return wasAdded;
    }
    if (isNewReservedFor)
    {
      aReserve.setReservedFor(this);
    }
    else
    {
      reserves.add(aReserve);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeReserve(Table aReserve)
  {
    boolean wasRemoved = false;
    //Unable to remove aReserve, as it must always have a reservedFor
    if (this.equals(aReserve.getReservedFor()))
    {
      return wasRemoved;
    }

    //reservedFor already at minimum (1)
    if (numberOfReserves() <= minimumNumberOfReserves())
    {
      return wasRemoved;
    }

    reserves.remove(aReserve);
    wasRemoved = true;
    return wasRemoved;
  }

  public boolean addReserveAt(Table aReserve, int index)
  {  
    boolean wasAdded = false;
    if(addReserve(aReserve))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReserves()) { index = numberOfReserves() - 1; }
      reserves.remove(aReserve);
      reserves.add(index, aReserve);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveReserveAt(Table aReserve, int index)
  {
    boolean wasAdded = false;
    if(reserves.contains(aReserve))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReserves()) { index = numberOfReserves() - 1; }
      reserves.remove(aReserve);
      reserves.add(index, aReserve);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addReserveAt(aReserve, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Customer existingBookedBy = bookedBy;
    bookedBy = null;
    if (existingBookedBy != null)
    {
      existingBookedBy.delete();
    }
    for(int i=reserves.size(); i > 0; i--)
    {
      Table aReserve = reserves.get(i - 1);
      aReserve.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "date" + ":" + getDate()+ "," +
            "time" + ":" + getTime()+ "," +
            "numPersons" + ":" + getNumPersons()+ "," +
            "reservationNumber" + ":" + getReservationNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "bookedBy = "+(getBookedBy()!=null?Integer.toHexString(System.identityHashCode(getBookedBy())):"null");
  }
}