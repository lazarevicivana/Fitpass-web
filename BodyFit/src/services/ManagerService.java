package services;

import java.io.File;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.tomcat.jni.User;

import beans.Manager;
import beans.Training;
import beans.UserRole;
import dao.ManagerDao;
import dto.ManagerDto;
import dto.TrainingDto;
@Path("/managers")
public class ManagerService {
	ManagerDao managerDao = new ManagerDao();
	
	@Context
	ServletContext ctx;
	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("managers") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("managers", new ManagerService());
		}
	}
	public String getContext() {
		return (ctx.getRealPath("") + "WEB-INF" + File.separator + "classes" + File.separator + "jsonData"
				+ File.separator);
	}
	@GET
	@Path("/GetAll")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Manager> getAllMangers() {
		managerDao.setBasePath(getContext());
		return managerDao.getAllToList();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Manager getManagerByUsername(@PathParam(value = "id") String username) {
		managerDao.setBasePath(getContext());
		
		return managerDao.getAllToList().stream()
				.filter(manager -> manager.getUsername().equals(username))
				.findFirst().get();
	}
	
	@POST
	@Path("/create")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Manager createManger(ManagerDto manager) {
		managerDao.setBasePath(getContext());
		Manager managerNew = new Manager(manager.username, manager.password, manager.name, manager.surname, manager.birthday, manager.gerGenderEnum(), UserRole.MANAGER, manager.sportFacilityId);
		managerDao.create(managerNew);
		return managerNew;
	}
	
	@GET
	@Path("available-managers")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Manager> getAvailableManagers(){
		managerDao.setBasePath(getContext());
		return (ArrayList<Manager>) managerDao.getAllToList()
				.stream()
				.filter(manager -> manager.getSportFacilityId().isEmpty())
				.collect(Collectors.toList());
	}
	
	@PUT
	@Path("/updateFacilityId")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Manager updateManagerFacilityId(ManagerDto manager) {
		managerDao.setBasePath(getContext());
		Manager managerNew = new Manager(manager.username, manager.password, manager.name, manager.surname, manager.birthday, manager.gerGenderEnum(), UserRole.MANAGER, manager.sportFacilityId);
		managerDao.update(managerNew);
	    return managerNew;
	}
	
	
}
