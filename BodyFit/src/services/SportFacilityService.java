package services;

import java.io.File;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


import beans.FacilityContent;
import beans.SportFacility;
import dao.SportFacilityDao;
import dto.FacilityDto;

@Path("/facilities")
public class SportFacilityService  {
	
	SportFacilityDao sportFacilityDao = new SportFacilityDao();
	
	@Context
	ServletContext ctx;
	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("facilities") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("facilities", new SportFacilityService());
		}
	}
	public String getContext() {
		return (ctx.getRealPath("") + "WEB-INF" + File.separator + "classes" + File.separator + "jsonData"
				+ File.separator);
	}
	
	@GET
	@Path("/GetAll")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<SportFacility> getAllFacilities() {
		sportFacilityDao.setBasePath(getContext());
		return sportFacilityDao.getAllToList();
	}
	
	@GET
	@Path("/get-all-dto")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<FacilityDto> getAllFacilityDto() {
		sportFacilityDao.setBasePath(getContext());
		ArrayList<FacilityDto> facilities = new ArrayList<FacilityDto>();
		for (SportFacility sportFacility : sportFacilityDao.getAllToList()) {
			FacilityDto dto = new FacilityDto(sportFacility);
			facilities.add(dto);
		}
		return facilities;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public SportFacility getById(@PathParam(value = "id") String id) {
		sportFacilityDao.setBasePath(getContext());
		
		return sportFacilityDao.getAllToList().stream()
				.filter(facility -> facility.getSportFacilityId().equals(id))
				.findFirst().get();
	}
	@POST
	@Path("/")	
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public void createFacility(FacilityDto facility) {
		sportFacilityDao.setBasePath(getContext());
		ArrayList<String> content = new ArrayList<String>();
		SportFacility sportFacility = new SportFacility(GenerateId(),facility.name,facility.getFacilityType(),content,facility.isWorking,facility.getAdress(),0,LocalTime.parse(facility.openTime),LocalTime.parse(facility.closeTime));
		sportFacilityDao.create(sportFacility);
	}
	
	private String GenerateId() {
		long id = sportFacilityDao.getAllToList().stream().count();
		System.out.println(Long.toString(id));
		return Long.toString(id);
	}	


}
