/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.util.*;

// line 59 "../../../../../RestoApp1a.ump"
public class OrderedDish
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //OrderedDish Attributes
  private int numItems;
  private boolean isShared;

  //OrderedDish Associations
  private List<Order> partOf;
  private DishName isDishOf;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public OrderedDish(int aNumItems, boolean aIsShared, DishName aIsDishOf)
  {
    numItems = aNumItems;
    isShared = aIsShared;
    partOf = new ArrayList<Order>();
    if (!setIsDishOf(aIsDishOf))
    {
      throw new RuntimeException("Unable to create OrderedDish due to aIsDishOf");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumItems(int aNumItems)
  {
    boolean wasSet = false;
    numItems = aNumItems;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsShared(boolean aIsShared)
  {
    boolean wasSet = false;
    isShared = aIsShared;
    wasSet = true;
    return wasSet;
  }

  public int getNumItems()
  {
    return numItems;
  }

  public boolean getIsShared()
  {
    return isShared;
  }

  public Order getPartOf(int index)
  {
    Order aPartOf = partOf.get(index);
    return aPartOf;
  }

  public List<Order> getPartOf()
  {
    List<Order> newPartOf = Collections.unmodifiableList(partOf);
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

  public int indexOfPartOf(Order aPartOf)
  {
    int index = partOf.indexOf(aPartOf);
    return index;
  }

  public DishName getIsDishOf()
  {
    return isDishOf;
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

  public boolean addPartOf(Order aPartOf)
  {
    boolean wasAdded = false;
    if (partOf.contains(aPartOf)) { return false; }
    partOf.add(aPartOf);
    if (aPartOf.indexOfContain(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aPartOf.addContain(this);
      if (!wasAdded)
      {
        partOf.remove(aPartOf);
      }
    }
    return wasAdded;
  }

  public boolean removePartOf(Order aPartOf)
  {
    boolean wasRemoved = false;
    if (!partOf.contains(aPartOf))
    {
      return wasRemoved;
    }

    if (numberOfPartOf() <= minimumNumberOfPartOf())
    {
      return wasRemoved;
    }

    int oldIndex = partOf.indexOf(aPartOf);
    partOf.remove(oldIndex);
    if (aPartOf.indexOfContain(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aPartOf.removeContain(this);
      if (!wasRemoved)
      {
        partOf.add(oldIndex,aPartOf);
      }
    }
    return wasRemoved;
  }

  public boolean setPartOf(Order... newPartOf)
  {
    boolean wasSet = false;
    ArrayList<Order> verifiedPartOf = new ArrayList<Order>();
    for (Order aPartOf : newPartOf)
    {
      if (verifiedPartOf.contains(aPartOf))
      {
        continue;
      }
      verifiedPartOf.add(aPartOf);
    }

    if (verifiedPartOf.size() != newPartOf.length || verifiedPartOf.size() < minimumNumberOfPartOf())
    {
      return wasSet;
    }

    ArrayList<Order> oldPartOf = new ArrayList<Order>(partOf);
    partOf.clear();
    for (Order aNewPartOf : verifiedPartOf)
    {
      partOf.add(aNewPartOf);
      if (oldPartOf.contains(aNewPartOf))
      {
        oldPartOf.remove(aNewPartOf);
      }
      else
      {
        aNewPartOf.addContain(this);
      }
    }

    for (Order anOldPartOf : oldPartOf)
    {
      anOldPartOf.removeContain(this);
    }
    wasSet = true;
    return wasSet;
  }

  public boolean addPartOfAt(Order aPartOf, int index)
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

  public boolean addOrMovePartOfAt(Order aPartOf, int index)
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

  public boolean setIsDishOf(DishName aNewIsDishOf)
  {
    boolean wasSet = false;
    if (aNewIsDishOf != null)
    {
      isDishOf = aNewIsDishOf;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    ArrayList<Order> copyOfPartOf = new ArrayList<Order>(partOf);
    partOf.clear();
    for(Order aPartOf : copyOfPartOf)
    {
      if (aPartOf.numberOfContains() <= Order.minimumNumberOfContains())
      {
        aPartOf.delete();
      }
      else
      {
        aPartOf.removeContain(this);
      }
    }
    isDishOf = null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "numItems" + ":" + getNumItems()+ "," +
            "isShared" + ":" + getIsShared()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "isDishOf = "+(getIsDishOf()!=null?Integer.toHexString(System.identityHashCode(getIsDishOf())):"null");
  }
}