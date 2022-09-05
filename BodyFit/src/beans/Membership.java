package beans;

import java.util.Date;

public class Membership {
	private String id;
	private MembershipType type;
	private Date dateOfPayment;
	private Date startDate;
	private Date endDate;
	private int price;
	private String customerId;
	private boolean isActive;
	private int numberOfTrainings;
	private int usedTrainings;
	
	public Membership(String id, MembershipType type, Date dateOfPayment, Date startDate, Date endDate, int price,
			String customerId, boolean isActive, int numberOfTrainings, int usedTraining) {
		super();
		this.id = id;
		this.type = type;
		this.dateOfPayment = dateOfPayment;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
		this.customerId = customerId;
		this.isActive = isActive;
		this.numberOfTrainings = numberOfTrainings;
		this.usedTrainings = usedTrainings;
	}
	
	public Membership() {
		super();
	}

	public int getUsedTrainings() {
		return usedTrainings;
	}

	public void setUsedTrainings(int usedTrainings) {
		this.usedTrainings = usedTrainings;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public MembershipType getType() {
		return type;
	}
	public void setType(MembershipType type) {
		this.type = type;
	}
	public Date getDateOfPayment() {
		return dateOfPayment;
	}
	public void setDateOfPayment(Date dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public int getNumberOfTrainings() {
		return numberOfTrainings;
	}
	public void setNumberOfTrainings(int numberOfTrainings) {
		this.numberOfTrainings = numberOfTrainings;
	}
	
}
