package services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.Trainer;
import beans.UserRole;
import dao.TrainerDao;
import dto.TrainerDto;

@Path("trainers")
public class TrainerService  {
	
	TrainerDao trainerDao = new TrainerDao();
	
	@Context
	ServletContext ctx;
	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("trainers") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("trainers", new CustomerService());
		}
	}
	public String getContext() {
		return (ctx.getRealPath("") + "WEB-INF" + File.separator + "classes" + File.separator + "jsonData"
				+ File.separator);
	}
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Trainer> getAllTrainers() {
		trainerDao.setBasePath(getContext());
//
//		ArrayList<Customer> customers = new ArrayList<Customer>();
//
//		for (Customer c : customerDao.getAllToList())
//			customers.add(c);
//
//		System.out.println("Found " + customers.size() + " customers.");

		return trainerDao.getAllToList();
	}
	@POST
	@Path("/create")	
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public void createTrainer(TrainerDto trainer) {
		trainerDao.setBasePath(getContext());
		Trainer newTrainer = new Trainer(trainer.username,trainer.password, trainer.name, trainer.surname, trainer.birthday, trainer.gerGenderEnum(),UserRole.TRAINER);
		trainerDao.create(newTrainer);
		System.out.println(getContext());
		
	}
	

}
