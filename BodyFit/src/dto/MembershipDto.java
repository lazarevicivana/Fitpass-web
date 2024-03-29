package dto;

import java.util.Date;

import beans.MembershipType;

public class MembershipDto {
	public String id;
	public String type;
	public Date dateOfPayment;
	public Date startDate;
	public Date endDate;
	public int price;
	public String customerId;
	public boolean isActive;
	public int numberOfTrainings;	
	public int usedTrainings;
	public boolean active;

	
	public MembershipType getType() {
		if(type.equals("YEAR"))
			return MembershipType.YEAR;
		else if (type.equals("SIX_MONTHS")) 
			return MembershipType.SIX_MONTHS;
		else if (type.equals("THREE_MONTHS")) 
			return MembershipType.THREE_MONTHS;
		else  
			return MembershipType.MONTH;
	}
}

 
