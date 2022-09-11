package dto;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import beans.Adress;
import beans.FacilityType;
import beans.SportFacility;

public class FacilityDto {
	public String sportFacilityId;
	public String name;
	public String type;
	public boolean isWorking;
	public String street;
	public String number;
	public String city;
	public String postalCode;
	public String longitude;
	public String latitude;
	public String averageGrade;
	public String openTime;
	public String closeTime;
	
	public FacilityDto() {
		super();
	}
	
	public FacilityDto(SportFacility sportFacility) {
		super();
		this.sportFacilityId = sportFacility.getSportFacilityId();
		this.name = sportFacility.getName();
		this.type = getFacilityTypeToString(sportFacility);
		this.isWorking = sportFacility.isWorikng();
		this.street = sportFacility.getLocation().getStreet();
		this.number = sportFacility.getLocation().getNumber();
		this.city = sportFacility.getLocation().getCity();
		this.postalCode = sportFacility.getLocation().getPostalCode();
		this.longitude = sportFacility.getLocation().getLongitude();
		this.latitude = sportFacility.getLocation().getLatitude();
		this.averageGrade = String.valueOf(sportFacility.getAverageGrade());
		this.openTime = FormatDate(sportFacility.getOpenTime());
		this.closeTime = FormatDate(sportFacility.getOpenTime());
	}
	
	public FacilityType getFacilityType() {
		if (type.equals("GYM"))
			return FacilityType.GYM;
		else if(type.equals("POOL"))
			return FacilityType.POOL;
		else if(type.equals("SPORTCENTER"))
			return FacilityType.SPORTCENTER;
		else if(type.equals("DANCINGSTUDIO"))
			return FacilityType.DANCINGSTUDIO;
		else 
			return FacilityType.BOWLINGCENTER;
	}
	
	public String getFacilityTypeToString(SportFacility sportFacility) {
		if (sportFacility.getType() == FacilityType.GYM)
			return "GYM";
		else if(sportFacility.getType() == FacilityType.POOL)
			return  "POOL";
		else if(sportFacility.getType() == FacilityType.SPORTCENTER)
			return "SPORTCENTER";
		else if(sportFacility.getType() == FacilityType.DANCINGSTUDIO)
			return "DANCINGSTUDIO";
		else 
			return "BOWLINGCENTER";
	}
	public Adress getAdress() {
		Adress adress = new Adress(street, number, city, postalCode, longitude, latitude);
		return adress;
	}
	private String FormatDate(LocalTime time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH.mm");
		String timeString = time.format(formatter);
		return timeString;
	}
}
