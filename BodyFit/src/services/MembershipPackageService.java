package services;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.MembershipPackage;
import dao.MembershipPackageDao;

@Path("/membership-package")
public class MembershipPackageService {
	MembershipPackageDao membershipPackageDao = new MembershipPackageDao();
	
	@Context
	ServletContext ctx;
	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("membershipPackages") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("membershipPackages", new MembershipPackageService());
		}
	}
	public String getContext() {
		return (ctx.getRealPath("") + "WEB-INF" + File.separator + "classes" + File.separator + "jsonData"
				+ File.separator);
	}
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<MembershipPackage> getAllMembershipPackages() {
		membershipPackageDao.setBasePath(getContext());
		return membershipPackageDao.getAllToList();
	}
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public MembershipPackage getMembershipPackagesById(@PathParam(value = "id") String id) {
		membershipPackageDao.setBasePath(getContext());
		return membershipPackageDao.getById(id);
	}

}
