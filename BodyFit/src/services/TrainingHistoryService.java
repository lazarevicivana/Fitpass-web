package services;

import java.io.File;
import java.time.LocalTime;
import java.util.ArrayList;
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

import beans.Training;
import beans.TrainingHistory;
import dao.TrainingHistoryDao;
import dto.TrainingHistoryDto;

@Path("/training-history")
public class TrainingHistoryService {
	TrainingHistoryDao trainingHistoryDao = new TrainingHistoryDao();
	
	@Context
	ServletContext ctx;
	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("trainingHistory") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("trainingHistory", new TrainingHistoryService());
		}
	}
	public String getContext() {
		return (ctx.getRealPath("") + "WEB-INF" + File.separator + "classes" + File.separator + "jsonData"
				+ File.separator);
	}
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<TrainingHistory> getAllTrainingHistory() {
		trainingHistoryDao.setBasePath(getContext());
		return trainingHistoryDao.getAllToList();
	}
	
	@GET
	@Path("/get-by/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public TrainingHistory getById(@PathParam( "id") String id) {
		trainingHistoryDao.setBasePath(getContext());
		
		return  trainingHistoryDao.getAllToList().stream()
				.filter(training -> training.getTrainingId().equals(id)).findFirst().get();
	}
	
	@GET
	@Path("/customer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<TrainingHistory> getCustomerTraining(@PathParam(value = "id") String id) {
		trainingHistoryDao.setBasePath(getContext());
		return (ArrayList<TrainingHistory>) trainingHistoryDao.getAllToList().stream()
				.filter(training -> training.getCustomerId().equals(id))
				.collect(Collectors.toList());
	}
	
	@POST
	@Path("/")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public TrainingHistory createTrainingHistory(TrainingHistoryDto trainingHistory) {
		trainingHistoryDao.setBasePath(getContext());
		TrainingHistory trainingHistoryNew = new TrainingHistory(GenerateId(),LocalTime.parse(trainingHistory.timeOfSign),trainingHistory.signDate,trainingHistory.trainingId,trainingHistory.customerId,trainingHistory.trainerId);
		trainingHistoryDao.create(trainingHistoryNew);
		return trainingHistoryNew;
	}
	
	private String GenerateId() {
		long id = trainingHistoryDao.getAllToList().stream().count();
		return Long.toString(id);
	}
	
}
