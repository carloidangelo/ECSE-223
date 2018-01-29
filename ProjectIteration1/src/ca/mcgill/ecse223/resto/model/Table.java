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
  private List<Seat> seat;
  private Reservation reservation;
  private TableLocation location;
  private Waiter waiter;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Table(int aTableNumber, int aNumSeats, boolean aIsAvailable, Reservation aReservation, TableLocation aLocation, Waiter aWaiter)
  {
    tableNumber = aTableNumber;
    numSeats = aNumSeats;
    isAvailable = aIsAvailable;
    seat = new ArrayList<Seat>();
    boolean didAddReservation = setReservation(aReservation);
    if (!didAddReservation)
    {
      throw new RuntimeException("Unable to create table due to reservation");
    }
    if (aLocation == null || aLocation.getTable() != null)
    {
      throw new RuntimeException("Unable to create Table due to aLocation");
    }
    location = aLocation;
    boolean didAddWaiter = setWaiter(aWaiter);
    if (!didAddWaiter)
    {
      throw new RuntimeException("Unable to create assignedTable due to waiter");
    }
  }

  public Table(int aTableNumber, int aNumSeats, boolean aIsAvailable, Reservation aReservation, String aLocationNameForLocation, Waiter aWaiter)
  {
    tableNumber = aTableNumber;
    numSeats = aNumSeats;
    isAvailable = aIsAvailable;
    seat = new ArrayList<Seat>();
    boolean didAddReservation = setReservation(aReservation);
    if (!didAddReservation)
    {
      throw new RuntimeException("Unable to create table due to reservation");
    }
    location = new TableLocation(aLocationNameForLocation, this);
    boolean didAddWaiter = setWaiter(aWaiter);
    if (!didAddWaiter)
    {
      throw new RuntimeException("Unable to create assignedTable due to waiter");
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

  public Seat getSeat(int index)
  {
    Seat aSeat = seat.get(index);
    return aSeat;
  }

  public List<Seat> getSeat()
  {
    List<Seat> newSeat = Collections.unmodifiableList(seat);
    return newSeat;
  }

  public int numberOfSeat()
  {
    int number = seat.size();
    return number;
  }

  public boolean hasSeat()
  {
    boolean has = seat.size() > 0;
    return has;
  }

  public int indexOfSeat(Seat aSeat)
  {
    int index = seat.indexOf(aSeat);
    return index;
  }

  public Reservation getReservation()
  {
    return reservation;
  }

  public TableLocation getLocation()
  {
    return location;
  }

  public Waiter getWaiter()
  {
    return waiter;
  }

  public static int minimumNumberOfSeat()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Seat addSeat(boolean aIsOccupied, Customer aOccupant)
  {
    return new Seat(aIsOccupied, aOccupant, this);
  }

  public boolean addSeat(Seat aSeat)
  {
    boolean wasAdded = false;
    if (seat.contains(aSeat)) { return false; }
    Table existingTable = aSeat.getTable();
    boolean isNewTable = existingTable != null && !this.equals(existingTable);
    if (isNewTable)
    {
      aSeat.setTable(this);
    }
    else
    {
      seat.add(aSeat);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSeat(Seat aSeat)
  {
    boolean wasRemoved = false;
    //Unable to remove aSeat, as it must always have a table
    if (!this.equals(aSeat.getTable()))
    {
      seat.remove(aSeat);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addSeatAt(Seat aSeat, int index)
  {  
    boolean wasAdded = false;
    if(addSeat(aSeat))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSeat()) { index = numberOfSeat() - 1; }
      seat.remove(aSeat);
      seat.add(index, aSeat);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSeatAt(Seat aSeat, int index)
  {
    boolean wasAdded = false;
    if(seat.contains(aSeat))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSeat()) { index = numberOfSeat() - 1; }
      seat.remove(aSeat);
      seat.add(index, aSeat);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSeatAt(aSeat, index);
    }
    return wasAdded;
  }

  public boolean setReservation(Reservation aReservation)
  {
    boolean wasSet = false;
    //Must provide reservation to table
    if (aReservation == null)
    {
      return wasSet;
    }

    if (reservation != null && reservation.numberOfTable() <= Reservation.minimumNumberOfTable())
    {
      return wasSet;
    }

    Reservation existingReservation = reservation;
    reservation = aReservation;
    if (existingReservation != null && !existingReservation.equals(aReservation))
    {
      boolean didRemove = existingReservation.removeTable(this);
      if (!didRemove)
      {
        reservation = existingReservation;
        return wasSet;
      }
    }
    reservation.addTable(this);
    wasSet = true;
    return wasSet;
  }

  public boolean setWaiter(Waiter aWaiter)
  {
    boolean wasSet = false;
    if (aWaiter == null)
    {
      return wasSet;
    }

    Waiter existingWaiter = waiter;
    waiter = aWaiter;
    if (existingWaiter != null && !existingWaiter.equals(aWaiter))
    {
      existingWaiter.removeAssignedTable(this);
    }
    waiter.addAssignedTable(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=seat.size(); i > 0; i--)
    {
      Seat aSeat = seat.get(i - 1);
      aSeat.delete();
    }
    Reservation placeholderReservation = reservation;
    this.reservation = null;
    if(placeholderReservation != null)
    {
      placeholderReservation.removeTable(this);
    }
    TableLocation existingLocation = location;
    location = null;
    if (existingLocation != null)
    {
      existingLocation.delete();
    }
    Waiter placeholderWaiter = waiter;
    this.waiter = null;
    if(placeholderWaiter != null)
    {
      placeholderWaiter.removeAssignedTable(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "tableNumber" + ":" + getTableNumber()+ "," +
            "numSeats" + ":" + getNumSeats()+ "," +
            "isAvailable" + ":" + getIsAvailable()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "reservation = "+(getReservation()!=null?Integer.toHexString(System.identityHashCode(getReservation())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "location = "+(getLocation()!=null?Integer.toHexString(System.identityHashCode(getLocation())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "waiter = "+(getWaiter()!=null?Integer.toHexString(System.identityHashCode(getWaiter())):"null");
  }
}