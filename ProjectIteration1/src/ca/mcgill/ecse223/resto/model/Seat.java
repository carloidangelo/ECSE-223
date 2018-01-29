/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;

// line 14 "../../../../../RestoApp1a.ump"
public class Seat
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Seat Attributes
  private boolean isOccupied;

  //Seat Associations
  private Customer occupant;
  private Table table;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Seat(boolean aIsOccupied, Customer aOccupant, Table aTable)
  {
    isOccupied = aIsOccupied;
    if (aOccupant == null || aOccupant.getSeat() != null)
    {
      throw new RuntimeException("Unable to create Seat due to aOccupant");
    }
    occupant = aOccupant;
    boolean didAddTable = setTable(aTable);
    if (!didAddTable)
    {
      throw new RuntimeException("Unable to create seat due to table");
    }
  }

  public Seat(boolean aIsOccupied, String aNameForOccupant, String aPhoneNumberForOccupant, String aEmailAddressForOccupant, boolean aHasPaidForOccupant, boolean aHasReservationForOccupant, Reservation aReservationForOccupant, Table aTable)
  {
    isOccupied = aIsOccupied;
    occupant = new Customer(aNameForOccupant, aPhoneNumberForOccupant, aEmailAddressForOccupant, aHasPaidForOccupant, aHasReservationForOccupant, this, aReservationForOccupant);
    boolean didAddTable = setTable(aTable);
    if (!didAddTable)
    {
      throw new RuntimeException("Unable to create seat due to table");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setIsOccupied(boolean aIsOccupied)
  {
    boolean wasSet = false;
    isOccupied = aIsOccupied;
    wasSet = true;
    return wasSet;
  }

  public boolean getIsOccupied()
  {
    return isOccupied;
  }

  public Customer getOccupant()
  {
    return occupant;
  }

  public Table getTable()
  {
    return table;
  }

  public boolean setTable(Table aTable)
  {
    boolean wasSet = false;
    if (aTable == null)
    {
      return wasSet;
    }

    Table existingTable = table;
    table = aTable;
    if (existingTable != null && !existingTable.equals(aTable))
    {
      existingTable.removeSeat(this);
    }
    table.addSeat(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Customer existingOccupant = occupant;
    occupant = null;
    if (existingOccupant != null)
    {
      existingOccupant.delete();
    }
    Table placeholderTable = table;
    this.table = null;
    if(placeholderTable != null)
    {
      placeholderTable.removeSeat(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "isOccupied" + ":" + getIsOccupied()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "occupant = "+(getOccupant()!=null?Integer.toHexString(System.identityHashCode(getOccupant())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "table = "+(getTable()!=null?Integer.toHexString(System.identityHashCode(getTable())):"null");
  }
}