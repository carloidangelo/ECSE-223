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
  private List<DishName> categoryItem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public DishCategory(String aCategoryName)
  {
    categoryName = aCategoryName;
    categoryItem = new ArrayList<DishName>();
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

  public DishName getCategoryItem(int index)
  {
    DishName aCategoryItem = categoryItem.get(index);
    return aCategoryItem;
  }

  public List<DishName> getCategoryItem()
  {
    List<DishName> newCategoryItem = Collections.unmodifiableList(categoryItem);
    return newCategoryItem;
  }

  public int numberOfCategoryItem()
  {
    int number = categoryItem.size();
    return number;
  }

  public boolean hasCategoryItem()
  {
    boolean has = categoryItem.size() > 0;
    return has;
  }

  public int indexOfCategoryItem(DishName aCategoryItem)
  {
    int index = categoryItem.indexOf(aCategoryItem);
    return index;
  }

  public static int minimumNumberOfCategoryItem()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public DishName addCategoryItem(String aName, float aPrice)
  {
    return new DishName(aName, aPrice, this);
  }

  public boolean addCategoryItem(DishName aCategoryItem)
  {
    boolean wasAdded = false;
    if (categoryItem.contains(aCategoryItem)) { return false; }
    DishCategory existingCategory = aCategoryItem.getCategory();
    boolean isNewCategory = existingCategory != null && !this.equals(existingCategory);
    if (isNewCategory)
    {
      aCategoryItem.setCategory(this);
    }
    else
    {
      categoryItem.add(aCategoryItem);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCategoryItem(DishName aCategoryItem)
  {
    boolean wasRemoved = false;
    //Unable to remove aCategoryItem, as it must always have a category
    if (!this.equals(aCategoryItem.getCategory()))
    {
      categoryItem.remove(aCategoryItem);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addCategoryItemAt(DishName aCategoryItem, int index)
  {  
    boolean wasAdded = false;
    if(addCategoryItem(aCategoryItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCategoryItem()) { index = numberOfCategoryItem() - 1; }
      categoryItem.remove(aCategoryItem);
      categoryItem.add(index, aCategoryItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCategoryItemAt(DishName aCategoryItem, int index)
  {
    boolean wasAdded = false;
    if(categoryItem.contains(aCategoryItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCategoryItem()) { index = numberOfCategoryItem() - 1; }
      categoryItem.remove(aCategoryItem);
      categoryItem.add(index, aCategoryItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCategoryItemAt(aCategoryItem, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=categoryItem.size(); i > 0; i--)
    {
      DishName aCategoryItem = categoryItem.get(i - 1);
      aCategoryItem.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "categoryName" + ":" + getCategoryName()+ "]";
  }
}