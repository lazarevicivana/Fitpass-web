package services;

import java.io.File;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.Admin;
import beans.Trainer;
import beans.UserRole;
import dao.AdminDao;
import dto.UserDto;

@Path("/admins")
public class AdminService {
	
	AdminDao adminDao = new AdminDao();
	
	@Context
	ServletContext ctx;
	@SuppressWarnings("unused")
	public void init() {
		if(ctx.getAttribute("admins") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("admins",new AdminService());
		}
	}
	
	public String getContext() {
		return (ctx.getRealPath("") + "WEB-INF" + File.separator + "classes" + File.separator + "jsonData"
				+ File.separator);
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Admin> getAllCustomers(){
		adminDao.setBasePath(getContext());
		return adminDao.getAllToList();
	}
	
	@PUT
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Admin updateAdmin(UserDto admin){
		adminDao.setBasePath(getContext());
		Admin adminNew = new Admin(admin.username, admin.password, admin.name, admin.surname, admin.birthday, admin.gerGenderEnum(), UserRole.ADMIN);
		adminDao.update(adminNew);
		return adminNew;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Admin getByUsername(@PathParam(value = "id") String username) {
		adminDao.setBasePath(getContext());
		return adminDao.getAllToList().stream()
				.filter(trainer -> trainer.getUsername().equals(username))
				.findFirst().get();
	}
}
