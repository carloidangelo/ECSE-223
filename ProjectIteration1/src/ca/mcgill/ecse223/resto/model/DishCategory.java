/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.util.*;

// line 73 "../../../../../RestoApp1a.ump"
public class DishCategory
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //DishCategory Attributes
  private String categoryName;

  //DishCategory Associations
  private List<DishName> contains;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public DishCategory(String aCategoryName)
  {
    categoryName = aCategoryName;
    contains = new ArrayList<DishName>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCategoryName(String aCategoryName)
  {
    boolean wasSet = false;
    categoryName = aCategoryName;
    wasSet = true;
    return wasSet;
  }

  public String getCategoryName()
  {
    return categoryName;
  }

  public DishName getContain(int index)
  {
    DishName aContain = contains.get(index);
    return aContain;
  }

  public List<DishName> getContains()
  {
    List<DishName> newContains = Collections.unmodifiableList(contains);
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

  public int indexOfContain(DishName aContain)
  {
    int index = contains.indexOf(aContain);
    return index;
  }

  public static int minimumNumberOfContains()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public DishName addContain(String aName, String aPrice)
  {
    return new DishName(aName, aPrice, this);
  }

  public boolean addContain(DishName aContain)
  {
    boolean wasAdded = false;
    if (contains.contains(aContain)) { return false; }
    DishCategory existingIsType = aContain.getIsType();
    boolean isNewIsType = existingIsType != null && !this.equals(existingIsType);
    if (isNewIsType)
    {
      aContain.setIsType(this);
    }
    else
    {
      contains.add(aContain);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeContain(DishName aContain)
  {
    boolean wasRemoved = false;
    //Unable to remove aContain, as it must always have a isType
    if (!this.equals(aContain.getIsType()))
    {
      contains.remove(aContain);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addContainAt(DishName aContain, int index)
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

  public boolean addOrMoveContainAt(DishName aContain, int index)
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
    for(int i=contains.size(); i > 0; i--)
    {
      DishName aContain = contains.get(i - 1);
      aContain.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "categoryName" + ":" + getCategoryName()+ "]";
  }
}