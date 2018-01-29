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
  private Customer seating;
  private Table assignedTo;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Seat(boolean aIsOccupied, Customer aSeating, Table aAssignedTo)
  {
    isOccupied = aIsOccupied;
    if (aSeating == null || aSeating.getSittingIn() != null)
    {
      throw new RuntimeException("Unable to create Seat due to aSeating");
    }
    seating = aSeating;
    boolean didAddAssignedTo = setAssignedTo(aAssignedTo);
    if (!didAddAssignedTo)
    {
      throw new RuntimeException("Unable to create seating due to assignedTo");
    }
  }

  public Seat(boolean aIsOccupied, String aNameForSeating, String aPhoneNumberForSeating, String aEmailAddressForSeating, boolean aHasPaidForSeating, boolean aHasReservationForSeating, Reservation aBookedForSeating, Table aAssignedTo)
  {
    isOccupied = aIsOccupied;
    seating = new Customer(aNameForSeating, aPhoneNumberForSeating, aEmailAddressForSeating, aHasPaidForSeating, aHasReservationForSeating, this, aBookedForSeating);
    boolean didAddAssignedTo = setAssignedTo(aAssignedTo);
    if (!didAddAssignedTo)
    {
      throw new RuntimeException("Unable to create seating due to assignedTo");
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

  public Customer getSeating()
  {
    return seating;
  }

  public Table getAssignedTo()
  {
    return assignedTo;
  }

  public boolean setAssignedTo(Table aAssignedTo)
  {
    boolean wasSet = false;
    if (aAssignedTo == null)
    {
      return wasSet;
    }

    Table existingAssignedTo = assignedTo;
    assignedTo = aAssignedTo;
    if (existingAssignedTo != null && !existingAssignedTo.equals(aAssignedTo))
    {
      existingAssignedTo.removeSeating(this);
    }
    assignedTo.addSeating(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Customer existingSeating = seating;
    seating = null;
    if (existingSeating != null)
    {
      existingSeating.delete();
    }
    Table placeholderAssignedTo = assignedTo;
    this.assignedTo = null;
    if(placeholderAssignedTo != null)
    {
      placeholderAssignedTo.removeSeating(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "isOccupied" + ":" + getIsOccupied()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "seating = "+(getSeating()!=null?Integer.toHexString(System.identityHashCode(getSeating())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "assignedTo = "+(getAssignedTo()!=null?Integer.toHexString(System.identityHashCode(getAssignedTo())):"null");
  }
}