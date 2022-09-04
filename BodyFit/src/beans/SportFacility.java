package beans;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class SportFacility {
	private String sportFacilityId;
	private String name;
	private FacilityType type;
	private ArrayList<String> facilityContent;
	private boolean isWorking;
	private Adress location;
	private Double averageGrade;
	private LocalTime openTime;
	private LocalTime closeTime;
	
	
	public SportFacility() {
		super();
	}
	public SportFacility(String sportFacilityId, String name, FacilityType type, ArrayList<String> facilityContent,
			boolean status, Adress location, double averageGrade, LocalTime openTime, LocalTime closeTime) {
		super();
		this.sportFacilityId = sportFacilityId;
		this.name = name;
		this.type = type;
		this.facilityContent = facilityContent;
		this.isWorking = status;
		this.location = location;
		this.averageGrade = averageGrade;
		this.openTime = openTime;
		this.closeTime = closeTime;
	}
	public String getSportFacilityId() {
		return sportFacilityId;
	}
	public void setSportFacilityId(String sportFacilityId) {
		this.sportFacilityId = sportFacilityId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public FacilityType getType() {
		return type;
	}
	public void setType(FacilityType type) {
		this.type = type;
	}
	public ArrayList<String> getFacilityContent() {
		return facilityContent;
	}
	public void setFacilityContent(ArrayList<String> facilityContent) {
		this.facilityContent = facilityContent;
	}
	public boolean isWorikng() {
		return isWorking;
	}
	public void setWorikng(boolean status) {
		this.isWorking = status;
	}
	public Adress getLocation() {
		return location;
	}
	public void setLocation(Adress location) {
		this.location = location;
	}
	public double getAverageGrade() {
		return averageGrade;
	}
	public void setAverageGrade(double averageGrade) {
		this.averageGrade = averageGrade;
	}
	public LocalTime getOpenTime() {
		return openTime;
	}
	public void setOpenTime(LocalTime openTime) {
		this.openTime = openTime;
	}
	public LocalTime getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(LocalTime closeTime) {
		this.closeTime = closeTime;
	}

	
}
