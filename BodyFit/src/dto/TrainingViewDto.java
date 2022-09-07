package dto;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import beans.SportFacility;
import beans.Training;
import beans.TrainingHistory;
import beans.TrainingType;

public class TrainingViewDto {

	public String id;
	public String name;
	public String type;
	public String sportFacilityId;
	public SportFacility facility;
	public int duration;
	public String trainerId;
	public String description;
	public boolean deleted;
	public boolean canceled;
	public int price;
	public Date signDate;
	public String timeOfSign;
	
	public TrainingViewDto(Training training) {
		this.id = training.getId();
		this.name = training.getName();
		this.type = getTrainingTypeToString(training.getType());
		this.sportFacilityId = training.getSportFacilityId();
		this.duration = training.getDuration();
		this.trainerId = training.getTrainerId();
		this.description = training.getDescription();
		this.deleted = training.isDeleted();
		this.canceled = training.isCanceled();
		this.price = training.getPrice();
		
	}
	
	public TrainingViewDto() {
		super();
	}

	
	public TrainingType getTrainingType() {
		if (type.equals("GROUP"))
			return TrainingType.GROUP;
		else if(type.equals("PERSONAL"))
			return TrainingType.PERSONAL;
		else if(type.equals("AEROBIC"))
			return TrainingType.AEROBIC;
		else if(type.equals("CARDIO"))
			return TrainingType.CARDIO;
		else if(type.equals("GYM"))
			return TrainingType.GYM;
		else if(type.equals("YOGA"))
			return TrainingType.YOGA;
		else if(type.equals("HIIT"))
			return TrainingType.HIIT;
		else if(type.equals("STRENGTH"))
			return TrainingType.STRENGTH;
		else
			return TrainingType.DANCE;
	}
	public String getTrainingTypeToString(TrainingType type) {
		if (type == TrainingType.GROUP)
			return "GROUP";
		else if(type == TrainingType.PERSONAL)
			return "PERSONAL";
		else if(type == TrainingType.AEROBIC)
			return "AEROBIC";
		else if(type == TrainingType.CARDIO)
			return "CARDIO";
		else if(type == TrainingType.GYM)
			return "GYM";
		else if(type == TrainingType.YOGA)
			return "YOGA";
		else if(type == TrainingType.HIIT)
			return "HIIT";
		else if(type == TrainingType.STRENGTH)
			return "STRENGTH";
		else
			return"DANCE";
	}
	
	private String FormatDate(LocalTime time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH.mm");
		String timeString = time.format(formatter);
		return timeString;
	}
	
}
