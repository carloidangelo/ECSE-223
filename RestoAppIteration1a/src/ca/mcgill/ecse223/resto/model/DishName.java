/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;

// line 67 "../../../../../RestoApp1a.ump"
public class DishName
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //DishName Attributes
  private String name;
  private String price;

  //DishName Associations
  private DishCategory isType;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public DishName(String aName, String aPrice, DishCategory aIsType)
  {
    name = aName;
    price = aPrice;
    boolean didAddIsType = setIsType(aIsType);
    if (!didAddIsType)
    {
      throw new RuntimeException("Unable to create contain due to isType");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setPrice(String aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getPrice()
  {
    return price;
  }

  public DishCategory getIsType()
  {
    return isType;
  }

  public boolean setIsType(DishCategory aIsType)
  {
    boolean wasSet = false;
    if (aIsType == null)
    {
      return wasSet;
    }

    DishCategory existingIsType = isType;
    isType = aIsType;
    if (existingIsType != null && !existingIsType.equals(aIsType))
    {
      existingIsType.removeContain(this);
    }
    isType.addContain(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    DishCategory placeholderIsType = isType;
    this.isType = null;
    if(placeholderIsType != null)
    {
      placeholderIsType.removeContain(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "price" + ":" + getPrice()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "isType = "+(getIsType()!=null?Integer.toHexString(System.identityHashCode(getIsType())):"null");
  }
}