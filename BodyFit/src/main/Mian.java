package main;

import java.util.Date;
import beans.Customer;
import beans.Gender;
import beans.UserRole;

import dao.CustomerDao;
import dao.SportFacilityDao;

public class Mian {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CustomerDao dao = new CustomerDao();
		Date date = new Date();
		Customer customer = new Customer("laza", "lazarevic", "m", "m",date, Gender.MALE, UserRole.CUSTOMER, 11);
		dao.create(customer);
		
		

		SportFacilityDao facilityDao = new SportFacilityDao();

		//SportFacility facility = new SportFacility("7", "Flex", FacilityType.GYM, new ArrayList<>() , false, "dgasfdfsgdgasgf", 4.4, date, date);

		
		//facilityDao.create(facility);
		}
		

}
