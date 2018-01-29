/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;

// line 79 "../../../../../RestoApp1a.ump"
public class BillingGroup
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //BillingGroup Associations
  private Customer contains;
  private Bill pays;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public BillingGroup(Customer aContains, Bill aPays)
  {
    boolean didAddContains = setContains(aContains);
    if (!didAddContains)
    {
      throw new RuntimeException("Unable to create partOf due to contains");
    }
    if (aPays == null || aPays.getPaidBy() != null)
    {
      throw new RuntimeException("Unable to create BillingGroup due to aPays");
    }
    pays = aPays;
  }

  public BillingGroup(Customer aContains)
  {
    boolean didAddContains = setContains(aContains);
    if (!didAddContains)
    {
      throw new RuntimeException("Unable to create partOf due to contains");
    }
    pays = new Bill(this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Customer getContains()
  {
    return contains;
  }

  public Bill getPays()
  {
    return pays;
  }

  public boolean setContains(Customer aContains)
  {
    boolean wasSet = false;
    //Must provide contains to partOf
    if (aContains == null)
    {
      return wasSet;
    }

    if (contains != null && contains.numberOfPartOf() <= Customer.minimumNumberOfPartOf())
    {
      return wasSet;
    }

    Customer existingContains = contains;
    contains = aContains;
    if (existingContains != null && !existingContains.equals(aContains))
    {
      boolean didRemove = existingContains.removePartOf(this);
      if (!didRemove)
      {
        contains = existingContains;
        return wasSet;
      }
    }
    contains.addPartOf(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Customer placeholderContains = contains;
    this.contains = null;
    if(placeholderContains != null)
    {
      placeholderContains.removePartOf(this);
    }
    Bill existingPays = pays;
    pays = null;
    if (existingPays != null)
    {
      existingPays.delete();
    }
  }

}