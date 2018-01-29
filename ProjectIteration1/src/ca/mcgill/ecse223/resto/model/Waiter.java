/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.util.*;

// line 49 "../../../../../RestoApp1a.ump"
public class Waiter extends Person
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Waiter Associations
  private List<Table> waitsOn;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Waiter(String aName, String aPhoneNumber, String aEmailAddress)
  {
    super(aName, aPhoneNumber, aEmailAddress);
    waitsOn = new ArrayList<Table>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Table getWaitsOn(int index)
  {
    Table aWaitsOn = waitsOn.get(index);
    return aWaitsOn;
  }

  public List<Table> getWaitsOn()
  {
    List<Table> newWaitsOn = Collections.unmodifiableList(waitsOn);
    return newWaitsOn;
  }

  public int numberOfWaitsOn()
  {
    int number = waitsOn.size();
    return number;
  }

  public boolean hasWaitsOn()
  {
    boolean has = waitsOn.size() > 0;
    return has;
  }

  public int indexOfWaitsOn(Table aWaitsOn)
  {
    int index = waitsOn.indexOf(aWaitsOn);
    return index;
  }

  public static int minimumNumberOfWaitsOn()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Table addWaitsOn(int aTableNumber, int aNumSeats, boolean aIsAvailable, Reservation aReservedFor, TableLocation aLocatedAt)
  {
    return new Table(aTableNumber, aNumSeats, aIsAvailable, aReservedFor, aLocatedAt, this);
  }

  public boolean addWaitsOn(Table aWaitsOn)
  {
    boolean wasAdded = false;
    if (waitsOn.contains(aWaitsOn)) { return false; }
    Waiter existingWaitedBy = aWaitsOn.getWaitedBy();
    boolean isNewWaitedBy = existingWaitedBy != null && !this.equals(existingWaitedBy);
    if (isNewWaitedBy)
    {
      aWaitsOn.setWaitedBy(this);
    }
    else
    {
      waitsOn.add(aWaitsOn);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeWaitsOn(Table aWaitsOn)
  {
    boolean wasRemoved = false;
    //Unable to remove aWaitsOn, as it must always have a waitedBy
    if (!this.equals(aWaitsOn.getWaitedBy()))
    {
      waitsOn.remove(aWaitsOn);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addWaitsOnAt(Table aWaitsOn, int index)
  {  
    boolean wasAdded = false;
    if(addWaitsOn(aWaitsOn))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWaitsOn()) { index = numberOfWaitsOn() - 1; }
      waitsOn.remove(aWaitsOn);
      waitsOn.add(index, aWaitsOn);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveWaitsOnAt(Table aWaitsOn, int index)
  {
    boolean wasAdded = false;
    if(waitsOn.contains(aWaitsOn))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWaitsOn()) { index = numberOfWaitsOn() - 1; }
      waitsOn.remove(aWaitsOn);
      waitsOn.add(index, aWaitsOn);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addWaitsOnAt(aWaitsOn, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=waitsOn.size(); i > 0; i--)
    {
      Table aWaitsOn = waitsOn.get(i - 1);
      aWaitsOn.delete();
    }
    super.delete();
  }

}