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
  private Table table;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TableLocation(String aLocationName, Table aTable)
  {
    locationName = aLocationName;
    if (aTable == null || aTable.getLocation() != null)
    {
      throw new RuntimeException("Unable to create TableLocation due to aTable");
    }
    table = aTable;
  }

  public TableLocation(String aLocationName, int aTableNumberForTable, int aNumSeatsForTable, boolean aIsAvailableForTable, Reservation aReservationForTable, Waiter aWaiterForTable)
  {
    locationName = aLocationName;
    table = new Table(aTableNumberForTable, aNumSeatsForTable, aIsAvailableForTable, aReservationForTable, this, aWaiterForTable);
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

  public Table getTable()
  {
    return table;
  }

  public void delete()
  {
    Table existingTable = table;
    table = null;
    if (existingTable != null)
    {
      existingTable.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "locationName" + ":" + getLocationName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "table = "+(getTable()!=null?Integer.toHexString(System.identityHashCode(getTable())):"null");
  }
}