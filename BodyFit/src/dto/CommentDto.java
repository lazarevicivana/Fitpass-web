package dto;

import beans.CommentState;

public class CommentDto {
	public String customerId; 
	public String sportFacilityId; 
	public String text;
	public int grade;
	public String state;
	public String id;
	
	public CommentState getState() {
		if(state.equals("WAITING") ) {
			return CommentState.WAITING;
		}else if(state.equals("APPROVED") )
			return CommentState.APPROVED;
		else 
			return CommentState.DENIED;
	}
	
}
