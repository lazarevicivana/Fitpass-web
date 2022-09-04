package services;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.Manager;
import beans.Membership;
import beans.PromoCode;
import beans.UserRole;
import dao.MembershipDao;
import dao.PromoCodeDao;
import dto.ManagerDto;
import dto.PromoCodeDto;

@Path("/promo-codes")
public class PromoCodeService {
	PromoCodeDao promoCodeDao = new PromoCodeDao();
	
	@Context
	ServletContext ctx;
	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("promoCode") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("promoCode", new PromoCodeService());
		}
	}
	public String getContext() {
		return (ctx.getRealPath("") + "WEB-INF" + File.separator + "classes" + File.separator + "jsonData"
				+ File.separator);
	}
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<PromoCode> getAllPromoCodes() {
		promoCodeDao.setBasePath(getContext());
		return promoCodeDao.getAllToList();
	}
	@GET
	@Path("/{code}")
	@Produces(MediaType.APPLICATION_JSON)
	public PromoCode getPromoCode(@PathParam(value = "code") String code) {
		promoCodeDao.setBasePath(getContext());
		//promoCodeDao.getAllToList().stream().filter(x -> x.getCode().equals(code))
		return promoCodeDao.getAllToList()
				.stream()
				.filter(x -> x.getCode().equals(code))
				.findFirst()
				.get();
	}
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public PromoCode createPromoCode(PromoCodeDto promoCode) {
		promoCodeDao.setBasePath(getContext());
		PromoCode promoCodeNew = new PromoCode(GenerateId(),promoCode.code, promoCode.expireDate,promoCode.numberOfUsage, promoCode.percentage);
		promoCodeDao.create(promoCodeNew);
		return promoCodeNew;
	}
	@PUT
	@Path("/")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public PromoCode updatePromoCode(PromoCodeDto promoCode) {
		promoCodeDao.setBasePath(getContext());
		PromoCode promoCodeNew = promoCodeDao.getById(promoCode.id);
		promoCodeNew.setNumberOfUsage(promoCode.numberOfUsage);
		promoCodeDao.update(promoCodeNew);
	    return promoCodeNew;
	}
	private String GenerateId() {
		long id = promoCodeDao.getAllToList().stream().count();
		return Long.toString(id);
	}
}
