package services;

import java.io.File;
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
import dao.FacilityContentDao;
import dto.FacilityContentDto;

@Path("/contents")
public class FacilityContentService {
	
	FacilityContentDao facilityContentDao = new FacilityContentDao();
	
	@Context
	ServletContext ctx;
	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("facilityContent") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("facilityContent", new FacilityContentDao());
		}
	}
	public String getContext() {
		return (ctx.getRealPath("") + "WEB-INF" + File.separator + "classes" + File.separator + "jsonData"
				+ File.separator);
	}
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<FacilityContent> getAll() {
		facilityContentDao.setBasePath(getContext());
		return facilityContentDao.getAllToList();
	}
	
	@POST
	@Path("/")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public FacilityContent createContent(FacilityContentDto content) {
		facilityContentDao.setBasePath(getContext());
		String duration = content.duration;
		if((content.duration).isEmpty()) {
			duration = "0";
		}
		String price = content.duration;
		if((content.price).isEmpty()) {
			price = "0";
		}
		FacilityContent facilityContentNew = new FacilityContent(GenerateId(), content.name, content.getFacilityContentType(), content.sportFacilityId,Integer.parseInt(duration) , content.description, content.deleted, Integer.parseInt(price));
		facilityContentDao.create(facilityContentNew);
		return facilityContentNew;
	}
	private String GenerateId() {
		long id = facilityContentDao.getAllToList().stream().count();
		return Long.toString(id);
	}	

}
