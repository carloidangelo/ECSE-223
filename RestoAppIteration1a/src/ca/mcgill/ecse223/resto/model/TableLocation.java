/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;

// line 29 "../../../../../RestoApp1a.ump"
public class TableLocation
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TableLocation Attributes
  private String locationName;

  //TableLocation Associations
  private Table occupiedBy;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TableLocation(String aLocationName, Table aOccupiedBy)
  {
    locationName = aLocationName;
    if (aOccupiedBy == null || aOccupiedBy.getLocatedAt() != null)
    {
      throw new RuntimeException("Unable to create TableLocation due to aOccupiedBy");
    }
    occupiedBy = aOccupiedBy;
  }

  public TableLocation(String aLocationName, int aTableNumberForOccupiedBy, int aNumSeatsForOccupiedBy, boolean aIsAvailableForOccupiedBy, Reservation aReservedForForOccupiedBy, Waiter aWaitedByForOccupiedBy)
  {
    locationName = aLocationName;
    occupiedBy = new Table(aTableNumberForOccupiedBy, aNumSeatsForOccupiedBy, aIsAvailableForOccupiedBy, aReservedForForOccupiedBy, this, aWaitedByForOccupiedBy);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setLocationName(String aLocationName)
  {
    boolean wasSet = false;
    locationName = aLocationName;
    wasSet = true;
    return wasSet;
  }

  public String getLocationName()
  {
    return locationName;
  }

  public Table getOccupiedBy()
  {
    return occupiedBy;
  }

  public void delete()
  {
    Table existingOccupiedBy = occupiedBy;
    occupiedBy = null;
    if (existingOccupiedBy != null)
    {
      existingOccupiedBy.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "locationName" + ":" + getLocationName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "occupiedBy = "+(getOccupiedBy()!=null?Integer.toHexString(System.identityHashCode(getOccupiedBy())):"null");
  }
}