package services;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import beans.Membership;
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
	@Path("/GetAll")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Membership> getAllMemberhipss() {
		membershipDao.setBasePath(getContext());
		return membershipDao.getAllToList();
	}
}
