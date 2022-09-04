package dto;

import java.util.Date;

import beans.MembershipType;

public class MembershipDto {
	public String type;
	public Date dateOfPayment;
	public Date startDate;
	public int price;
	public String customerId;
	public boolean isActive;
	public int numberOfTrainings;
	
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

 
