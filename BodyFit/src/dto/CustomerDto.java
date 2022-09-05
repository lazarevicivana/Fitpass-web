package dto;

import java.util.ArrayList;
import java.util.Date;

import beans.CustomerType;
import beans.Due;

public class CustomerDto {
	public String username;
	public String password;
	public String name;
	public String surname;
	public String gender;
	public Date birthday;	
	public String userRole;
	public boolean deleted;
	public boolean banned;
	public String sportFacilityId;
	public String birthdate;
	public CustomerType customerType;
	public double colletedPoints;
	public ArrayList<String> visitedFacilities;
	public Due due;


}
