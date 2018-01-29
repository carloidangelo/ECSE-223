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
  private BillingGroup payer;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Bill(BillingGroup aPayer)
  {
    if (aPayer == null || aPayer.getBill() != null)
    {
      throw new RuntimeException("Unable to create Bill due to aPayer");
    }
    payer = aPayer;
  }

  public Bill(Customer aMemberForPayer)
  {
    payer = new BillingGroup(aMemberForPayer, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public BillingGroup getPayer()
  {
    return payer;
  }

  public void delete()
  {
    BillingGroup existingPayer = payer;
    payer = null;
    if (existingPayer != null)
    {
      existingPayer.delete();
    }
  }

}