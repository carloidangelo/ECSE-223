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
  private float price;

  //DishName Associations
  private DishCategory category;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public DishName(String aName, float aPrice, DishCategory aCategory)
  {
    name = aName;
    price = aPrice;
    boolean didAddCategory = setCategory(aCategory);
    if (!didAddCategory)
    {
      throw new RuntimeException("Unable to create categoryItem due to category");
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

  public boolean setPrice(float aPrice)
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

  public float getPrice()
  {
    return price;
  }

  public DishCategory getCategory()
  {
    return category;
  }

  public boolean setCategory(DishCategory aCategory)
  {
    boolean wasSet = false;
    if (aCategory == null)
    {
      return wasSet;
    }

    DishCategory existingCategory = category;
    category = aCategory;
    if (existingCategory != null && !existingCategory.equals(aCategory))
    {
      existingCategory.removeCategoryItem(this);
    }
    category.addCategoryItem(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    DishCategory placeholderCategory = category;
    this.category = null;
    if(placeholderCategory != null)
    {
      placeholderCategory.removeCategoryItem(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "price" + ":" + getPrice()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "category = "+(getCategory()!=null?Integer.toHexString(System.identityHashCode(getCategory())):"null");
  }
}