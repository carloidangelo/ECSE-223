/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.util.*;

// line 3 "../../../../../RestoApp1a.ump"
public class Customer extends Person
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Customer Attributes
  private boolean hasPaid;
  private boolean hasReservation;

  //Customer Associations
  private Seat sittingIn;
  private Reservation booked;
  private List<Order> placed;
  private List<BillingGroup> partOf;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Customer(String aName, String aPhoneNumber, String aEmailAddress, boolean aHasPaid, boolean aHasReservation, Seat aSittingIn, Reservation aBooked)
  {
    super(aName, aPhoneNumber, aEmailAddress);
    hasPaid = aHasPaid;
    hasReservation = aHasReservation;
    if (aSittingIn == null || aSittingIn.getSeating() != null)
    {
      throw new RuntimeException("Unable to create Customer due to aSittingIn");
    }
    sittingIn = aSittingIn;
    if (aBooked == null || aBooked.getBookedBy() != null)
    {
      throw new RuntimeException("Unable to create Customer due to aBooked");
    }
    booked = aBooked;
    placed = new ArrayList<Order>();
    partOf = new ArrayList<BillingGroup>();
  }

  public Customer(String aName, String aPhoneNumber, String aEmailAddress, boolean aHasPaid, boolean aHasReservation, boolean aIsOccupiedForSittingIn, Table aAssignedToForSittingIn, String aDateForBooked, String aTimeForBooked, int aNumPersonsForBooked, int aReservationNumberForBooked)
  {
    super(aName, aPhoneNumber, aEmailAddress);
    hasPaid = aHasPaid;
    hasReservation = aHasReservation;
    sittingIn = new Seat(aIsOccupiedForSittingIn, this, aAssignedToForSittingIn);
    booked = new Reservation(aDateForBooked, aTimeForBooked, aNumPersonsForBooked, aReservationNumberForBooked, this);
    placed = new ArrayList<Order>();
    partOf = new ArrayList<BillingGroup>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setHasPaid(boolean aHasPaid)
  {
    boolean wasSet = false;
    hasPaid = aHasPaid;
    wasSet = true;
    return wasSet;
  }

  public boolean setHasReservation(boolean aHasReservation)
  {
    boolean wasSet = false;
    hasReservation = aHasReservation;
    wasSet = true;
    return wasSet;
  }

  public boolean getHasPaid()
  {
    return hasPaid;
  }

  public boolean getHasReservation()
  {
    return hasReservation;
  }

  public Seat getSittingIn()
  {
    return sittingIn;
  }

  public Reservation getBooked()
  {
    return booked;
  }

  public Order getPlaced(int index)
  {
    Order aPlaced = placed.get(index);
    return aPlaced;
  }

  public List<Order> getPlaced()
  {
    List<Order> newPlaced = Collections.unmodifiableList(placed);
    return newPlaced;
  }

  public int numberOfPlaced()
  {
    int number = placed.size();
    return number;
  }

  public boolean hasPlaced()
  {
    boolean has = placed.size() > 0;
    return has;
  }

  public int indexOfPlaced(Order aPlaced)
  {
    int index = placed.indexOf(aPlaced);
    return index;
  }

  public BillingGroup getPartOf(int index)
  {
    BillingGroup aPartOf = partOf.get(index);
    return aPartOf;
  }

  public List<BillingGroup> getPartOf()
  {
    List<BillingGroup> newPartOf = Collections.unmodifiableList(partOf);
    return newPartOf;
  }

  public int numberOfPartOf()
  {
    int number = partOf.size();
    return number;
  }

  public boolean hasPartOf()
  {
    boolean has = partOf.size() > 0;
    return has;
  }

  public int indexOfPartOf(BillingGroup aPartOf)
  {
    int index = partOf.indexOf(aPartOf);
    return index;
  }

  public static int minimumNumberOfPlaced()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Order addPlaced()
  {
    return new Order(this);
  }

  public boolean addPlaced(Order aPlaced)
  {
    boolean wasAdded = false;
    if (placed.contains(aPlaced)) { return false; }
    Customer existingPlacedBy = aPlaced.getPlacedBy();
    boolean isNewPlacedBy = existingPlacedBy != null && !this.equals(existingPlacedBy);
    if (isNewPlacedBy)
    {
      aPlaced.setPlacedBy(this);
    }
    else
    {
      placed.add(aPlaced);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePlaced(Order aPlaced)
  {
    boolean wasRemoved = false;
    //Unable to remove aPlaced, as it must always have a placedBy
    if (!this.equals(aPlaced.getPlacedBy()))
    {
      placed.remove(aPlaced);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addPlacedAt(Order aPlaced, int index)
  {  
    boolean wasAdded = false;
    if(addPlaced(aPlaced))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlaced()) { index = numberOfPlaced() - 1; }
      placed.remove(aPlaced);
      placed.add(index, aPlaced);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePlacedAt(Order aPlaced, int index)
  {
    boolean wasAdded = false;
    if(placed.contains(aPlaced))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlaced()) { index = numberOfPlaced() - 1; }
      placed.remove(aPlaced);
      placed.add(index, aPlaced);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPlacedAt(aPlaced, index);
    }
    return wasAdded;
  }

  public boolean isNumberOfPartOfValid()
  {
    boolean isValid = numberOfPartOf() >= minimumNumberOfPartOf();
    return isValid;
  }

  public static int minimumNumberOfPartOf()
  {
    return 1;
  }

  public BillingGroup addPartOf(Bill aPays)
  {
    BillingGroup aNewPartOf = new BillingGroup(this, aPays);
    return aNewPartOf;
  }

  public boolean addPartOf(BillingGroup aPartOf)
  {
    boolean wasAdded = false;
    if (partOf.contains(aPartOf)) { return false; }
    Customer existingContains = aPartOf.getContains();
    boolean isNewContains = existingContains != null && !this.equals(existingContains);

    if (isNewContains && existingContains.numberOfPartOf() <= minimumNumberOfPartOf())
    {
      return wasAdded;
    }
    if (isNewContains)
    {
      aPartOf.setContains(this);
    }
    else
    {
      partOf.add(aPartOf);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePartOf(BillingGroup aPartOf)
  {
    boolean wasRemoved = false;
    //Unable to remove aPartOf, as it must always have a contains
    if (this.equals(aPartOf.getContains()))
    {
      return wasRemoved;
    }

    //contains already at minimum (1)
    if (numberOfPartOf() <= minimumNumberOfPartOf())
    {
      return wasRemoved;
    }

    partOf.remove(aPartOf);
    wasRemoved = true;
    return wasRemoved;
  }

  public boolean addPartOfAt(BillingGroup aPartOf, int index)
  {  
    boolean wasAdded = false;
    if(addPartOf(aPartOf))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPartOf()) { index = numberOfPartOf() - 1; }
      partOf.remove(aPartOf);
      partOf.add(index, aPartOf);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePartOfAt(BillingGroup aPartOf, int index)
  {
    boolean wasAdded = false;
    if(partOf.contains(aPartOf))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPartOf()) { index = numberOfPartOf() - 1; }
      partOf.remove(aPartOf);
      partOf.add(index, aPartOf);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPartOfAt(aPartOf, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Seat existingSittingIn = sittingIn;
    sittingIn = null;
    if (existingSittingIn != null)
    {
      existingSittingIn.delete();
    }
    Reservation existingBooked = booked;
    booked = null;
    if (existingBooked != null)
    {
      existingBooked.delete();
    }
    for(int i=placed.size(); i > 0; i--)
    {
      Order aPlaced = placed.get(i - 1);
      aPlaced.delete();
    }
    for(int i=partOf.size(); i > 0; i--)
    {
      BillingGroup aPartOf = partOf.get(i - 1);
      aPartOf.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "hasPaid" + ":" + getHasPaid()+ "," +
            "hasReservation" + ":" + getHasReservation()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "sittingIn = "+(getSittingIn()!=null?Integer.toHexString(System.identityHashCode(getSittingIn())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "booked = "+(getBooked()!=null?Integer.toHexString(System.identityHashCode(getBooked())):"null");
  }
}