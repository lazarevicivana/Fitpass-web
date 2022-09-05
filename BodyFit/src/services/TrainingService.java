package services;

import java.io.File;
import java.util.ArrayList;
import java.util.stream.Collector;
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

import beans.Manager;
import beans.Training;
import beans.TrainingHistory;
import beans.UserRole;
import dao.TrainingDao;
import dao.TrainingHistoryDao;
import dto.ManagerDto;
import dto.TrainingDto;

@Path("/trainings")
public class TrainingService {
	TrainingDao trainingDao = new TrainingDao();
	TrainingHistoryDao trainingHistoryDao = new TrainingHistoryDao();
	
	@Context
	ServletContext ctx;
	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("trainings") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("trainings", new TrainingService());
		}
	}
	
	public String getContext() {
		return (ctx.getRealPath("") + "WEB-INF" + File.separator + "classes" + File.separator + "jsonData"
				+ File.separator);
	}
	
	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Training> getAllTrainings() {
		trainingDao.setBasePath(getContext());
		return trainingDao.getAllToList();
	}
	
	@POST
	@Path("/create")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Training createTraining(TrainingDto training) {
		trainingDao.setBasePath(getContext());
		Training trainingrNew = new Training(GenerateId(), training.name,training.getTrainingType(), training.sportFacilityId,Integer.parseInt(training.duration) , training.trainerId, training.description,Integer.parseInt(training.price), training.canceled);
		trainingDao.create(trainingrNew);
		return trainingrNew;
	}
	
	@GET
	@Path("/get-by/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Training getById(@PathParam( "id") String id) {
		trainingDao.setBasePath(getContext());
		
		return  trainingDao.getAllToList().stream()
				.filter(training -> training.getId().equals(id)).findFirst().get();
	}
	
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Training> getFacilityTrainings(@PathParam(value = "id") String id) {
		trainingDao.setBasePath(getContext());
		return (ArrayList<Training>) trainingDao.getAllToList().stream()
				.filter(training -> training.getSportFacilityId().equals(id))
				.collect(Collectors.toList());
	}
	
	
	
	@GET
	@Path("/trainer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Training> getTrainingsForTrainer(@PathParam(value = "id") String id) {
		trainingDao.setBasePath(getContext());
		ArrayList<Training> trainings  = new ArrayList<Training>();
         for (Training training : trainingDao.getAllToList()) {
        	 if(training.getTrainerId() != null) {
		    	 if(training.getTrainerId().equals(id)) {
		
		    		 trainings.add(training);
		    	 }
        	 }
		}
         return trainings;
	}
	
	
	
	@PUT
	@Path("/")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Training updateTraining(TrainingDto training) {
		trainingDao.setBasePath(getContext());
		Training trainingrNew = new Training(training.id, training.name,training.getTrainingType(), training.sportFacilityId,Integer.parseInt(training.duration) , training.trainerId, training.description,Integer.parseInt(training.price), training.canceled);
		trainingDao.update(trainingrNew);
		return trainingrNew;
	}
	
	@PUT
	@Path("/cancel")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Training cancelTraining(TrainingDto training) {
		trainingDao.setBasePath(getContext());

		Training trainingrNew = trainingDao.getById(training.id);
		trainingrNew.setCanceled(true);
		trainingDao.update(trainingrNew);
		return trainingrNew;
	}
	
	private String GenerateId() {
		long id = trainingDao.getAllToList().stream().count();
		return Long.toString(id);
	}	
}
