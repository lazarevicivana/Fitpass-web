package beans;

public class Comment {
	private String id;
	private String customerId; 
	private String sportFacilityId; 
	private String text;
	private int grade;
	private CommentState state = CommentState.WAITING;
	
	public Comment(String id, String customerId, String sportFacilityId, String text, int grade, CommentState state) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.sportFacilityId = sportFacilityId;
		this.text = text;
		this.grade = grade;
		this.state = state;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getSportFacilityId() {
		return sportFacilityId;
	}
	public void setSportFacilityId(String sportFacilityId) {
		this.sportFacilityId = sportFacilityId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int rating) {
		this.grade = rating;
	}
	public CommentState getState() {
		return state;
	}
	public void setState(CommentState state) {
		this.state = state;
	}
	

}
