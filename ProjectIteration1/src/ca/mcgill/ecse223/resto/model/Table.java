/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.util.*;

// line 19 "../../../../../RestoApp1a.ump"
public class Table
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Table Attributes
  private int tableNumber;
  private int numSeats;
  private boolean isAvailable;

  //Table Associations
  private List<Seat> seating;
  private Reservation reservedFor;
  private TableLocation locatedAt;
  private Waiter waitedBy;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Table(int aTableNumber, int aNumSeats, boolean aIsAvailable, Reservation aReservedFor, TableLocation aLocatedAt, Waiter aWaitedBy)
  {
    tableNumber = aTableNumber;
    numSeats = aNumSeats;
    isAvailable = aIsAvailable;
    seating = new ArrayList<Seat>();
    boolean didAddReservedFor = setReservedFor(aReservedFor);
    if (!didAddReservedFor)
    {
      throw new RuntimeException("Unable to create reserve due to reservedFor");
    }
    if (aLocatedAt == null || aLocatedAt.getOccupiedBy() != null)
    {
      throw new RuntimeException("Unable to create Table due to aLocatedAt");
    }
    locatedAt = aLocatedAt;
    boolean didAddWaitedBy = setWaitedBy(aWaitedBy);
    if (!didAddWaitedBy)
    {
      throw new RuntimeException("Unable to create waitsOn due to waitedBy");
    }
  }

  public Table(int aTableNumber, int aNumSeats, boolean aIsAvailable, Reservation aReservedFor, String aLocationNameForLocatedAt, Waiter aWaitedBy)
  {
    tableNumber = aTableNumber;
    numSeats = aNumSeats;
    isAvailable = aIsAvailable;
    seating = new ArrayList<Seat>();
    boolean didAddReservedFor = setReservedFor(aReservedFor);
    if (!didAddReservedFor)
    {
      throw new RuntimeException("Unable to create reserve due to reservedFor");
    }
    locatedAt = new TableLocation(aLocationNameForLocatedAt, this);
    boolean didAddWaitedBy = setWaitedBy(aWaitedBy);
    if (!didAddWaitedBy)
    {
      throw new RuntimeException("Unable to create waitsOn due to waitedBy");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTableNumber(int aTableNumber)
  {
    boolean wasSet = false;
    tableNumber = aTableNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumSeats(int aNumSeats)
  {
    boolean wasSet = false;
    numSeats = aNumSeats;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsAvailable(boolean aIsAvailable)
  {
    boolean wasSet = false;
    isAvailable = aIsAvailable;
    wasSet = true;
    return wasSet;
  }

  public int getTableNumber()
  {
    return tableNumber;
  }

  public int getNumSeats()
  {
    return numSeats;
  }

  public boolean getIsAvailable()
  {
    return isAvailable;
  }

  public Seat getSeating(int index)
  {
    Seat aSeating = seating.get(index);
    return aSeating;
  }

  public List<Seat> getSeating()
  {
    List<Seat> newSeating = Collections.unmodifiableList(seating);
    return newSeating;
  }

  public int numberOfSeating()
  {
    int number = seating.size();
    return number;
  }

  public boolean hasSeating()
  {
    boolean has = seating.size() > 0;
    return has;
  }

  public int indexOfSeating(Seat aSeating)
  {
    int index = seating.indexOf(aSeating);
    return index;
  }

  public Reservation getReservedFor()
  {
    return reservedFor;
  }

  public TableLocation getLocatedAt()
  {
    return locatedAt;
  }

  public Waiter getWaitedBy()
  {
    return waitedBy;
  }

  public static int minimumNumberOfSeating()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Seat addSeating(boolean aIsOccupied, Customer aSeating)
  {
    return new Seat(aIsOccupied, aSeating, this);
  }

  public boolean addSeating(Seat aSeating)
  {
    boolean wasAdded = false;
    if (seating.contains(aSeating)) { return false; }
    Table existingAssignedTo = aSeating.getAssignedTo();
    boolean isNewAssignedTo = existingAssignedTo != null && !this.equals(existingAssignedTo);
    if (isNewAssignedTo)
    {
      aSeating.setAssignedTo(this);
    }
    else
    {
      seating.add(aSeating);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSeating(Seat aSeating)
  {
    boolean wasRemoved = false;
    //Unable to remove aSeating, as it must always have a assignedTo
    if (!this.equals(aSeating.getAssignedTo()))
    {
      seating.remove(aSeating);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addSeatingAt(Seat aSeating, int index)
  {  
    boolean wasAdded = false;
    if(addSeating(aSeating))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSeating()) { index = numberOfSeating() - 1; }
      seating.remove(aSeating);
      seating.add(index, aSeating);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSeatingAt(Seat aSeating, int index)
  {
    boolean wasAdded = false;
    if(seating.contains(aSeating))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSeating()) { index = numberOfSeating() - 1; }
      seating.remove(aSeating);
      seating.add(index, aSeating);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSeatingAt(aSeating, index);
    }
    return wasAdded;
  }

  public boolean setReservedFor(Reservation aReservedFor)
  {
    boolean wasSet = false;
    //Must provide reservedFor to reserve
    if (aReservedFor == null)
    {
      return wasSet;
    }

    if (reservedFor != null && reservedFor.numberOfReserves() <= Reservation.minimumNumberOfReserves())
    {
      return wasSet;
    }

    Reservation existingReservedFor = reservedFor;
    reservedFor = aReservedFor;
    if (existingReservedFor != null && !existingReservedFor.equals(aReservedFor))
    {
      boolean didRemove = existingReservedFor.removeReserve(this);
      if (!didRemove)
      {
        reservedFor = existingReservedFor;
        return wasSet;
      }
    }
    reservedFor.addReserve(this);
    wasSet = true;
    return wasSet;
  }

  public boolean setWaitedBy(Waiter aWaitedBy)
  {
    boolean wasSet = false;
    if (aWaitedBy == null)
    {
      return wasSet;
    }

    Waiter existingWaitedBy = waitedBy;
    waitedBy = aWaitedBy;
    if (existingWaitedBy != null && !existingWaitedBy.equals(aWaitedBy))
    {
      existingWaitedBy.removeWaitsOn(this);
    }
    waitedBy.addWaitsOn(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=seating.size(); i > 0; i--)
    {
      Seat aSeating = seating.get(i - 1);
      aSeating.delete();
    }
    Reservation placeholderReservedFor = reservedFor;
    this.reservedFor = null;
    if(placeholderReservedFor != null)
    {
      placeholderReservedFor.removeReserve(this);
    }
    TableLocation existingLocatedAt = locatedAt;
    locatedAt = null;
    if (existingLocatedAt != null)
    {
      existingLocatedAt.delete();
    }
    Waiter placeholderWaitedBy = waitedBy;
    this.waitedBy = null;
    if(placeholderWaitedBy != null)
    {
      placeholderWaitedBy.removeWaitsOn(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "tableNumber" + ":" + getTableNumber()+ "," +
            "numSeats" + ":" + getNumSeats()+ "," +
            "isAvailable" + ":" + getIsAvailable()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "reservedFor = "+(getReservedFor()!=null?Integer.toHexString(System.identityHashCode(getReservedFor())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "locatedAt = "+(getLocatedAt()!=null?Integer.toHexString(System.identityHashCode(getLocatedAt())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "waitedBy = "+(getWaitedBy()!=null?Integer.toHexString(System.identityHashCode(getWaitedBy())):"null");
  }
}