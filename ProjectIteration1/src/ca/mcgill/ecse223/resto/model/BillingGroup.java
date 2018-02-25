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
  private Customer member;
  private Bill bill;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public BillingGroup(Customer aMember, Bill aBill)
  {
    boolean didAddMember = setMember(aMember);
    if (!didAddMember)
    {
      throw new RuntimeException("Unable to create billingGroup due to member");
    }
    if (aBill == null || aBill.getPayer() != null)
    {
      throw new RuntimeException("Unable to create BillingGroup due to aBill");
    }
    bill = aBill;
  }

  public BillingGroup(Customer aMember)
  {
    boolean didAddMember = setMember(aMember);
    if (!didAddMember)
    {
      throw new RuntimeException("Unable to create billingGroup due to member");
    }
    bill = new Bill(this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Customer getMember()
  {
    return member;
  }

  public Bill getBill()
  {
    return bill;
  }

  public boolean setMember(Customer aMember)
  {
    boolean wasSet = false;
    //Must provide member to billingGroup
    if (aMember == null)
    {
      return wasSet;
    }

    if (member != null && member.numberOfBillingGroup() <= Customer.minimumNumberOfBillingGroup())
    {
      return wasSet;
    }

    Customer existingMember = member;
    member = aMember;
    if (existingMember != null && !existingMember.equals(aMember))
    {
      boolean didRemove = existingMember.removeBillingGroup(this);
      if (!didRemove)
      {
        member = existingMember;
        return wasSet;
      }
    }
    member.addBillingGroup(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Customer placeholderMember = member;
    this.member = null;
    if(placeholderMember != null)
    {
      placeholderMember.removeBillingGroup(this);
    }
    Bill existingBill = bill;
    bill = null;
    if (existingBill != null)
    {
      existingBill.delete();
    }
  }

}
