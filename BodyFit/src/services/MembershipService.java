package services;

import java.io.File;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import beans.Membership;
import beans.PromoCode;
import dto.MembershipDto;
import dto.PromoCodeDto;
import dao.MembershipDao;

@Path("/memberships")
public class MembershipService {
	MembershipDao membershipDao = new MembershipDao();
	
	@Context
	ServletContext ctx;
	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("memberships") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("memberships", new MembershipService());
		}
	}
	public String getContext() {
		return (ctx.getRealPath("") + "WEB-INF" + File.separator + "classes" + File.separator + "jsonData"
				+ File.separator);
	}
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Membership> getAllMemberhips() {
		membershipDao.setBasePath(getContext());
		return membershipDao.getAllToList();
	}
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Membership createMemberhips(MembershipDto membership) {
		membershipDao.setBasePath(getContext());
		Membership membershipNew = new Membership(GenerateId(), membership.getType(), membership.startDate, membership.startDate, null, membership.price, membership.customerId, membership.isActive, membership.numberOfTrainings);
		membershipDao.create(membershipNew);
		return membershipNew;
	}
	
	@PUT
	@Path("/")	
	@Consumes(MediaType.APPLICATION_JSON)
	public void deactivateMembership(MembershipDto membership) {
		membershipDao.setBasePath(getContext());
		ArrayList<Membership> memberships = (ArrayList<Membership>) membershipDao.getAllToList()
																	.stream().filter(m -> m.getCustomerId().equals(membership.customerId))
																	.collect(Collectors.toList());
		for (Membership m :memberships) {
			m.setActive(false);
			membershipDao.update(m);
		}	  
	}
	
	private String GenerateId() {
		long id = membershipDao.getAllToList().stream().count();
		return Long.toString(id);
	}
	
}
