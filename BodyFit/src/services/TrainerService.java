package services;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
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

import beans.Trainer;
import beans.Training;
import beans.UserRole;
import dao.TrainerDao;
import dao.TrainingDao;
import dto.TrainerDto;
import dto.UserDto;

@Path("trainers")
public class TrainerService  {
	
	TrainerDao trainerDao = new TrainerDao();
	TrainingDao trainingDao = new TrainingDao();
	
	@Context
	ServletContext ctx;
	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("trainers") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("trainers", new TrainerService());
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
		return trainerDao.getAllToList();
	} 
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Trainer getByUsername(@PathParam(value = "id") String username) {
		trainerDao.setBasePath(getContext());
		return trainerDao.getAllToList().stream()
				.filter(trainer -> trainer.getUsername().equals(username))
				.findFirst()
				.get();
	}
	
	@GET
	@Path("/facility/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Trainer> getByFacility(@PathParam(value = "id") String id) {
		trainerDao.setBasePath(getContext());
		trainingDao.setBasePath(getContext());
		Map<String, Trainer> trainersMap =  new HashMap<String, Trainer>();
		for (Training t : trainingDao.getAllByFacility(id)) {
			if(t.getTrainerId()!= null) {
			Trainer trainer = getTrainerByUsername(t.getTrainerId());
			trainersMap.put(trainer.getUsername(), trainer);
		}
		}
		return trainersMap.values();
	}
	
	private  Trainer getTrainerByUsername(String trainerId) {
		  trainerDao.setBasePath(getContext());
		 return trainerDao.getAllToList().stream()
				.filter(trainer -> trainer.getUsername().equals(trainerId))
				.findFirst()
				.get();
		
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
	
	@PUT
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Trainer updateTrainer(UserDto trainer) {
		trainerDao.setBasePath(getContext());
		Trainer t = trainerDao.getById(trainer.username);
		t.setPassword(trainer.password);
		t.setName(trainer.name);
		t.setSurname(trainer.surname);
		t.setGender(trainer.gerGenderEnum());
		//Trainer trainerNew = new Trainer(trainer.username,trainer.password, trainer.name, trainer.surname, trainer.birthday, trainer.gerGenderEnum(),UserRole.TRAINER);
		trainerDao.update(t);
		return t;
	}	

}
