package rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlRootElement;

import DAO.EmployeeDAO;
import entities.Employee;

@Path("/json")
public class JSONService {
	// @GET
	// @Path("{name}")
	// @Produces(MediaType.APPLICATION_JSON)
	/*
	 * public Response getTrackInJSON(@PathParam("name") String name) {
	 * 
	 * Employee emp = new Employee(); emp.setPkey(41); emp.setName("meera123");
	 * emp.setAddress("baroda"); emp.setAge(33); emp.setDept("research");
	 * 
	 * return Response.status(200).entity(name).build(); }
	 */
	@GET
	@Path("{id : \\d+}")
	// support digit only
	public Response getUserById(@PathParam("id") String id) {

		return Response.status(200).entity("getUserById is called, id : " + id)
				.build();

	}

	@GET
	@Path("/username/{username : [a-zA-Z][a-zA-Z_0-9]}")
	public Response getUserByUserName(@PathParam("username") String username) {

		return Response.status(200)
				.entity("getUserByUserName is called, username : " + username)
				.build();

	}

	@GET
	@Path("/books/{isbn : \\d+}")
	public Response getUserBookByISBN(@PathParam("isbn") String isbn) {

		return Response.status(200)
				.entity("getUserBookByISBN is called, isbn : " + isbn).build();

	}

	@GET
	@Path("/book/{isbn : \\d+}")
	public Response getUserBookByISBN(@PathParam("isbn") String isbn,@MatrixParam("author") String author,
			@MatrixParam("country") String country) {

		return Response.status(200)
				.entity("getUserBookByISBN is called, isbn : " + isbn+", author : " + author + ", country : " + country).build();

	}

	// using pathparam to store values in variables
	@GET
	@Path("/bdate/{year:\\d+}/{month: [A-Z][a-z][a-z]}/{day: [a-z][a-z][a-z]}")
	public Response getUserHistory(@PathParam("year") int year,
			@PathParam("month") String month, @PathParam("day") String day) {

		String date = year + "/" + month + "/" + day;

		return Response.status(200)
				.entity("getUserHistory is called, year/month/day : " + date)
				.build();

	}

	
	@GET
	@Path("/query")
	public Response getUsers(@Context UriInfo info) {
 
		String from = info.getQueryParameters().getFirst("from");
		String to = info.getQueryParameters().getFirst("to");
		List<String> orderBy = info.getQueryParameters().get("orderBy");
 
		return Response
		   .status(200)
		   .entity("getUsers is called, from : " + from + ", to : " + to
			+ ", orderBy" + orderBy.toString()).build();
 
	}
	
	@GET
	@Path("/query_default")
	public Response getUsers(
		@DefaultValue("1000") @QueryParam("from") int from,
		@DefaultValue("999")@QueryParam("to") int to,
		@DefaultValue("name") @QueryParam("orderBy") List<String> orderBy) {
 
		return Response
		   .status(200)
		   .entity("getUsers is called, from : " + from + ", to : " + to
			+ ", orderBy" + orderBy.toString()).build();
 
	}
	
	@GET
	@Path("/wery")
	public Response getUsers(@QueryParam("from") int from,
			@QueryParam("to") int to) {

		return Response
				.status(200)
				.entity("getUsers is called, from : " + from + ", to : " + to
						).build();

	}

	// @POST
	// @Path("/post")
	// @Consumes(MediaType.APPLICATION_JSON)
	/*
	 * public Response employeeList(List<Employee> list) { // storing result in
	 * database EmployeeDAO.store_multiple(list); return
	 * Response.status(201).entity(list.toString()).build(); }
	 */

	/*
	 * public Response createEmployeeinJSON(Employee emp) {
	 * 
	 * 
	 * return Response.status(201).entity(emp).build();
	 * 
	 * }
	 */
}