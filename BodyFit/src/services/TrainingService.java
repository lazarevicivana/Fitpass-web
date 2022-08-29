package services;

import java.io.File;
import java.util.ArrayList;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.Manager;
import beans.Training;
import beans.UserRole;
import dao.TrainingDao;
import dto.ManagerDto;
import dto.TrainingDto;

@Path("/trainings")
public class TrainingService {
	TrainingDao trainingDao = new TrainingDao();
	
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
		Training trainingrNew = new Training(GenerateId(), training.name,training.getTrainingType(), training.sportFacilityId,Integer.parseInt(training.duration) , training.trainerId, training.description);
		trainingDao.create(trainingrNew);
		return trainingrNew;
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
	
	private String GenerateId() {
		long id = trainingDao.getAllToList().stream().count();
		return Long.toString(id);
	}	
}
