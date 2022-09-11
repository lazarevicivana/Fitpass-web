package dto;

import beans.FacilityContentType;
import beans.TrainingType;

public class FacilityContentDto {
	public String id;
	public String name;
	public String type;
	public String sportFacilityId;
	public String duration;
	public String description;
	public boolean deleted;
	public String price;
	
	public FacilityContentType getFacilityContentType() {
		if (type.equals("SPA"))
			return FacilityContentType.SPA;
		else if(type.equals("POOL"))
			return FacilityContentType.POOL;
		else 
			return FacilityContentType.SAUNA;
	}

}
