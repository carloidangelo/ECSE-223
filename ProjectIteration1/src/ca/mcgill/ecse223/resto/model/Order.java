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
  private Customer orderPlacer;
  private List<OrderedDish> orderItem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Order(Customer aOrderPlacer)
  {
    boolean didAddOrderPlacer = setOrderPlacer(aOrderPlacer);
    if (!didAddOrderPlacer)
    {
      throw new RuntimeException("Unable to create order due to orderPlacer");
    }
    orderItem = new ArrayList<OrderedDish>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Customer getOrderPlacer()
  {
    return orderPlacer;
  }

  public OrderedDish getOrderItem(int index)
  {
    OrderedDish aOrderItem = orderItem.get(index);
    return aOrderItem;
  }

  public List<OrderedDish> getOrderItem()
  {
    List<OrderedDish> newOrderItem = Collections.unmodifiableList(orderItem);
    return newOrderItem;
  }

  public int numberOfOrderItem()
  {
    int number = orderItem.size();
    return number;
  }

  public boolean hasOrderItem()
  {
    boolean has = orderItem.size() > 0;
    return has;
  }

  public int indexOfOrderItem(OrderedDish aOrderItem)
  {
    int index = orderItem.indexOf(aOrderItem);
    return index;
  }

  public boolean setOrderPlacer(Customer aOrderPlacer)
  {
    boolean wasSet = false;
    if (aOrderPlacer == null)
    {
      return wasSet;
    }

    Customer existingOrderPlacer = orderPlacer;
    orderPlacer = aOrderPlacer;
    if (existingOrderPlacer != null && !existingOrderPlacer.equals(aOrderPlacer))
    {
      existingOrderPlacer.removeOrder(this);
    }
    orderPlacer.addOrder(this);
    wasSet = true;
    return wasSet;
  }

  public boolean isNumberOfOrderItemValid()
  {
    boolean isValid = numberOfOrderItem() >= minimumNumberOfOrderItem();
    return isValid;
  }

  public static int minimumNumberOfOrderItem()
  {
    return 1;
  }

  public boolean addOrderItem(OrderedDish aOrderItem)
  {
    boolean wasAdded = false;
    if (orderItem.contains(aOrderItem)) { return false; }
    orderItem.add(aOrderItem);
    if (aOrderItem.indexOfOrder(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aOrderItem.addOrder(this);
      if (!wasAdded)
      {
        orderItem.remove(aOrderItem);
      }
    }
    return wasAdded;
  }

  public boolean removeOrderItem(OrderedDish aOrderItem)
  {
    boolean wasRemoved = false;
    if (!orderItem.contains(aOrderItem))
    {
      return wasRemoved;
    }

    if (numberOfOrderItem() <= minimumNumberOfOrderItem())
    {
      return wasRemoved;
    }

    int oldIndex = orderItem.indexOf(aOrderItem);
    orderItem.remove(oldIndex);
    if (aOrderItem.indexOfOrder(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aOrderItem.removeOrder(this);
      if (!wasRemoved)
      {
        orderItem.add(oldIndex,aOrderItem);
      }
    }
    return wasRemoved;
  }

  public boolean setOrderItem(OrderedDish... newOrderItem)
  {
    boolean wasSet = false;
    ArrayList<OrderedDish> verifiedOrderItem = new ArrayList<OrderedDish>();
    for (OrderedDish aOrderItem : newOrderItem)
    {
      if (verifiedOrderItem.contains(aOrderItem))
      {
        continue;
      }
      verifiedOrderItem.add(aOrderItem);
    }

    if (verifiedOrderItem.size() != newOrderItem.length || verifiedOrderItem.size() < minimumNumberOfOrderItem())
    {
      return wasSet;
    }

    ArrayList<OrderedDish> oldOrderItem = new ArrayList<OrderedDish>(orderItem);
    orderItem.clear();
    for (OrderedDish aNewOrderItem : verifiedOrderItem)
    {
      orderItem.add(aNewOrderItem);
      if (oldOrderItem.contains(aNewOrderItem))
      {
        oldOrderItem.remove(aNewOrderItem);
      }
      else
      {
        aNewOrderItem.addOrder(this);
      }
    }

    for (OrderedDish anOldOrderItem : oldOrderItem)
    {
      anOldOrderItem.removeOrder(this);
    }
    wasSet = true;
    return wasSet;
  }

  public boolean addOrderItemAt(OrderedDish aOrderItem, int index)
  {  
    boolean wasAdded = false;
    if(addOrderItem(aOrderItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrderItem()) { index = numberOfOrderItem() - 1; }
      orderItem.remove(aOrderItem);
      orderItem.add(index, aOrderItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOrderItemAt(OrderedDish aOrderItem, int index)
  {
    boolean wasAdded = false;
    if(orderItem.contains(aOrderItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrderItem()) { index = numberOfOrderItem() - 1; }
      orderItem.remove(aOrderItem);
      orderItem.add(index, aOrderItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOrderItemAt(aOrderItem, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Customer placeholderOrderPlacer = orderPlacer;
    this.orderPlacer = null;
    if(placeholderOrderPlacer != null)
    {
      placeholderOrderPlacer.removeOrder(this);
    }
    ArrayList<OrderedDish> copyOfOrderItem = new ArrayList<OrderedDish>(orderItem);
    orderItem.clear();
    for(OrderedDish aOrderItem : copyOfOrderItem)
    {
      if (aOrderItem.numberOfOrder() <= OrderedDish.minimumNumberOfOrder())
      {
        aOrderItem.delete();
      }
      else
      {
        aOrderItem.removeOrder(this);
      }
    }
  }

}