package beans;

import java.time.Duration;

public class Training {
	private String id;
	private String name;
	private TrainingType type;
	private String sportFacilityId;
	private int duration;
	private String trainerId;
	private String description;
	private boolean deleted;
	
	public Training(String id, String name,TrainingType type, String sportFacilityId, int duration, String trainerId,
			String description) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.sportFacilityId = sportFacilityId;
		this.duration = duration;
		this.trainerId = trainerId;
		this.description = description;
		this.deleted = false;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public TrainingType getType() {
		return type;
	}
	public void setType(TrainingType type) {
		this.type = type;
	}
	public String getSportFacilityId() {
		return sportFacilityId;
	}
	public void setSportFacilityId(String sportFacilityId) {
		this.sportFacilityId = sportFacilityId;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getTrainerId() {
		return trainerId;
	}
	public void setTrainerId(String trainerId) {
		this.trainerId = trainerId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	@Override
	public String toString() {
		return "Training [id=" + id + ", type=" + type + ", sportFacilityId=" + sportFacilityId + ", duration="
				+ duration + ", trainerId=" + trainerId + ", description=" + description + ", deleted=" + deleted + "]";
	}

}
