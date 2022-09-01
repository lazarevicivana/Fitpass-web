package beans;

public class FacilityContent {
	private String id;
	private String name;
	private FacilityContentType type;
	private String sportFacilityId;
	private int duration;
	private String description;
	private boolean deleted;
	private int price;
	
	public FacilityContent(String id, String name, FacilityContentType type, String sportFacilityId, int duration,
			String description, boolean deleted, int price) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.sportFacilityId = sportFacilityId;
		this.duration = duration;
		this.description = description;
		this.deleted = deleted;
		this.price = price;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public FacilityContentType getType() {
		return type;
	}
	public void setType(FacilityContentType type) {
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	

}
