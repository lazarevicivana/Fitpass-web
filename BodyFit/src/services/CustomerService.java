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

import beans.Customer;
import beans.Manager;
import dao.CustomerDao;
import dto.CustomerDto;
import dto.UserDto;
@Path("/customers")
public class CustomerService {
	CustomerDao customerDao = new CustomerDao();
	
	@Context
	ServletContext ctx;
	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("customers") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("customers", new CustomerService());
		}
	}
	public String getContext() {
		return (ctx.getRealPath("") + "WEB-INF" + File.separator + "classes" + File.separator + "jsonData"
				+ File.separator);
	}
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Customer> getAllCustomers() {
		customerDao.setBasePath(getContext());
		return customerDao.getAllToList();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomerByUsername(@PathParam(value = "id") String username) {
		customerDao.setBasePath(getContext());
		
		return customerDao.getAllToList().stream()
				.filter(customer -> customer.getUsername().equals(username))
				.findFirst().get();
	}
	
	@POST
	@Path("/Create")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Customer createCustomer(UserDto customer) {
		customerDao.setBasePath(getContext());
		Customer customerNew = new Customer(customer.username,customer.password,customer.name,customer.surname,customer.birthday,customer.gerGenderEnum(),null);
		customerDao.create(customerNew);
		return customerNew;
	}
	
	@PUT
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Customer updateCustomer(UserDto customer){
		customerDao.setBasePath(getContext());
	Customer customerNew = new Customer(customer.username,customer.password,customer.name,customer.surname,customer.birthday,customer.gerGenderEnum(),null);
		customerDao.update(customerNew);
		return customerNew;
	}
	@PUT
	@Path("/collected-points")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Customer updateCollectedPoints(CustomerDto customer){
		customerDao.setBasePath(getContext());
		Customer customerNew = customerDao.getById(customer.username);
		double point = customerNew.getColletedPoints() + customer.colletedPoints;
		System.out.println(customerNew.getColletedPoints());
		customerNew.setColletedPoints(point);
		System.out.println(customerNew.getColletedPoints());
		customerDao.update(customerNew);
		return customerNew;
	}
}