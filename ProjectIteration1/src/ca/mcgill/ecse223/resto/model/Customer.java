/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.util.*;
import java.sql.Date;
import java.sql.Time;

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
  private Seat seat;
  private Reservation reservation;
  private List<Order> order;
  private List<BillingGroup> billingGroup;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Customer(String aName, String aPhoneNumber, String aEmailAddress, boolean aHasPaid, boolean aHasReservation, Seat aSeat, Reservation aReservation)
  {
    super(aName, aPhoneNumber, aEmailAddress);
    hasPaid = aHasPaid;
    hasReservation = aHasReservation;
    if (aSeat == null || aSeat.getOccupant() != null)
    {
      throw new RuntimeException("Unable to create Customer due to aSeat");
    }
    seat = aSeat;
    if (aReservation == null || aReservation.getBooker() != null)
    {
      throw new RuntimeException("Unable to create Customer due to aReservation");
    }
    reservation = aReservation;
    order = new ArrayList<Order>();
    billingGroup = new ArrayList<BillingGroup>();
  }

  public Customer(String aName, String aPhoneNumber, String aEmailAddress, boolean aHasPaid, boolean aHasReservation, boolean aIsOccupiedForSeat, Table aTableForSeat, Date aDateForReservation, Time aTimeForReservation, int aNumPersonsForReservation, int aReservationNumberForReservation)
  {
    super(aName, aPhoneNumber, aEmailAddress);
    hasPaid = aHasPaid;
    hasReservation = aHasReservation;
    seat = new Seat(aIsOccupiedForSeat, this, aTableForSeat);
    reservation = new Reservation(aDateForReservation, aTimeForReservation, aNumPersonsForReservation, aReservationNumberForReservation, this);
    order = new ArrayList<Order>();
    billingGroup = new ArrayList<BillingGroup>();
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

  public Seat getSeat()
  {
    return seat;
  }

  public Reservation getReservation()
  {
    return reservation;
  }

  public Order getOrder(int index)
  {
    Order aOrder = order.get(index);
    return aOrder;
  }

  public List<Order> getOrder()
  {
    List<Order> newOrder = Collections.unmodifiableList(order);
    return newOrder;
  }

  public int numberOfOrder()
  {
    int number = order.size();
    return number;
  }

  public boolean hasOrder()
  {
    boolean has = order.size() > 0;
    return has;
  }

  public int indexOfOrder(Order aOrder)
  {
    int index = order.indexOf(aOrder);
    return index;
  }

  public BillingGroup getBillingGroup(int index)
  {
    BillingGroup aBillingGroup = billingGroup.get(index);
    return aBillingGroup;
  }

  public List<BillingGroup> getBillingGroup()
  {
    List<BillingGroup> newBillingGroup = Collections.unmodifiableList(billingGroup);
    return newBillingGroup;
  }

  public int numberOfBillingGroup()
  {
    int number = billingGroup.size();
    return number;
  }

  public boolean hasBillingGroup()
  {
    boolean has = billingGroup.size() > 0;
    return has;
  }

  public int indexOfBillingGroup(BillingGroup aBillingGroup)
  {
    int index = billingGroup.indexOf(aBillingGroup);
    return index;
  }

  public static int minimumNumberOfOrder()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Order addOrder()
  {
    return new Order(this);
  }

  public boolean addOrder(Order aOrder)
  {
    boolean wasAdded = false;
    if (order.contains(aOrder)) { return false; }
    Customer existingOrderPlacer = aOrder.getOrderPlacer();
    boolean isNewOrderPlacer = existingOrderPlacer != null && !this.equals(existingOrderPlacer);
    if (isNewOrderPlacer)
    {
      aOrder.setOrderPlacer(this);
    }
    else
    {
      order.add(aOrder);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeOrder(Order aOrder)
  {
    boolean wasRemoved = false;
    //Unable to remove aOrder, as it must always have a orderPlacer
    if (!this.equals(aOrder.getOrderPlacer()))
    {
      order.remove(aOrder);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addOrderAt(Order aOrder, int index)
  {  
    boolean wasAdded = false;
    if(addOrder(aOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrder()) { index = numberOfOrder() - 1; }
      order.remove(aOrder);
      order.add(index, aOrder);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOrderAt(Order aOrder, int index)
  {
    boolean wasAdded = false;
    if(order.contains(aOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrder()) { index = numberOfOrder() - 1; }
      order.remove(aOrder);
      order.add(index, aOrder);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOrderAt(aOrder, index);
    }
    return wasAdded;
  }

  public boolean isNumberOfBillingGroupValid()
  {
    boolean isValid = numberOfBillingGroup() >= minimumNumberOfBillingGroup();
    return isValid;
  }

  public static int minimumNumberOfBillingGroup()
  {
    return 1;
  }

  public BillingGroup addBillingGroup(Bill aBill)
  {
    BillingGroup aNewBillingGroup = new BillingGroup(this, aBill);
    return aNewBillingGroup;
  }

  public boolean addBillingGroup(BillingGroup aBillingGroup)
  {
    boolean wasAdded = false;
    if (billingGroup.contains(aBillingGroup)) { return false; }
    Customer existingMember = aBillingGroup.getMember();
    boolean isNewMember = existingMember != null && !this.equals(existingMember);

    if (isNewMember && existingMember.numberOfBillingGroup() <= minimumNumberOfBillingGroup())
    {
      return wasAdded;
    }
    if (isNewMember)
    {
      aBillingGroup.setMember(this);
    }
    else
    {
      billingGroup.add(aBillingGroup);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBillingGroup(BillingGroup aBillingGroup)
  {
    boolean wasRemoved = false;
    //Unable to remove aBillingGroup, as it must always have a member
    if (this.equals(aBillingGroup.getMember()))
    {
      return wasRemoved;
    }

    //member already at minimum (1)
    if (numberOfBillingGroup() <= minimumNumberOfBillingGroup())
    {
      return wasRemoved;
    }

    billingGroup.remove(aBillingGroup);
    wasRemoved = true;
    return wasRemoved;
  }

  public boolean addBillingGroupAt(BillingGroup aBillingGroup, int index)
  {  
    boolean wasAdded = false;
    if(addBillingGroup(aBillingGroup))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBillingGroup()) { index = numberOfBillingGroup() - 1; }
      billingGroup.remove(aBillingGroup);
      billingGroup.add(index, aBillingGroup);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBillingGroupAt(BillingGroup aBillingGroup, int index)
  {
    boolean wasAdded = false;
    if(billingGroup.contains(aBillingGroup))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBillingGroup()) { index = numberOfBillingGroup() - 1; }
      billingGroup.remove(aBillingGroup);
      billingGroup.add(index, aBillingGroup);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBillingGroupAt(aBillingGroup, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Seat existingSeat = seat;
    seat = null;
    if (existingSeat != null)
    {
      existingSeat.delete();
    }
    Reservation existingReservation = reservation;
    reservation = null;
    if (existingReservation != null)
    {
      existingReservation.delete();
    }
    for(int i=order.size(); i > 0; i--)
    {
      Order aOrder = order.get(i - 1);
      aOrder.delete();
    }
    for(int i=billingGroup.size(); i > 0; i--)
    {
      BillingGroup aBillingGroup = billingGroup.get(i - 1);
      aBillingGroup.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "hasPaid" + ":" + getHasPaid()+ "," +
            "hasReservation" + ":" + getHasReservation()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "seat = "+(getSeat()!=null?Integer.toHexString(System.identityHashCode(getSeat())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "reservation = "+(getReservation()!=null?Integer.toHexString(System.identityHashCode(getReservation())):"null");
  }
}