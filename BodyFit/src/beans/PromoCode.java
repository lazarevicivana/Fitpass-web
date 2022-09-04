package beans;

import java.util.Date;

public class PromoCode {
	private String code;
	private Date exireDate;
	private int numberOfUsage;
	private int percentage;
	private String id;
	public PromoCode(String id,String code, Date exireDate, int numberOfUsage, int percentage) {
		super();
		this.id = id;
		this.code = code;
		this.exireDate = exireDate;
		this.numberOfUsage = numberOfUsage;
		this.percentage = percentage;
	}
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getExireDate() {
		return exireDate;
	}
	public void setExireDate(Date exireDate) {
		this.exireDate = exireDate;
	}
	public int getNumberOfUsage() {
		return numberOfUsage;
	}
	public void setNumberOfUsage(int numberOfUsage) {
		this.numberOfUsage = numberOfUsage;
	}
	public int getpercentage() {
		return percentage;
	}
	public void setpercentage(int percentage) {
		this.percentage = percentage;
	}
	
}
