/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.util.*;

// line 88 "../../../../../RestoApp1a.ump"
public class Restaurant
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Restaurant Attributes
  private String restaurantName;

  //Restaurant Associations
  private List<Table> tabling;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Restaurant(String aRestaurantName)
  {
    restaurantName = aRestaurantName;
    tabling = new ArrayList<Table>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setRestaurantName(String aRestaurantName)
  {
    boolean wasSet = false;
    restaurantName = aRestaurantName;
    wasSet = true;
    return wasSet;
  }

  public String getRestaurantName()
  {
    return restaurantName;
  }

  public Table getTabling(int index)
  {
    Table aTabling = tabling.get(index);
    return aTabling;
  }

  public List<Table> getTabling()
  {
    List<Table> newTabling = Collections.unmodifiableList(tabling);
    return newTabling;
  }

  public int numberOfTabling()
  {
    int number = tabling.size();
    return number;
  }

  public boolean hasTabling()
  {
    boolean has = tabling.size() > 0;
    return has;
  }

  public int indexOfTabling(Table aTabling)
  {
    int index = tabling.indexOf(aTabling);
    return index;
  }

  public static int minimumNumberOfTabling()
  {
    return 0;
  }

  public boolean addTabling(Table aTabling)
  {
    boolean wasAdded = false;
    if (tabling.contains(aTabling)) { return false; }
    tabling.add(aTabling);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTabling(Table aTabling)
  {
    boolean wasRemoved = false;
    if (tabling.contains(aTabling))
    {
      tabling.remove(aTabling);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addTablingAt(Table aTabling, int index)
  {  
    boolean wasAdded = false;
    if(addTabling(aTabling))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTabling()) { index = numberOfTabling() - 1; }
      tabling.remove(aTabling);
      tabling.add(index, aTabling);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTablingAt(Table aTabling, int index)
  {
    boolean wasAdded = false;
    if(tabling.contains(aTabling))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTabling()) { index = numberOfTabling() - 1; }
      tabling.remove(aTabling);
      tabling.add(index, aTabling);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTablingAt(aTabling, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    tabling.clear();
  }


  public String toString()
  {
    return super.toString() + "["+
            "restaurantName" + ":" + getRestaurantName()+ "]";
  }
}