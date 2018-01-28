/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.util.*;

// line 55 "../../../../../RestoApp1a.ump"
public class Order
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Order Associations
  private Customer placedBy;
  private List<OrderedDish> contains;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Order(Customer aPlacedBy)
  {
    boolean didAddPlacedBy = setPlacedBy(aPlacedBy);
    if (!didAddPlacedBy)
    {
      throw new RuntimeException("Unable to create placed due to placedBy");
    }
    contains = new ArrayList<OrderedDish>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Customer getPlacedBy()
  {
    return placedBy;
  }

  public OrderedDish getContain(int index)
  {
    OrderedDish aContain = contains.get(index);
    return aContain;
  }

  public List<OrderedDish> getContains()
  {
    List<OrderedDish> newContains = Collections.unmodifiableList(contains);
    return newContains;
  }

  public int numberOfContains()
  {
    int number = contains.size();
    return number;
  }

  public boolean hasContains()
  {
    boolean has = contains.size() > 0;
    return has;
  }

  public int indexOfContain(OrderedDish aContain)
  {
    int index = contains.indexOf(aContain);
    return index;
  }

  public boolean setPlacedBy(Customer aPlacedBy)
  {
    boolean wasSet = false;
    if (aPlacedBy == null)
    {
      return wasSet;
    }

    Customer existingPlacedBy = placedBy;
    placedBy = aPlacedBy;
    if (existingPlacedBy != null && !existingPlacedBy.equals(aPlacedBy))
    {
      existingPlacedBy.removePlaced(this);
    }
    placedBy.addPlaced(this);
    wasSet = true;
    return wasSet;
  }

  public boolean isNumberOfContainsValid()
  {
    boolean isValid = numberOfContains() >= minimumNumberOfContains();
    return isValid;
  }

  public static int minimumNumberOfContains()
  {
    return 1;
  }

  public boolean addContain(OrderedDish aContain)
  {
    boolean wasAdded = false;
    if (contains.contains(aContain)) { return false; }
    contains.add(aContain);
    if (aContain.indexOfPartOf(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aContain.addPartOf(this);
      if (!wasAdded)
      {
        contains.remove(aContain);
      }
    }
    return wasAdded;
  }

  public boolean removeContain(OrderedDish aContain)
  {
    boolean wasRemoved = false;
    if (!contains.contains(aContain))
    {
      return wasRemoved;
    }

    if (numberOfContains() <= minimumNumberOfContains())
    {
      return wasRemoved;
    }

    int oldIndex = contains.indexOf(aContain);
    contains.remove(oldIndex);
    if (aContain.indexOfPartOf(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aContain.removePartOf(this);
      if (!wasRemoved)
      {
        contains.add(oldIndex,aContain);
      }
    }
    return wasRemoved;
  }

  public boolean setContains(OrderedDish... newContains)
  {
    boolean wasSet = false;
    ArrayList<OrderedDish> verifiedContains = new ArrayList<OrderedDish>();
    for (OrderedDish aContain : newContains)
    {
      if (verifiedContains.contains(aContain))
      {
        continue;
      }
      verifiedContains.add(aContain);
    }

    if (verifiedContains.size() != newContains.length || verifiedContains.size() < minimumNumberOfContains())
    {
      return wasSet;
    }

    ArrayList<OrderedDish> oldContains = new ArrayList<OrderedDish>(contains);
    contains.clear();
    for (OrderedDish aNewContain : verifiedContains)
    {
      contains.add(aNewContain);
      if (oldContains.contains(aNewContain))
      {
        oldContains.remove(aNewContain);
      }
      else
      {
        aNewContain.addPartOf(this);
      }
    }

    for (OrderedDish anOldContain : oldContains)
    {
      anOldContain.removePartOf(this);
    }
    wasSet = true;
    return wasSet;
  }

  public boolean addContainAt(OrderedDish aContain, int index)
  {  
    boolean wasAdded = false;
    if(addContain(aContain))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfContains()) { index = numberOfContains() - 1; }
      contains.remove(aContain);
      contains.add(index, aContain);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveContainAt(OrderedDish aContain, int index)
  {
    boolean wasAdded = false;
    if(contains.contains(aContain))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfContains()) { index = numberOfContains() - 1; }
      contains.remove(aContain);
      contains.add(index, aContain);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addContainAt(aContain, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Customer placeholderPlacedBy = placedBy;
    this.placedBy = null;
    if(placeholderPlacedBy != null)
    {
      placeholderPlacedBy.removePlaced(this);
    }
    ArrayList<OrderedDish> copyOfContains = new ArrayList<OrderedDish>(contains);
    contains.clear();
    for(OrderedDish aContain : copyOfContains)
    {
      if (aContain.numberOfPartOf() <= OrderedDish.minimumNumberOfPartOf())
      {
        aContain.delete();
      }
      else
      {
        aContain.removePartOf(this);
      }
    }
  }

}