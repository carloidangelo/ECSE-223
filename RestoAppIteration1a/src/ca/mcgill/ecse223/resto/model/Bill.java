/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;

// line 83 "../../../../../RestoApp1a.ump"
public class Bill
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Bill Associations
  private BillingGroup paidBy;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Bill(BillingGroup aPaidBy)
  {
    if (aPaidBy == null || aPaidBy.getPays() != null)
    {
      throw new RuntimeException("Unable to create Bill due to aPaidBy");
    }
    paidBy = aPaidBy;
  }

  public Bill(Customer aContainsForPaidBy)
  {
    paidBy = new BillingGroup(aContainsForPaidBy, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public BillingGroup getPaidBy()
  {
    return paidBy;
  }

  public void delete()
  {
    BillingGroup existingPaidBy = paidBy;
    paidBy = null;
    if (existingPaidBy != null)
    {
      existingPaidBy.delete();
    }
  }

}