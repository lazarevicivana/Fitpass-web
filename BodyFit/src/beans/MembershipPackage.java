package beans;

public class MembershipPackage {
	private String id;
	private MembershipType type;
	private int numberOfTrainings;
	private String description;
	public MembershipPackage(String id, MembershipType type, int numberOfTrainings, String description) {
		super();
		this.id = id;
		this.type = type;
		this.numberOfTrainings = numberOfTrainings;
		this.description = description;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public MembershipType getType() {
		return type;
	}
	public void setType(MembershipType type) {
		this.type = type;
	}
	public int getNumberOfTrainings() {
		return numberOfTrainings;
	}
	public void setNumberOfTrainings(int numberOfTrainings) {
		this.numberOfTrainings = numberOfTrainings;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
