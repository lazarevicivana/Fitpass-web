package beans;

import java.time.LocalTime;
import java.util.Date;

public class TrainingHistory {
	private Date signDate;
	private LocalTime timeOfSign;
	private String trainingId;
	private String customerId;
	private String trainerId;
	private String id;
	private boolean canceled;

	public TrainingHistory() {
		super();
	}

	public TrainingHistory(String id,LocalTime timeOfSign ,Date signDate, String trainingId, String customerId, String trainerId, boolean canceled) {
		super();
		this.id = id;
		this.timeOfSign = timeOfSign;
		this.signDate = signDate;
		this.trainingId = trainingId;
		this.customerId = customerId;
		this.trainerId = trainerId;
		this.canceled = canceled;
		
	}

	public Date getSignDate() {
		return signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTrainingId() {
		return trainingId;
	}
	public void setTrainingId(String trainingId) {
		this.trainingId = trainingId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getTrainerId() {
		return trainerId;
	}
	public void setTrainerId(String trainerId) {
		this.trainerId = trainerId;
	}

	public LocalTime getTimeOfSign() {
		return timeOfSign;
	}

	public void setTimeOfSign(LocalTime timeOfSign) {
		this.timeOfSign = timeOfSign;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}
	
	
	

}
