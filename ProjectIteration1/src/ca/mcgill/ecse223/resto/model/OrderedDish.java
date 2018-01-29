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
  private List<Order> order;
  private DishName menuItem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public OrderedDish(int aNumItems, boolean aIsShared, DishName aMenuItem)
  {
    numItems = aNumItems;
    isShared = aIsShared;
    order = new ArrayList<Order>();
    if (!setMenuItem(aMenuItem))
    {
      throw new RuntimeException("Unable to create OrderedDish due to aMenuItem");
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

  public DishName getMenuItem()
  {
    return menuItem;
  }

  public boolean isNumberOfOrderValid()
  {
    boolean isValid = numberOfOrder() >= minimumNumberOfOrder();
    return isValid;
  }

  public static int minimumNumberOfOrder()
  {
    return 1;
  }

  public boolean addOrder(Order aOrder)
  {
    boolean wasAdded = false;
    if (order.contains(aOrder)) { return false; }
    order.add(aOrder);
    if (aOrder.indexOfOrderItem(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aOrder.addOrderItem(this);
      if (!wasAdded)
      {
        order.remove(aOrder);
      }
    }
    return wasAdded;
  }

  public boolean removeOrder(Order aOrder)
  {
    boolean wasRemoved = false;
    if (!order.contains(aOrder))
    {
      return wasRemoved;
    }

    if (numberOfOrder() <= minimumNumberOfOrder())
    {
      return wasRemoved;
    }

    int oldIndex = order.indexOf(aOrder);
    order.remove(oldIndex);
    if (aOrder.indexOfOrderItem(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aOrder.removeOrderItem(this);
      if (!wasRemoved)
      {
        order.add(oldIndex,aOrder);
      }
    }
    return wasRemoved;
  }

  public boolean setOrder(Order... newOrder)
  {
    boolean wasSet = false;
    ArrayList<Order> verifiedOrder = new ArrayList<Order>();
    for (Order aOrder : newOrder)
    {
      if (verifiedOrder.contains(aOrder))
      {
        continue;
      }
      verifiedOrder.add(aOrder);
    }

    if (verifiedOrder.size() != newOrder.length || verifiedOrder.size() < minimumNumberOfOrder())
    {
      return wasSet;
    }

    ArrayList<Order> oldOrder = new ArrayList<Order>(order);
    order.clear();
    for (Order aNewOrder : verifiedOrder)
    {
      order.add(aNewOrder);
      if (oldOrder.contains(aNewOrder))
      {
        oldOrder.remove(aNewOrder);
      }
      else
      {
        aNewOrder.addOrderItem(this);
      }
    }

    for (Order anOldOrder : oldOrder)
    {
      anOldOrder.removeOrderItem(this);
    }
    wasSet = true;
    return wasSet;
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

  public boolean setMenuItem(DishName aNewMenuItem)
  {
    boolean wasSet = false;
    if (aNewMenuItem != null)
    {
      menuItem = aNewMenuItem;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    ArrayList<Order> copyOfOrder = new ArrayList<Order>(order);
    order.clear();
    for(Order aOrder : copyOfOrder)
    {
      if (aOrder.numberOfOrderItem() <= Order.minimumNumberOfOrderItem())
      {
        aOrder.delete();
      }
      else
      {
        aOrder.removeOrderItem(this);
      }
    }
    menuItem = null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "numItems" + ":" + getNumItems()+ "," +
            "isShared" + ":" + getIsShared()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "menuItem = "+(getMenuItem()!=null?Integer.toHexString(System.identityHashCode(getMenuItem())):"null");
  }
}