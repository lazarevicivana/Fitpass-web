package dto;

import java.time.Duration;

import beans.Gender;
import beans.TrainingType;

public class TrainingDto {
	public String id;
	public String name;
	public String type;
	public String sportFacilityId;
	public String duration;
	public String trainerId;
	public String description;
	public boolean deleted;
	public String price;
	
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
	

}
