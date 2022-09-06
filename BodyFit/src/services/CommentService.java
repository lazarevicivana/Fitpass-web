package services;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.Admin;
import beans.Comment;
import beans.CommentState;
import beans.UserRole;
import dao.CommentDao;
import dto.CommentDto;
import dto.UserDto;

@Path("/comments")
public class CommentService {
	
	CommentDao commentDao = new CommentDao();
	
	@Context
	ServletContext ctx;
	@SuppressWarnings("unused")
	public void init() {
		if(ctx.getAttribute("comments") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("comments",new CommentService());
		}
	}
	
	public String getContext() {
		return (ctx.getRealPath("") + "WEB-INF" + File.separator + "classes" + File.separator + "jsonData"
				+ File.separator);
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Comment> getAllComments(){
		commentDao.setBasePath(getContext());
		return commentDao.getAllToList();
	}
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Comment createComment(CommentDto comment){
		commentDao.setBasePath(getContext());
		Comment commentNew = new Comment(GenerateId(), comment.customerId, comment.sportFacilityId, comment.text, comment.grade, CommentState.WAITING);
		commentDao.create(commentNew);
		return commentNew;
	}
	
	@PUT
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Comment updateComment(CommentDto comment){
		commentDao.setBasePath(getContext());
		Comment commentNew = commentDao.getById(comment.id);
		System.out.println(comment.getState());
		commentNew.setState(comment.getState());
		System.out.println(commentNew.getState());
		commentDao.update(commentNew);
		return commentNew;
	}
	
	private String GenerateId() {
		long id = commentDao.getAllToList().stream().count();
		return Long.toString(id);
	}	
}