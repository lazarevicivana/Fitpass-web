package services;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.Membership;
import beans.PromoCode;
import dao.MembershipDao;
import dao.PromoCodeDao;
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
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public PromoCode createPromoCode(PromoCodeDto promoCode) {
		promoCodeDao.setBasePath(getContext());
		PromoCode promoCodeNew = new PromoCode(GenerateId(),promoCode.code, promoCode.expireDate,promoCode.numberOfUsage, promoCode.percentage);
		promoCodeDao.create(promoCodeNew);
		return promoCodeNew;
	}
	private String GenerateId() {
		long id = promoCodeDao.getAllToList().stream().count();
		return Long.toString(id);
	}
}
